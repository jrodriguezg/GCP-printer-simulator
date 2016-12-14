package com.jmrodrigg.model.CJT;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class MediaSize {

    public Integer width_microns              = null;
    public Integer height_microns             = null;
    public Boolean is_continuous_feed         = false; /* default = FALSE */
    public String vendor_id                   = null;

    @Override
    public String toString() {
        return "MediaSize{" +
                "width_microns=" + width_microns +
                ", height_microns=" + height_microns +
                ", is_continuous_feed=" + is_continuous_feed +
                ", vendor_id='" + vendor_id + '\'' +
                '}';
    }
}
