package com.jmrodrigg;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Pair;
import com.jmrodrigg.model.CDD.PrinterDescription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author: jrodriguezg
 * Date: 1/25/17
 */
public class Main {

//    private static Printer printer = null;
    private static PrivetDevice device = null;

    public static void main(String[] args) {
        int action;

        do {
            System.out.println("");
            System.out.println("-------------------------Actions-------------------------");
            System.out.println("---------------------------------------------------------");
                System.out.println("| 1.- Set device IP.                                    |");
            if (device != null) {
                System.out.println("| 2.- Show capabilities for a printer.                  |");
                System.out.println("| 4.- Submit a job.                                     |");
            }
            System.out.println("|                                                       |");
            System.out.println("| 0.- Exit program.                                     |");
            System.out.println("---------------------------------------------------------");

            System.out.print("Choose any action: ");
            action = Integer.parseInt(new Scanner(System.in).next());
            System.out.println("");

            switch (action) {
                case 1:
                    System.out.print("Set Device IP: ");
                    String ip = new Scanner(System.in).next();
                    System.out.println("");

                    info(ip);

                    break;
                case 2:
                case 3:
                case 4:
                    // May be not necessary, but just in case.
                    if (device == null) {
                        System.out.print("First you need to select a device IP.");
                        break;
                    }

                    if (action == 2) requestCapabilities();
                    else {
                        System.out.print("Which type of job: (P)DF or (J)PEG? ");
                        String job_type = new Scanner(System.in).next();
                        System.out.println("");

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
    }

    private static void info(String ip) {
        try {
            Pair<Integer, String> response = PrivetClient.info(ip);

            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();
            String type = object.getAsJsonArray("type").get(0).getAsString();

            String token = object.get("x-privet-token").getAsString();
            String name = object.get("name").getAsString();
            String status = object.get("device_state").getAsString();
            String deviceId = object.get("id").getAsString();
            JsonArray jsonApi = object.getAsJsonArray("api");
            ArrayList<String> api = new ArrayList<>();
            for (int i=0; i< jsonApi.size(); i++) api.add(jsonApi.get(i).getAsString());

            device = new PrivetDevice(ip, deviceId, name, token, status, type, api);

        } catch (IOException ex) {
            System.out.println("IOException.");
        }
    }

    private static void requestCapabilities() {
        try {
            Pair<Integer, String> response = PrivetClient.capabilities(device);
            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();

            PrinterDescription printerDescription = PrivetPrinter.setPrinterDescription(object.getAsJsonObject(device.getTypeStr()));
            device.setCapabilities(printerDescription);

            System.out.println(printerDescription.toString());
        } catch (IOException ex) {
            System.out.println("IOException.");
        }
    }

    private static boolean submitJob(String jobType) {
        try {
            JsonObject object;
            Integer job_id = null;

            // Try to create job ticket:
            if (device.supportsCreateJob()) {
                Pair<Integer, String> response = PrivetClient.create(device, jobType);
                object = new JsonParser().parse(response.second).getAsJsonObject();

                if (object.has("error")) {
                    System.out.println(object.get("error").getAsString());
                    return false;
                } else if (object.has("success")) {
                    job_id = object.get("job_id").getAsInt();
                }
            }

            // Submit Doc to print:
            Pair<Integer, String> response = PrivetClient.submit(device, jobType, job_id);

            object = new JsonParser().parse(response.second).getAsJsonObject();

            if (object.has("success")) {
                return object.get("success").getAsBoolean();
            } else if (object.has("error")) {
                System.out.println(object.get("error").getAsString());
                return false;
            }
        } catch (Exception ex) {
            System.out.println("IOException.");
        }

        return false;
    }
}
