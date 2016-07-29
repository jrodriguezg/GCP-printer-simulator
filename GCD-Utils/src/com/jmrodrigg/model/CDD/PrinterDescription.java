package com.jmrodrigg.model.CDD;

import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 7/29/16
 */
public class PrinterDescription {
    public final List<SupportedContentType> supported_content_type;
    public final PrintingSpeed printing_speed;
    public final MediaSize media_size;

    public final List<Marker> marker;
    public final List<Cover> cover;
    public final List<MediaPath> media_path;
    public final List<VendorCapability> vendor_capability;
    public final Color color;
    public final Copies copies;

    private PrinterDescription(PrinterDescriptionBuilder builder) {
        this.supported_content_type = builder.supported_content_type;
        this.printing_speed = builder.printing_speed;
        this.media_size = builder.media_size;
        this.marker = builder.marker_list;
        this.cover = builder.cover_list;
        this.media_path = builder.media_path_list;
        this.vendor_capability = builder.vendor_capability_list;
        this.color = builder.color;
        this.copies = builder.copies;
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

        public PrinterDescription build() {
            return new PrinterDescription(this);
        }
    }
}
