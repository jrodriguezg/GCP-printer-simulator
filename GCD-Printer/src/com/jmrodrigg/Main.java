package com.jmrodrigg;

import com.google.api.client.http.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Pair;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.jmrodrigg.GCPrinter.*;

/**
 * Author: jrodriguezg
 * Date: 7/08/16
 */
public class Main {

    private static OAuth oAuth;

    private static Credentials credentials = new Credentials(Main.class.getClassLoader().getResourceAsStream("keys/api_key.json"));

    private static String printerid, authorization_code, email;

    private static boolean registerPrinter(boolean is_roll) {
        Pair<Integer,String> response;
        try {
            // 1. Register printer:
            response = register(is_roll);
        } catch (Exception ex) {
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
                response = getAuthCode(printer_id.getAsString(),credentials.getClientID());
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

                if (oAuth.authorize(false,credentials)) {
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
        List<PrintJob> jobs = new ArrayList<>();

        try {
            response = fetch(oAuth.getAccessToken(), printerid);
        } catch (IOException ex) {
            System.out.println("IO Exception");
            return jobs;
        }

        if (response.first == HttpStatusCodes.STATUS_CODE_OK) {
            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();

            if (object.get("success").getAsBoolean()) {

                JsonArray jobsJson = object.getAsJsonArray("jobs");

                System.out.println("Jobs Available (" + jobsJson.size() + ")");
                System.out.println("--------------");

                for (JsonElement obj : jobsJson) {
                    jobs.add(new PrintJob(obj));
                }

                return jobs;

            } else System.out.println("Error #" + object.get("errorCode").getAsInt() + ": " + object.get("message").getAsString());
        }

        return jobs;
    }

    private static boolean claimPrinter() {
        printerid = null;

        try {
            List<String> printers = Files.readAllLines(Paths.get("printers.txt"));
            if (!printers.isEmpty()) {
                int i = 1;
                for (String line : printers) {
                    System.out.println((i++) + "- " + line);
                }
                System.out.println("--------------");
                System.out.print("Pick one: ");

                int num = Integer.parseInt(new Scanner(System.in).next());
                System.out.println("");

                PrinterInfo pi = new PrinterInfo(printers.get(num - 1));

                // 2.- User claimed the printer. Let's get the access_token:
                oAuth = new OAuth(pi.authorization_code, pi.refresh_token);
                if (oAuth.authorize(true,credentials)) printerid = pi.printerid;
            } else {
                System.out.print("There are no printers on the list.");
            }
        } catch (IOException ex) {
            System.out.println("Error reading printers data.");
        }

        return printerid != null;
    }

    public static void main(String[] args) {

        do {
            System.out.println("");
            System.out.println("-------------------------Actions-------------------------");
            System.out.println("---------------------------------------------------------");
            System.out.println("| 1.- Register new printer.                             |");
            System.out.println("| 2.- Claim an already registered printer.              |");
            System.out.println("| 3.- List jobs from previous printer.                  |");
            System.out.println("| 4.- Update printer state.                             |");
            System.out.println("| 5.- Printer status (server).                          |");
            System.out.println("---------------------------------------------------------");

            System.out.print("Choose any action: ");
            int action = Integer.parseInt(new Scanner(System.in).next());
            System.out.println("");

            switch (action) {
                case 1:
                    System.out.print("(S)heet printer or (R)oll printer? ");
                    String type = new Scanner(System.in).next();
                    System.out.println("");

                    registerPrinter(type.equals("R"));
                    break;
                case 2:
                    if (claimPrinter()) System.out.println("Printer claimed successfully.");
                    else System.out.println("Couldn't retrieve printer.");
                    break;
                case 3:
                    if (printerid == null) System.out.println("printerid is null. Have you registered the printer?");
                    else {
                        List<PrintJob> jobs = getJobs();
                        if (jobs.size() > 0) {
                            int num = 1;
                            for (PrintJob job : jobs) System.out.println("#" + (num++) + "-->" + job.toString());

                            System.out.print("Select job number to print one or 0 cancel the operation: ");
                            int jobId = Integer.parseInt(new Scanner(System.in).next());

                            System.out.println("");

                            if (jobId > 0) {
                                PrintJob job = jobs.get(jobId-1);

                                try {
                                    Pair<Integer,String> response = getJobTicket(oAuth.getAccessToken(),job.getJobId());
                                    downloadFile(oAuth.getAccessToken(),job);
                                    printJob(oAuth.getAccessToken(),job);

                                } catch (IOException ex) {
                                    System.out.println("Error printing job.");
                                }
                            }
                        } else System.out.println("No jobs in printer queue.");
                    }
                    break;
                case 4:
                    if (printerid == null) System.out.println("printerid is null. Have you registered the printer?");
                    else {
                        try {
                            String printer_state = "IDLE";
                            Pair<Integer,String> response = updatePrinterState(oAuth.getAccessToken(), printerid, printer_state);
                            System.out.println("Printer State updated to " + printer_state + ".");
                        } catch (IOException ex) {
                            System.out.println("Error updating printer state.");
                        }
                    }
                    break;
                case 5:
                    if (printerid == null) System.out.println("printerid is null. Have you registered the printer?");
                    else {
                        try {
                            Pair<Integer,String> response = printer(oAuth.getAccessToken(), printerid);
                            System.out.println("Printer State response:");
                            System.out.println(response.second);
                        } catch (IOException ex) {
                            System.out.println("Error getting printer state.");
                        }
                    }
                    break;
                default:
                    System.out.println("Unknown action. Try again.");
            }
            System.out.println("");
        }while (true);
    }
}
