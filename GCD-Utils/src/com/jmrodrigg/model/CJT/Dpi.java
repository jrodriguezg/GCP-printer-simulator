package com.jmrodrigg.model.CJT;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class Dpi {

    public Integer horizontal_dpi                 = null;
    public Integer vertical_dpi                   = null;
    public String vendor_id                       = null;

    @Override
    public String toString() {
        return "dpi{" +
                "horizontal_dpi=" + horizontal_dpi +
                ", vertical_dpi=" + vertical_dpi +
                ", vendor_id='" + vendor_id + '\'' +
                '}';
    }
}
