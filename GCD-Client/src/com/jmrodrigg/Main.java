package com.jmrodrigg;

import com.google.gson.*;
import com.google.gson.internal.Pair;
import com.google.gson.reflect.TypeToken;
import com.jmrodrigg.model.CDD.*;

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

                            PrinterDescription printer_description = requestCapabilities(printers.get(printernum).getPrinterId());
                            if (printer_description != null) {
                                if(printer_description.supported_content_type != null) System.out.println(printer_description.supported_content_type.toString());
                                if(printer_description.media_size != null) System.out.println(printer_description.media_size.toString());
                                if(printer_description.marker != null) System.out.println(printer_description.marker.toString());
                                if(printer_description.color != null) System.out.println(printer_description.color.toString());
                                if(printer_description.copies != null) System.out.println(printer_description.copies.toString());
                                if(printer_description.cover != null) System.out.println(printer_description.cover.toString());
                                if(printer_description.margins != null) System.out.println(printer_description.margins.toString());
                            }
                        } else if (action == 3) {
                            System.out.print("Select a printer to submit the job to: ");
                            int printernum = Integer.parseInt(new Scanner(System.in).next());
                            System.out.println("");

                            System.out.print("Which type of job: (P)DF or (J)PEG? ");
                            String job_type = new Scanner(System.in).next();
                            System.out.println("");

                            if (submitJob(printers.get(printernum).getPrinterId(),job_type)) System.out.println("Job sent successfully.");
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

    private static boolean submitJob(String printerid, String jobType) {
        try {
            Pair<Integer, String> response = submit(oAuth.getAccessToken(), printerid, jobType);

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
            for (int i=0; i< printers.size(); i++) System.out.println("#" + i + " -- " + printers.get(i).toString());

        } catch (IOException ex) {
            System.out.println("IOException.");
        }

        return printers;
    }

    private static PrinterDescription requestCapabilities(String printerid) {
        try {
            Pair<Integer, String> response = printer(oAuth.getAccessToken(), printerid);

            JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();
            JsonObject retCapabilities = object.getAsJsonArray("printers").get(0).getAsJsonObject().getAsJsonObject("capabilities");

            Gson gson = new Gson();
            // Supported content types:
            List<SupportedContentType> supported_content_types = gson.fromJson(retCapabilities.getAsJsonObject("printer").get("supported_content_type"), new TypeToken<List<SupportedContentType>>(){}.getType());
            // Media Size:
            MediaSize media_size = gson.fromJson(retCapabilities.getAsJsonObject("printer").get("media_size"), MediaSize.class);
            // Marker:
            List<Marker> marker = gson.fromJson(retCapabilities.getAsJsonObject("printer").get("marker"), new TypeToken<List<Marker>>(){}.getType());
            // Color:
            Color color = gson.fromJson(retCapabilities.getAsJsonObject("printer").get("color"), Color.class);
            // Copies:
            Copies copies = gson.fromJson(retCapabilities.getAsJsonObject("printer").get("copies"), Copies.class);
            // Cover:
            List<Cover> covers = gson.fromJson(retCapabilities.getAsJsonObject("printer").get("cover"), new TypeToken<List<Cover>>(){}.getType());
            // Cover:
            Margins margins = gson.fromJson(retCapabilities.getAsJsonObject("printer").get("margins"), Margins.class);

            return new PrinterDescription.PrinterDescriptionBuilder()
                            .supportedContentTypes(supported_content_types)
                            .mediaSizes(media_size)
                            .markers(marker)
                            .colors(color)
                            .copies(copies)
                            .covers(covers)
                            .margins(margins)
                            .build();
        } catch (IOException ex) {
            System.out.println("IOException.");
            return null;
        }
    }
}
