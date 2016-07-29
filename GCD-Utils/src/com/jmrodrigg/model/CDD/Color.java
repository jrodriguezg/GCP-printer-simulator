package com.jmrodrigg.model.CDD;

import com.jmrodrigg.model.Utils.LocalizedString;

import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 7/29/16
 */
public class Color {

    public enum Type {
        /**
         * <code>STANDARD_COLOR = 0;</code>
         */
        STANDARD_COLOR(0, 0),
        /**
         * <code>STANDARD_MONOCHROME = 1;</code>
         */
        STANDARD_MONOCHROME(1, 1),
        /**
         * <code>CUSTOM_COLOR = 2;</code>
         */
        CUSTOM_COLOR(2, 2),
        /**
         * <code>CUSTOM_MONOCHROME = 3;</code>
         */
        CUSTOM_MONOCHROME(3, 3),
        /**
         * <code>AUTO = 4;</code>
         */
        AUTO(4, 4),
        ;

        /**
         * <code>STANDARD_COLOR = 0;</code>
         */
        public static final int STANDARD_COLOR_VALUE = 0;
        /**
         * <code>STANDARD_MONOCHROME = 1;</code>
         */
        public static final int STANDARD_MONOCHROME_VALUE = 1;
        /**
         * <code>CUSTOM_COLOR = 2;</code>
         */
        public static final int CUSTOM_COLOR_VALUE = 2;
        /**
         * <code>CUSTOM_MONOCHROME = 3;</code>
         */
        public static final int CUSTOM_MONOCHROME_VALUE = 3;
        /**
         * <code>AUTO = 4;</code>
         */
        public static final int AUTO_VALUE = 4;


        public final int getNumber() { return value; }

        public static Type valueOf(int value) {
            switch (value) {
                case 0: return STANDARD_COLOR;
                case 1: return STANDARD_MONOCHROME;
                case 2: return CUSTOM_COLOR;
                case 3: return CUSTOM_MONOCHROME;
                case 4: return AUTO;
                default: return null;
            }
        }

        private final int index;
        private final int value;

        private Type(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public class Option {
        public final String vendor_id                                           = null;
        public final Type type                                                  = null;
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
