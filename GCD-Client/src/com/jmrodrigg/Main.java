package com.jmrodrigg;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

    public static void main(String[] args) {
        List<Printer> printers;

        System.out.print("Type your email address: ");
        String username = new Scanner(System.in).next();
        System.out.print("");

        if(oAuth.authorize(username)) {
            int action;

            do {
                System.out.println("");
                System.out.println("-------------------------Actions-------------------------");
                System.out.println("---------------------------------------------------------");
                System.out.println("| 1.- List printers.                                    |");
                System.out.println("| 2.- Show capabilities for a printer.                  |");
                System.out.println("| 3.- Submit a job.                                     |");
                System.out.println("|                                                       |");
                System.out.println("| 0.- Exit program.                                     |");
                System.out.println("---------------------------------------------------------");

                System.out.print("Choose any action: ");
                action = Integer.parseInt(new Scanner(System.in).next());
                System.out.println("");

                switch (action) {
                    case 1:
                    case 2:
                    case 3:
                        printers = searchPrinters();

                        if (action == 2) {
                            System.out.print("Select a printer to request its capabilties: ");
                            int printernum = Integer.parseInt(new Scanner(System.in).next());
                            System.out.println("");

                            PrinterCapabilities caps = requestCapabilities(printers.get(printernum).getPrinterId());
                            if (caps.getMediaSizeList().size() > 0) {
                                System.out.println("Media Sizes:");
                                for (PrinterCapabilities.MediaSize ms : caps.getMediaSizeList()) System.out.println(ms.toString());
                            } else System.out.println("no Media Size info.");

                        } else if (action == 3) {
                            System.out.print("Select a printer to submit the job to: ");
                            int printernum = Integer.parseInt(new Scanner(System.in).next());
                            System.out.println("");
                            if (submitJob(printers.get(printernum).getPrinterId())) System.out.println("Job sent successfully.");
                            else System.out.println("Error while submitting job.");
                        }
                        break;

                    default:
                        System.out.println("Unknown action. Try again.");
                }
                System.out.println("");
            }while (action != 0);

        } else System.out.print("Error: Unauthorized.");
    }

    private static boolean submitJob(String printerid) {
        try {
            Pair<Integer, String> response = submit(oAuth.getAccessToken(), printerid);

            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();
            return object.get("success").getAsBoolean();

        } catch (IOException ex) {
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
            for (int i=0; i< printers.size(); i++) System.out.println("#" + i + " -- " + printers.get(i).toString());

        } catch (IOException ex) {
            System.out.println("IOException.");
        }

        return printers;
    }

    private static PrinterCapabilities requestCapabilities(String printerid) {
        try {
            Pair<Integer, String> response = printer(oAuth.getAccessToken(), printerid);

            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();
            JsonObject retCapabilities = object.getAsJsonArray("printers").get(0).getAsJsonObject().getAsJsonObject("capabilities");

            PrinterCapabilities caps = new PrinterCapabilities();

            // Media Size:
            JsonArray sizes = retCapabilities.getAsJsonObject("printer").getAsJsonObject("media_size").getAsJsonArray("option");
            for (JsonElement je : sizes) {
                JsonObject media_size = je.getAsJsonObject();

                PrinterCapabilities.MediaSize m = caps.new MediaSize();

                m.custom_display_name = (media_size.get("custom_display_name") != null) ? media_size.get("custom_display_name").getAsString() : null;
                m.custom_display_name_localized = (media_size.get("custom_display_name_localized") != null) ? media_size.get("custom_display_name_localized").getAsString() : null;
                m.vendor_id = (media_size.get("vendor_id") != null) ? media_size.get("vendor_id").getAsString() : null;

                m.height_microns = (media_size.get("height_microns") != null) ? media_size.get("height_microns").getAsInt() : 0;
                m.width_microns = (media_size.get("width_microns") != null) ? media_size.get("width_microns").getAsInt() : 0;
                m.is_continuous_feed = (media_size.get("is_continuous_feed") != null) && media_size.get("is_continuous_feed").getAsBoolean();
                m.is_default = (media_size.get("is_default") != null) && media_size.get("is_default").getAsBoolean();

                caps.addMediaSize(m);
            }

            return caps;

        } catch (IOException ex) {
            System.out.println("IOException.");
            return null;
        }
    }
}
