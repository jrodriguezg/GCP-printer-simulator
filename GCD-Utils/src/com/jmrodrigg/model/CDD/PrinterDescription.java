package com.jmrodrigg.model.CDD;

import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 7/29/16
 */
public class PrinterDescription {
    public final List<SupportedContentType> supported_content_type_list;
    public final PrintingSpeed printing_speed;
    public final MediaSize media_size;

    public final List<Marker> marker_list;

    private PrinterDescription(PrinterDescriptionBuilder builder) {
        this.supported_content_type_list = builder.supported_content_type;
        this.printing_speed = builder.printing_speed;
        this.media_size = builder.media_size;
        this.marker_list = builder.marker_list;
    }

    public static class PrinterDescriptionBuilder {
        private List<SupportedContentType> supported_content_type;
        private PrintingSpeed printing_speed;
        private MediaSize media_size;
        private List<Marker> marker_list;

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

        public PrinterDescription build() {
            return new PrinterDescription(this);
        }
    }
}
