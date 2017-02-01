package com.jmrodrigg.model.CDD;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 7/29/16
 */
public class PrinterDescription {
    public final List<SupportedContentType> supportedContentTypes;
    public final PrintingSpeed printing_speed;
    public final MediaSize mediaSize;
    public final List<Marker> marker;
    public final List<Cover> cover;
    public final List<MediaPath> media_path;
    public final List<VendorCapability> vendor_capability;
    public final Color color;
    public final Copies copies;
    public final Margins margins;

    public PrinterDescription(JsonObject printerDescription) {
        Gson gson = new Gson();

        supportedContentTypes = gson.fromJson(printerDescription.get("supportedContentTypes"), new TypeToken<List<SupportedContentType>>(){}.getType());
        printing_speed = gson.fromJson(printerDescription.get("printing_speed"), PrintingSpeed.class);
        marker = gson.fromJson(printerDescription.get("marker"), new TypeToken<List<Marker>>(){}.getType());
        cover = gson.fromJson(printerDescription.get("cover"), new TypeToken<List<Cover>>(){}.getType());
        media_path = gson.fromJson(printerDescription.get("media_path"), new TypeToken<List<MediaPath>>(){}.getType());
        color = gson.fromJson(printerDescription.get("color"), Color.class);
        vendor_capability = gson.fromJson(printerDescription.get("vendor_capability"), new TypeToken<List<VendorCapability>>(){}.getType());
        copies = gson.fromJson(printerDescription.get("copies"), Copies.class);
        margins = gson.fromJson(printerDescription.get("margins"), Margins.class);
        mediaSize = gson.fromJson(printerDescription.get("mediaSize"), MediaSize.class);
    }

    private PrinterDescription(PrinterDescriptionBuilder builder) {
        this.supportedContentTypes = builder.supported_content_type;
        this.printing_speed = builder.printing_speed;
        this.mediaSize = builder.media_size;
        this.marker = builder.marker_list;
        this.cover = builder.cover_list;
        this.media_path = builder.media_path_list;
        this.vendor_capability = builder.vendor_capability_list;
        this.color = builder.color;
        this.copies = builder.copies;
        this.margins = builder.margins;
    }

    public static class PrinterDescriptionBuilder {
        private List<SupportedContentType> supported_content_type;
        private PrintingSpeed printing_speed;
        private MediaSize media_size;
        private List<Marker> marker_list;
        private List<Cover> cover_list;
        private List<MediaPath> media_path_list;
        private List<VendorCapability> vendor_capability_list;
        private Color color;
        private Copies copies;
        private Margins margins;

        public PrinterDescriptionBuilder supportedContentTypes(List<SupportedContentType> list) {
            this.supported_content_type = list;
            return this;
        }

        public PrinterDescriptionBuilder printingSpeed(PrintingSpeed printing_speed) {
            this.printing_speed = printing_speed;
            return this;
        }

        public PrinterDescriptionBuilder mediaSizes(MediaSize list) {
            this.media_size = list;
            return this;
        }

        public PrinterDescriptionBuilder markers(List<Marker> list) {
            this.marker_list = list;
            return this;
        }

        public PrinterDescriptionBuilder covers(List<Cover> list) {
            this.cover_list = list;
            return this;
        }

        public PrinterDescriptionBuilder mediaPaths(List<MediaPath> list) {
            this.media_path_list = list;
            return this;
        }

        public PrinterDescriptionBuilder vendorCapabilities(List<VendorCapability> list) {
            this.vendor_capability_list = list;
            return this;
        }

        public PrinterDescriptionBuilder colors(Color color) {
            this.color = color;
            return this;
        }

        public PrinterDescriptionBuilder copies(Copies copies) {
            this.copies = copies;
            return this;
        }

        public PrinterDescriptionBuilder margins(Margins margins) {
            this.margins = margins;
            return this;
        }

        public PrinterDescription build() {
            return new PrinterDescription(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if(supportedContentTypes != null) stringBuilder.append(supportedContentTypes.toString()).append("\n");
        if(printing_speed != null) stringBuilder.append(printing_speed.toString()).append("\n");
        if(mediaSize != null) stringBuilder.append(mediaSize.toString()).append("\n");
        if(marker != null) stringBuilder.append(marker.toString()).append("\n");
        if(cover != null) stringBuilder.append(cover.toString()).append("\n");
        if(media_path != null) stringBuilder.append(media_path.toString()).append("\n");
        if(vendor_capability != null) stringBuilder.append(vendor_capability.toString()).append("\n");
        if(color != null) stringBuilder.append(color.toString()).append("\n");
        if(copies != null) stringBuilder.append(copies.toString()).append("\n");
        if(margins != null) stringBuilder.append(margins.toString()).append("\n");

        return stringBuilder.toString();
    }
}