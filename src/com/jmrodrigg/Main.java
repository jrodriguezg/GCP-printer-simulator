package com.jmrodrigg;

import com.google.api.client.http.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Pair;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static com.jmrodrigg.GCPrinter.*;

public class Main {

    private static OAuth oAuth;
    private static String printerid;

    private static boolean registerPrinter() {
        Pair<Integer,String> response;
        try {
            // 1. Register printer:
            response = register();
        } catch (IOException ex) {
            System.out.println("IO Exception");
            printerid = null;
            return false;
        }

        if (response.first == HttpStatusCodes.STATUS_CODE_OK) {
            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();

            JsonElement invite_url = object.get("complete_invite_url");
            JsonElement polling_url = object.get("polling_url");

            System.out.println("Printer registration success. Please claim the printer at " + invite_url.getAsString());
            System.out.println("Once Done, press any key to continue...");

            new Scanner(System.in).next();

            try {
                // 2.- User claimed the printer. Let's get the auth code:
                String get_auth_code_url = polling_url.getAsString() + Credentials.CLIENT_ID;
                response = getAuthCode(get_auth_code_url);
            } catch (IOException ex) {
                System.out.println("IO Exception");
                printerid = null;
                return false;
            }

            if (response.first == HttpStatusCodes.STATUS_CODE_OK) {

                object = new JsonParser().parse(response.second).getAsJsonObject();
                JsonArray printerIdArray = object.getAsJsonObject("request").getAsJsonObject("params").getAsJsonArray("printerid");
                printerid = printerIdArray.get(0).getAsString();

                // TODO Persistence of printerid

                String authorization_code = object.get("authorization_code").getAsString();
                String email = object.get("user_email").getAsString();

                System.out.println("Printer registered to user " + email);

                oAuth = new OAuth(authorization_code);

                if (oAuth.authorize()) {
                    System.out.println("Access token received: " + oAuth.getAccessToken());
                    return true;
                }
            }
        }

        printerid = null;
        return false;
    }

    private static void getJobs() {
        Pair<Integer,String> response;
        try {
            response = fetch(oAuth.getAccessToken(), printerid);
        } catch (IOException ex) {
            System.out.println("IO Exception");
            return;
        }

        if (response.first == HttpStatusCodes.STATUS_CODE_OK) {
            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();

            if (object.get("success").getAsBoolean()) {

                JsonArray jobs = object.getAsJsonArray("jobs");

                System.out.println("Jobs Available (" + jobs.size() + ")");
                System.out.println("--------------");

                for (JsonElement obj : jobs) {
                    String title = obj.getAsJsonObject().get("title").getAsString();
                    String fileUrl = obj.getAsJsonObject().get("fileUrl").getAsString();
                    String rasterUrl = obj.getAsJsonObject().get("rasterUrl").getAsString();
                    int pages = obj.getAsJsonObject().get("numberOfPages").getAsInt();

                    String contentType = obj.getAsJsonObject().get("contentType").getAsString();
                    String status = obj.getAsJsonObject().get("status").getAsString();
                    Date updateTime = new Date(obj.getAsJsonObject().get("updateTime").getAsLong());

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd - HH:mm");

                    System.out.println(sdf.format(updateTime) + " - " + status + " - " + title + " - " + pages + " pages - " + contentType);
                    System.out.println("\t File Url: " + fileUrl);
                    System.out.println("\t Raster Url: " + rasterUrl);
                    System.out.println("--------------");
                }

            } else System.out.println("Error #" + object.get("errorCode").getAsInt() + ": " + object.get("message").getAsString());
        }
    }

    public static void main(String[] args) {

        do {
            System.out.println("-----------------Actions-----------------");
            System.out.println("-----------------------------------------");
            System.out.println("| 1.- Register new printer.             |");
            System.out.println("| 2.- List jobs from previous printer.  |");
            System.out.println("-----------------------------------------");
            System.out.print("Choose any action: ");

            int action = Integer.parseInt(new Scanner(System.in).next());
            System.out.println("");

            switch (action) {
                case 1:
                    registerPrinter();
                    break;
                case 2:
                    if (printerid == null) System.out.println("printerid is null. Have you registered the printer?");
                    else getJobs();
                    break;
                default:
                    System.out.println("Unknown action. Try again.");
            }
            System.out.println("");
        }while (true);
    }
}
