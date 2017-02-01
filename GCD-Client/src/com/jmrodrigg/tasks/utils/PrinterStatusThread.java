package com.jmrodrigg.tasks.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Pair;
import org.apache.http.HttpStatus;

import java.io.IOException;

import static com.jmrodrigg.GCPClient.printer;

/**
 * Author: jrodriguezg
 * Date: 1/31/17
 */
public class PrinterStatusThread extends NotifyingThread {

    // The ID of the printer we want to retrieve capabilities from:
    private String printerId;
    private JsonObject printerCapabilities;

    // The token which is required to perform the API calls:
    private String accessToken;

    public synchronized JsonObject getPrinterCapabilties() {
        return this.printerCapabilities;
    }

    public PrinterStatusThread(String printerId, String access_token) {
        this.printerId = printerId;
        printerCapabilities = null;

        setAccessToken(access_token);
    }

    // Access token may be refreshed at any time if it expires.
    // This setter provides a safe way to refresh it in the thread.
    public synchronized void setAccessToken(String access_token) {
        this.accessToken = access_token;
    }

    private synchronized boolean requestCapabilities() throws IOException {
        boolean success = false;

        Pair<Integer, String> response = printer(accessToken, printerId);
        JsonObject object = new JsonParser().parse(response.second).getAsJsonObject();

        if (response.first == HttpStatus.SC_OK) {
            if (object.has("success")) {
                printerCapabilities = object.getAsJsonArray("printers").get(0).getAsJsonObject().getAsJsonObject("capabilities");
                success = true;
            } else {
                if (object.has("error")) System.out.println(response.first + " - Error while requesting printer capabilities --> " + object.get("error").getAsString());
                else System.out.println(response.first + " - Unexpected error while retrieving printer capabilities.");
            }

        } else System.out.println("Error" + response.first + "retrieving printer capabilities --> " + response.second);

        return success;
    }

    public void doRun() {
        try {
            do {
                try {
                    requestCapabilities();
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    // TODO ignore.
                }
            } while(true);
        } catch (IOException ex) {
            System.out.println("IOException.");
        }
    }
}
