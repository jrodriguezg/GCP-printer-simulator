package com.jmrodrigg;

import com.google.gson.JsonObject;
import com.jmrodrigg.model.CDD.*;

/**
 * Author: jrodriguezg
 * Date: 7/18/16
 */
public class Printer {

    private String printerId;
    private String status;
    private String displayName;

    private PrinterDescription printerDescription;

    public Printer(JsonObject object) {
        this.printerId = object.getAsJsonObject().get("id").getAsString();
        this.status = object.getAsJsonObject().get("connectionStatus").getAsString();
        this.displayName = object.getAsJsonObject().get("displayName").getAsString();
        this.printerDescription = null;
    }

    public String getPrinterId() {
        return printerId;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[").append(status).append("] - ").append(displayName).append(" {").append(printerId).append("}");

        if (printerDescription != null) stringBuilder.append("\n").append(printerDescription.toString());

        return stringBuilder.toString();
    }

    public void setPrinterDescription(JsonObject printerDescription) {
        this.printerDescription = new PrinterDescription(printerDescription);
    }

    public PrinterDescription getPrinterDescription() {
        return printerDescription;
    }
}
