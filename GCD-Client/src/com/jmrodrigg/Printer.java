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

    public Printer(JsonObject object) {
        this.printerid = object.getAsJsonObject().get("id").getAsString();
        this.status = object.getAsJsonObject().get("connectionStatus").getAsString();
        this.displayName = object.getAsJsonObject().get("displayName").getAsString();
        this.printerDescription = null;
    }

    public String getPrinterId() {
        return printerid;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[" + status + "] - " + displayName + " {" + printerid + "}");

        if (printerDescription != null) stringBuilder.append("\n" + printerDescription.toString());

        return stringBuilder.toString();
    }

    public void setPrinterDescription(JsonObject printerDescription) {
        Gson gson = new Gson();

        List<SupportedContentType> supported_content_types = gson.fromJson(printerDescription.get("supported_content_type"), new TypeToken<List<SupportedContentType>>(){}.getType());
        PrintingSpeed printing_speed = gson.fromJson(printerDescription.get("printing_speed"), PrintingSpeed.class);
        List<Marker> marker = gson.fromJson(printerDescription.get("marker"), new TypeToken<List<Marker>>(){}.getType());
        List<Cover> covers = gson.fromJson(printerDescription.get("cover"), new TypeToken<List<Cover>>(){}.getType());
        List<MediaPath> media_paths = gson.fromJson(printerDescription.get("media_path"), new TypeToken<List<MediaPath>>(){}.getType());
        Color color = gson.fromJson(printerDescription.get("color"), Color.class);
        List<VendorCapability> vendor_capabilities = gson.fromJson(printerDescription.get("vendor_capability"), new TypeToken<List<VendorCapability>>(){}.getType());
        Copies copies = gson.fromJson(printerDescription.get("copies"), Copies.class);
        Margins margins = gson.fromJson(printerDescription.get("margins"), Margins.class);
        MediaSize media_size = gson.fromJson(printerDescription.get("media_size"), MediaSize.class);

        this.printerDescription = new PrinterDescription.PrinterDescriptionBuilder()
                .supportedContentTypes(supported_content_types)
                .printingSpeed(printing_speed)
                .markers(marker)
                .covers(covers)
                .mediaPaths(media_paths)
                .colors(color)
                .vendorCapabilities(vendor_capabilities)
                .copies(copies)
                .margins(margins)
                .mediaSizes(media_size)
                .build();
    }

    public PrinterDescription getPrinterDescription() {
        return printerDescription;
    }
}
