package com.jmrodrigg.model.CDD;

import com.jmrodrigg.model.Utils.LocalizedString;
import com.jmrodrigg.model.Utils.PrintingColor;

import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 7/29/16
 */
public class Color {

    public class Option {
        public final String vendor_id                                           = null;
        public final PrintingColor.Type type                                    = null;
        public final String custom_display_name                                 = null;
        public final List<LocalizedString> custom_display_name_localized        = null;

        @Override
        public String toString() {
            return "Option{" +
                    "vendor_id='" + vendor_id + '\'' +
                    ", type=" + type +
                    ", custom_display_name='" + custom_display_name + '\'' +
                    ", custom_display_name_localized=" + custom_display_name_localized +
                    '}';
        }
    }

    public final List<Option> option            = null;

    @Override
    public String toString() {
        return "Color{" +
                "option=" + option.toString() +
                '}';
    }
}
