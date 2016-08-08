package com.jmrodrigg.model.CJT;

import com.jmrodrigg.model.Utils.PrintingColor;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class Color {

    public final String vendor_id                                           = null;
    public final PrintingColor.Type type                                    = null;

    @Override
    public String toString() {
        return "Color{" +
                "vendor_id='" + vendor_id + '\'' +
                ", type=" + type +
                '}';
    }
}
