package com.jmrodrigg.model.CDD;

import com.jmrodrigg.model.Utils.PrintingColor;

import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 7/29/16
 */
public class PrintingSpeed {

    public class Option {
        public final Float speed_ppm                                = null;     /* Required */
        public final List<PrintingColor.Type> color_type            = null;
        public final List<MediaSize.Name> media_size_name           = null;

        @Override
        public String toString() {
            return "Option{" +
                    "speed_ppm=" + speed_ppm +
                    ", color_type=" + color_type +
                    ", media_size_name=" + media_size_name +
                    '}';
        }
    }

    public final List<Option> option            = null;

    @Override
    public String toString() {
        return "PrintingSpeed{" +
                "option=" + option.toString() +
                '}';
    }
}
