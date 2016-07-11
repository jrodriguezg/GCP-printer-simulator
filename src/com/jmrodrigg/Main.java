package com.jmrodrigg;

import com.google.api.client.http.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.jmrodrigg.GCPrinter.*;

public class Main {

    private static OAuth oAuth;

    private static String printerid, authorization_code, email;

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
            JsonElement printer_id = object.get("printers").getAsJsonArray().get(0).getAsJsonObject().get("id");

            System.out.println("Printer registration success. printerid={" + printer_id.getAsString() + "}");
            System.out.println("Please claim the printer at " + invite_url.getAsString());
            System.out.println("Once Done, press any key to continue...");

            new Scanner(System.in).next();

            try {
                // 2.- User claimed the printer. Let's get the auth code:
                response = getAuthCode(printer_id.getAsString());
            } catch (IOException ex) {
                System.out.println("IO Exception");
                printerid = null;
                return false;
            }

            if (response.first == HttpStatusCodes.STATUS_CODE_OK) {

                object = new JsonParser().parse(response.second).getAsJsonObject();
                JsonArray printerIdArray = object.getAsJsonObject("request").getAsJsonObject("params").getAsJsonArray("printerid");
                printerid = printerIdArray.get(0).getAsString();
                authorization_code = object.get("authorization_code").getAsString();
                email = object.get("user_email").getAsString();

                System.out.println("Printer registered to user " + email);

                oAuth = new OAuth(authorization_code,null);

                if (oAuth.authorize(false)) {
                    System.out.println("Access token received: " + oAuth.getAccessToken());

                    try {
                        String printer_data = printerid + ";" + authorization_code + ";" + oAuth.getRefreshToken() + ";" + email + "\n";
                        Files.write(Paths.get("printers.txt"), printer_data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        return true;
                    } catch (IOException ex) {
                        System.out.println("Error. Printer data not stored.");
                        printerid = null;
                        return false;
                    }
                }
            }
        }

        printerid = null;
        return false;
    }

    private static List<PrintJob> getJobs() {
        Pair<Integer,String> response;
        try {
            response = fetch(oAuth.getAccessToken(), printerid);
        } catch (IOException ex) {
            System.out.println("IO Exception");
            return null;
        }

        if (response.first == HttpStatusCodes.STATUS_CODE_OK) {
            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();

            if (object.get("success").getAsBoolean()) {

                JsonArray jobsJson = object.getAsJsonArray("jobs");

                System.out.println("Jobs Available (" + jobsJson.size() + ")");
                System.out.println("--------------");

                List<PrintJob> jobs = new ArrayList<>();

                for (JsonElement obj : jobsJson) {
                    jobs.add(new PrintJob(obj));
                }

                return jobs;

            } else System.out.println("Error #" + object.get("errorCode").getAsInt() + ": " + object.get("message").getAsString());
        }

        return null;
    }

    private static PrinterInfo claimPrinter() {
        try {
            List<String> printers = Files.readAllLines(Paths.get("printers.txt"));
            int i= 1;
            for (String line : printers) {
                System.out.println((i++) + "- " + line);
            }
            System.out.println("--------------");
            System.out.print("Pick one: ");

            int num = Integer.parseInt(new Scanner(System.in).next());
            System.out.println("");

            return new PrinterInfo(printers.get(num-1));

        } catch (IOException ex) {
            System.out.println("Error reading printers data.");
        }

        return null;
    }

    public static void main(String[] args) {

        do {
            System.out.println("-------------------------Actions-------------------------");
            System.out.println("---------------------------------------------------------");
            System.out.println("| 1.- Register new printer.                             |");
            System.out.println("| 2.- Claim an already registered printer.              |");
            System.out.println("| 3.- List jobs from previous printer.                  |");
            System.out.println("---------------------------------------------------------");
            System.out.print("Choose any action: ");

            int action = Integer.parseInt(new Scanner(System.in).next());
            System.out.println("");

            switch (action) {
                case 1:
                    registerPrinter();
                    break;
                case 2:
                    PrinterInfo pi = claimPrinter();
                    if (pi != null) {
                        // 2.- User claimed the printer. Let's get the access_token:
                        oAuth = new OAuth(pi.authorization_code,pi.refresh_token);

                        boolean authorized = oAuth.authorize(true);

                        if (authorized) {
                            System.out.println("Printer claimed successfully.");
                            printerid = pi.printerid;
                        } else {
                            System.out.println("Couldn't retrieve printer.");
                            printerid = null;
                        }
                    }
                    break;
                case 3:
                    if (printerid == null) System.out.println("printerid is null. Have you registered the printer?");
                    else {
                        List<PrintJob> jobs = getJobs();
                        if (jobs != null)
                            for (PrintJob job : jobs) System.out.println(job.toString());
                    }
                    break;
                default:
                    System.out.println("Unknown action. Try again.");
            }
            System.out.println("");
        }while (true);
    }
}
