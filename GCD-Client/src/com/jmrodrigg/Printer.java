package com.jmrodrigg;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.jmrodrigg.model.CDD.*;

import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 7/18/16
 */
public class Printer {

    private String printerid;
    private String status;
    private String displayName;

    private PrinterDescription printerDescription;

    public Printer(String printerid, String status, String displayName) {
        this.printerid = printerid;
        this.status = status;
        this.displayName = displayName;
        this.printerDescription = null;
    }

    public String getPrinterId() {
        return printerid;
    }

    @Override
    public String toString() {
        return "[" + status + "] - " + displayName + " {" + printerid + "}" +
                printerDescription.toString();
    }

    public void setPrinterDescription(JsonObject printerDescription) {
        Gson gson = new Gson();
        // Supported content types:
        List<SupportedContentType> supported_content_types = gson.fromJson(printerDescription.get("supported_content_type"), new TypeToken<List<SupportedContentType>>(){}.getType());
        // Media Size:
        MediaSize media_size = gson.fromJson(printerDescription.get("media_size"), MediaSize.class);
        // Marker:
        List<Marker> marker = gson.fromJson(printerDescription.get("marker"), new TypeToken<List<Marker>>(){}.getType());
        // Color:
        Color color = gson.fromJson(printerDescription.get("color"), Color.class);
        // Copies:
        Copies copies = gson.fromJson(printerDescription.get("copies"), Copies.class);
        // Cover:
        List<Cover> covers = gson.fromJson(printerDescription.get("cover"), new TypeToken<List<Cover>>(){}.getType());
        // Margins:
        Margins margins = gson.fromJson(printerDescription.get("margins"), Margins.class);

        this.printerDescription = new PrinterDescription.PrinterDescriptionBuilder()
                .supportedContentTypes(supported_content_types)
                .mediaSizes(media_size)
                .markers(marker)
                .colors(color)
                .copies(copies)
                .covers(covers)
                .margins(margins)
                .build();
    }

    public PrinterDescription getPrinterDescription() {
        return printerDescription;
    }
}
