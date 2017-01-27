package com.jmrodrigg;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.jmrodrigg.model.CDD.*;

import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 1/26/17
 */
public class PrivetPrinter {

    private PrinterDescription printerDescription;

    public PrivetPrinter(PrinterDescription printerDescription) {
        this.printerDescription = printerDescription;
    }

    public PrinterDescription getPrinterDescription() {
        return printerDescription;
    }

    public static PrinterDescription setPrinterDescription(JsonObject printerDescriptionJson) {
        Gson gson = new Gson();

        List<SupportedContentType> supported_content_types = gson.fromJson(printerDescriptionJson.get("supported_content_type"), new TypeToken<List<SupportedContentType>>(){}.getType());
        PrintingSpeed printing_speed = gson.fromJson(printerDescriptionJson.get("printing_speed"), PrintingSpeed.class);
        List<Marker> marker = gson.fromJson(printerDescriptionJson.get("marker"), new TypeToken<List<Marker>>(){}.getType());
        List<Cover> covers = gson.fromJson(printerDescriptionJson.get("cover"), new TypeToken<List<Cover>>(){}.getType());
        List<MediaPath> media_paths = gson.fromJson(printerDescriptionJson.get("media_path"), new TypeToken<List<MediaPath>>(){}.getType());
        Color color = gson.fromJson(printerDescriptionJson.get("color"), Color.class);
        List<VendorCapability> vendor_capabilities = gson.fromJson(printerDescriptionJson.get("vendor_capability"), new TypeToken<List<VendorCapability>>(){}.getType());
        Copies copies = gson.fromJson(printerDescriptionJson.get("copies"), Copies.class);
        Margins margins = gson.fromJson(printerDescriptionJson.get("margins"), Margins.class);
        MediaSize media_size = gson.fromJson(printerDescriptionJson.get("media_size"), MediaSize.class);

        return new PrinterDescription.PrinterDescriptionBuilder()
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
}
