package com.jmrodrigg;

import com.google.gson.*;
import com.google.gson.internal.Pair;
import com.jmrodrigg.tasks.utils.PrinterStatusThread;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.jmrodrigg.GCPClient.search;
import static com.jmrodrigg.GCPClient.submit;

/**
 * Author: jrodriguezg
 * Date: 7/15/16
 */
public class Main {

    private static OAuth oAuth = new OAuth(Main.class.getClassLoader().getResourceAsStream("keys/api_key.json"));

    private static Printer printer = null;
    private static PrinterStatusThread printerStatusThread = null;

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

                            if (!printers.isEmpty()) {
                                for (int i = 0; i < printers.size(); i++)
                                    System.out.println("#" + (i + 1) + " -- " + printers.get(i).toString());

                                do {
                                    System.out.print("Select a printer: ");
                                    printernum = Integer.parseInt(new Scanner(System.in).next());
                                    System.out.println("");

                                    if ((printernum > 0) && (printernum <= printers.size()))
                                        printer = printers.get(printernum - 1);

                                } while (printer == null);

                                // Once the printer is selected. We start tracking its capabilities:
                                printerStatusThread = new PrinterStatusThread(printer.getPrinterId(), oAuth.getAccessToken());
                                printerStatusThread.addListener(thread -> {
                                    System.out.println("Error: Connection with selected printer was lost.");
                                    System.exit(0);
                                });
                                printerStatusThread.start();

                            } else System.out.println("No Printers retrieved. ");

                            break;
                        case 2:
                        case 3:

                            // May be not necessary, but just in case.
                            if (printer == null) {
                                System.out.println("First you need to select a printer.");
                                break;
                            }

                            if (action == 2) {
                                if (requestCapabilities()) System.out.println(printer.toString());
                                else System.out.println("Error - No valid capabilities.");
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
                            // Nothing to do.
                            break;

                        default:
                            System.out.println("Unknown action. Try again.");
                    }
                    System.out.println("");
                } while (action != 0);

            } else System.out.println("Error: Unauthorized.");
        } catch (SocketTimeoutException ex) {
            System.out.println("Error: Timeout.");
        }

        exit();
    }

    private static boolean submitJob(String jobType) throws SocketTimeoutException {
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

        } catch (SocketTimeoutException ex) {
            throw ex;
        } catch (Exception ex) {
            System.out.println("IOException:");
            ex.printStackTrace();
        }

        return success;
    }

    private static List<Printer> searchPrinters() throws SocketTimeoutException {
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

        } catch (SocketTimeoutException ex) {
            throw ex;
        } catch (IOException ex) {
            System.out.println("IOException:");
            ex.printStackTrace();
        }

        return printers;
    }

    private static boolean requestCapabilities() throws SocketTimeoutException {
        JsonObject object = printerStatusThread.getPrinterCapabilties();

        if (object != null) {
            printer.setPrinterDescription(object.getAsJsonObject("printer"));
            return true;
        } else return false;
    }

    private static void exit() {
        // Status Monitor safe stop:
        System.out.print("Stopping printer status monitor...");
        if ((printerStatusThread != null) && printerStatusThread.isAlive()) {
            printerStatusThread.interrupt();
            System.out.println(" Done.");
        } else System.out.println("Already stopped.");

        System.out.println("Bye-Bye =)");

        System.exit(0);
    }
}
