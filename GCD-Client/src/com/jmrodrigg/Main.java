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

import static com.jmrodrigg.GCPClient.search;

/**
 * Author: jrodriguezg
 * Date: 7/15/16
 */
public class Main {

    static List<Printer> printers;

    private static OAuth oAuth = new OAuth(Main.class.getClassLoader().getResourceAsStream("keys/api_key.json"));

    public static void main(String[] args) {
        System.out.print("Type your email address: ");
        String username = new Scanner(System.in).next();
        System.out.print("");

        printers = new ArrayList<>();

        if(oAuth.authorize(username)) {

            do {
                System.out.println("");
                System.out.println("-------------------------Actions-------------------------");
                System.out.println("---------------------------------------------------------");
                System.out.println("| 1.- List printers.                                    |");
                System.out.println("---------------------------------------------------------");

                System.out.print("Choose any action: ");
                int action = Integer.parseInt(new Scanner(System.in).next());
                System.out.println("");

                switch (action) {
                    case 1:
                        searchPrinters();
                        break;
                    default:
                        System.out.println("Unknown action. Try again.");
                }


                System.out.println("");
            }while (true);

        } else System.out.print("Error: Unauthorized.");
    }

    private static void searchPrinters() {
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
            System.out.print("IOException.");
        }
    }
}
