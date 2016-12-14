package com.jmrodrigg;

import com.google.gson.*;
import com.google.gson.internal.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.jmrodrigg.GCPClient.printer;
import static com.jmrodrigg.GCPClient.search;
import static com.jmrodrigg.GCPClient.submit;

/**
 * Author: jrodriguezg
 * Date: 7/15/16
 */
public class Main {

    private static OAuth oAuth = new OAuth(Main.class.getClassLoader().getResourceAsStream("keys/api_key.json"));

    private static Printer printer = null;

    public static void main(String[] args) {
        List<Printer> printers;

        System.out.print("Type your email address: ");
        String username = new Scanner(System.in).next();
        System.out.print("");

        if(oAuth.authorize(username)) {
            int action;
            int printernum;

            do {
                System.out.println("");
                System.out.println("-------------------------Actions-------------------------");
                System.out.println("---------------------------------------------------------");
                System.out.println("| 1.- Select printer.                                   |");
                if (printer != null) {
                    System.out.println("| 2.- Show capabilities for a printer.                  |");
                    System.out.println("| 3.- Submit a job.                                     |");
                }
                System.out.println("|                                                       |");
                System.out.println("| 0.- Exit program.                                     |");
                System.out.println("---------------------------------------------------------");

                System.out.print("Choose any action: ");
                action = Integer.parseInt(new Scanner(System.in).next());
                System.out.println("");

                switch (action) {
                    case 1:

                        printers = searchPrinters();

                        do {
                            System.out.print("Select a printer: ");
                            printernum = Integer.parseInt(new Scanner(System.in).next());
                            System.out.println("");

                            if ((printernum > 0) && (printernum <= printers.size()))
                                printer = printers.get(printernum - 1);

                        } while (printer == null);

                        break;
                    case 2:
                    case 3:

                        // May be not necessary, but just in case.
                        if (printer == null) {
                            System.out.print("First you need to select a printer.");
                            break;
                        }

                        if (action == 2) {
                            requestCapabilities();
                            System.out.println(printer.toString());
                        } else {
                            System.out.print("Which type of job: (P)DF or (J)PEG? ");
                            String job_type = new Scanner(System.in).next();
                            System.out.println("");

                            if (printer.getPrinterDescription() == null)
                                requestCapabilities();

                            if (submitJob(job_type)) System.out.println("Job sent successfully.");
                            else System.out.println("Error while submitting job.");
                        }
                        break;

                    case 0:
                        System.out.println("Bye-Bye =)");
                        break;

                    default:
                        System.out.println("Unknown action. Try again.");
                }
                System.out.println("");
            }while (action != 0);

        } else System.out.print("Error: Unauthorized.");
    }

    private static boolean submitJob(String jobType) {
        try {
            Pair<Integer, String> response = submit(oAuth.getAccessToken(), printer.getPrinterId(), jobType);

            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();
            return object.get("success").getAsBoolean();

        } catch (Exception ex) {
            System.out.println("IOException.");
        }

        return false;
    }

    private static List<Printer> searchPrinters() {
        List<Printer> printers = new ArrayList<>();

        try {
            Pair<Integer,String> response = search(oAuth.getAccessToken());

            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();
            JsonArray retPrinters = object.getAsJsonArray("printers");

            for (JsonElement printer: retPrinters) {
                String printerid = printer.getAsJsonObject().get("id").getAsString();
                String status = printer.getAsJsonObject().get("connectionStatus").getAsString();
                String displayName = printer.getAsJsonObject().get("displayName").getAsString();

                printers.add(new Printer(printerid,status,displayName));
            }

            // 2. Then list the printers owned by the user:
            for (int i=0; i< printers.size(); i++) System.out.println("#" + (i+1) + " -- " + printers.get(i).toString());

        } catch (IOException ex) {
            System.out.println("IOException.");
        }

        return printers;
    }

    private static void requestCapabilities() {
        try {
            Pair<Integer, String> response = printer(oAuth.getAccessToken(), printer.getPrinterId());

            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();
            JsonObject retCapabilities = object.getAsJsonArray("printers").get(0).getAsJsonObject().getAsJsonObject("capabilities");

            printer.setPrinterDescription(retCapabilities.getAsJsonObject("printer"));

        } catch (IOException ex) {
            System.out.println("IOException.");
        }
    }
}
