package com.jmrodrigg.model.CJT;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class MediaSize {

    public final Integer width_microns              = null;
    public final Integer height_microns             = null;
    public final Boolean is_contunuous_feed         = false; /* default = FALSE */
    public final String vendor_id                   = null;

    @Override
    public String toString() {
        return "MediaSize{" +
                "width_microns=" + width_microns +
                ", height_microns=" + height_microns +
                ", is_contunuous_feed=" + is_contunuous_feed +
                ", vendor_id='" + vendor_id + '\'' +
                '}';
    }
}
