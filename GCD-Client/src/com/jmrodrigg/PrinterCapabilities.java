package com.jmrodrigg;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 7/18/16
 */
public class PrinterCapabilities {

    private List<MediaSize> mediaSizeList;

    public PrinterCapabilities() {
        this.mediaSizeList = new ArrayList<>();
    }

    public void addMediaSize(MediaSize mediaSize) {
        mediaSizeList.add(mediaSize);
    }

    public List<MediaSize> getMediaSizeList() {
        return mediaSizeList;
    }

    public class MediaSize {

        public int width_microns;
        public int height_microns;
        public boolean is_continuous_feed = false;
        public boolean is_default = false;

        public String custom_display_name;
        public String vendor_id;
        public String custom_display_name_localized;

        @Override
        public String toString() {
            return "{name: " + ((custom_display_name_localized != null) ? custom_display_name_localized : custom_display_name)
                    + ((width_microns > 0) ? (", width:" + width_microns) : (""))
                    + ((height_microns > 0) ? (", height:" + height_microns) : (""))
                    + ", is_continuous: " + is_continuous_feed
                    + ((vendor_id != null) ? (", " + vendor_id) : (""))
                    + ", is_default: " + is_default
                + "}";
        }
    }
}
