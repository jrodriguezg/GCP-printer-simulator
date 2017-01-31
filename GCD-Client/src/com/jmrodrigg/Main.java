package com.jmrodrigg;

import com.google.gson.*;
import com.google.gson.internal.Pair;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.net.SocketTimeoutException;
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

        try {
            if (oAuth.authorize(username)) {
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
                            for (int i = 0; i < printers.size(); i++)
                                System.out.println("#" + (i + 1) + " -- " + printers.get(i).toString());

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
                } while (action != 0);

            } else System.out.print("Error: Unauthorized.");
        } catch (SocketTimeoutException ex) {
            System.out.print("Error: Timeout.");
        }
    }

    private static boolean submitJob(String jobType) {
        boolean success = false;
        try {
            Pair<Integer, String> response = submit(oAuth.getAccessToken(), printer, jobType);
            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();

            if (response.first == HttpStatus.SC_OK) {
                if (object.has("success")) success = object.get("success").getAsBoolean();
                else {
                    if (object.has("error")) System.out.println(response.first + " - Error while submitting job --> " + object.get("error").getAsString());
                    else System.out.println(response.first + " - Unexpected error while submitting job.");
                }
            } else System.out.println("Error" + response.first + " while submitting job --> " + response.second);

        } catch (Exception ex) {
            System.out.println("IOException:");
            ex.printStackTrace();
        }

        return success;
    }

    private static List<Printer> searchPrinters() {
        List<Printer> printers = new ArrayList<>();

        try {
            Pair<Integer,String> response = search(oAuth.getAccessToken());
            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();

            if (response.first == HttpStatus.SC_OK) {
                if (object.has("success")) {
                    JsonArray retPrinters = object.getAsJsonArray("printers");
                    for (JsonElement printer: retPrinters) printers.add(new Printer(printer.getAsJsonObject()));

                } else {
                    if (object.has("error")) System.out.println(response.first + " - Error while requesting the list of printers --> " + object.get("error").getAsString());
                    else System.out.println(response.first + " - Unexpected error while retrieving printers list.");
                }

            } else System.out.println("Error" + response.first + " while retrieving printers list --> " + response.second);

        } catch (IOException ex) {
            System.out.println("IOException:");
            ex.printStackTrace();
        }

        return printers;
    }

    private static boolean requestCapabilities() {
        boolean success = false;

        try {
            Pair<Integer, String> response = printer(oAuth.getAccessToken(), printer.getPrinterId());
            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();

            if (response.first == HttpStatus.SC_OK) {
                if (object.has("success")) {
                    JsonObject retCapabilities = object.getAsJsonArray("printers").get(0).getAsJsonObject().getAsJsonObject("capabilities");
                    printer.setPrinterDescription(retCapabilities.getAsJsonObject("printer"));

                    success = true;
                } else {
                    if (object.has("error")) System.out.println(response.first + " - Error while requesting printer capabilities --> " + object.get("error").getAsString());
                    else System.out.println(response.first + " - Unexpected error while retrieving printer capabilities.");
                }

            } else System.out.println("Error" + response.first + "retrieving printer capabilities --> " + response.second);

        } catch (IOException ex) {
            System.out.println("IOException:");
            ex.printStackTrace();
        }

        return success;
    }
}
