package com.jmrodrigg.model.CDD;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: jrodriguez
 * Date: 29/07/16
 */
public class Margins {

    public enum Type {
        /**
         * <code>BORDERLESS = 0;</code>
         */
        BORDERLESS(0, 0),
        /**
         * <code>STANDARD = 1;</code>
         */
        STANDARD(1, 1),
        /**
         * <code>CUSTOM = 2;</code>
         */
        CUSTOM(2, 2),
        ;

        /**
         * <code>BORDERLESS = 0;</code>
         */
        public static final int BORDERLESS_VALUE = 0;
        /**
         * <code>STANDARD = 1;</code>
         */
        public static final int STANDARD_VALUE = 1;
        /**
         * <code>CUSTOM = 2;</code>
         */
        public static final int CUSTOM_VALUE = 2;


        public final int getNumber() { return value; }

        public static Type valueOf(int value) {
            switch (value) {
                case 0: return BORDERLESS;
                case 1: return STANDARD;
                case 2: return CUSTOM;
                default: return null;
            }
        }

        private static final Type[] VALUES = values();

        private final int index;
        private final int value;

        private Type(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public class Option {
        @SerializedName("type")
        public final Type margin_type                   = null;     /* Required */
        public final Integer top_microns                = null;     /* Required */
        public final Integer right_microns              = null;     /* Required */
        public final Integer bottom_microns             = null;     /* Required */
        public final Integer left_microns               = null;     /* Required */
        public final Boolean is_default                 = false; /* default = FALSE */

        @Override
        public String toString() {
            return "Option{" +
                    "margin_type=" + margin_type +
                    ", top_microns=" + top_microns +
                    ", right_microns=" + right_microns +
                    ", bottom_microns=" + bottom_microns +
                    ", left_microns=" + left_microns +
                    ", is_default=" + is_default +
                    '}';
        }
    }

    public final List<Option> option            = null;

    @Override
    public String toString() {
        return "Margins{" +
                "option=" + option.toString() +
                '}';
    }
}
