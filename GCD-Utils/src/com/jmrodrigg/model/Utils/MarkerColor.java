package com.jmrodrigg.model.Utils;

import java.util.List;

/**
 * Created by jrodriguezg on 7/29/16.
 */
public class MarkerColor {
    public enum Type {
        /**
         * <code>CUSTOM = 0;</code>
         */
        CUSTOM(0, 0),
        /**
         * <code>BLACK = 1;</code>
         */
        BLACK(1, 1),
        /**
         * <code>COLOR = 2;</code>
         */
        COLOR(2, 2),
        /**
         * <code>CYAN = 3;</code>
         */
        CYAN(3, 3),
        /**
         * <code>MAGENTA = 4;</code>
         */
        MAGENTA(4, 4),
        /**
         * <code>YELLOW = 5;</code>
         */
        YELLOW(5, 5),
        /**
         * <code>LIGHT_CYAN = 6;</code>
         */
        LIGHT_CYAN(6, 6),
        /**
         * <code>LIGHT_MAGENTA = 7;</code>
         */
        LIGHT_MAGENTA(7, 7),
        /**
         * <code>GRAY = 8;</code>
         */
        GRAY(8, 8),
        /**
         * <code>LIGHT_GRAY = 9;</code>
         */
        LIGHT_GRAY(9, 9),
        /**
         * <code>PIGMENT_BLACK = 10;</code>
         */
        PIGMENT_BLACK(10, 10),
        /**
         * <code>MATTE_BLACK = 11;</code>
         */
        MATTE_BLACK(11, 11),
        /**
         * <code>PHOTO_CYAN = 12;</code>
         */
        PHOTO_CYAN(12, 12),
        /**
         * <code>PHOTO_MAGENTA = 13;</code>
         */
        PHOTO_MAGENTA(13, 13),
        /**
         * <code>PHOTO_YELLOW = 14;</code>
         */
        PHOTO_YELLOW(14, 14),
        /**
         * <code>PHOTO_GRAY = 15;</code>
         */
        PHOTO_GRAY(15, 15),
        /**
         * <code>RED = 16;</code>
         */
        RED(16, 16),
        /**
         * <code>GREEN = 17;</code>
         */
        GREEN(17, 17),
        /**
         * <code>BLUE = 18;</code>
         */
        BLUE(18, 18),
        ;

        /**
         * <code>CUSTOM = 0;</code>
         */
        public static final int CUSTOM_VALUE = 0;
        /**
         * <code>BLACK = 1;</code>
         */
        public static final int BLACK_VALUE = 1;
        /**
         * <code>COLOR = 2;</code>
         */
        public static final int COLOR_VALUE = 2;
        /**
         * <code>CYAN = 3;</code>
         */
        public static final int CYAN_VALUE = 3;
        /**
         * <code>MAGENTA = 4;</code>
         */
        public static final int MAGENTA_VALUE = 4;
        /**
         * <code>YELLOW = 5;</code>
         */
        public static final int YELLOW_VALUE = 5;
        /**
         * <code>LIGHT_CYAN = 6;</code>
         */
        public static final int LIGHT_CYAN_VALUE = 6;
        /**
         * <code>LIGHT_MAGENTA = 7;</code>
         */
        public static final int LIGHT_MAGENTA_VALUE = 7;
        /**
         * <code>GRAY = 8;</code>
         */
        public static final int GRAY_VALUE = 8;
        /**
         * <code>LIGHT_GRAY = 9;</code>
         */
        public static final int LIGHT_GRAY_VALUE = 9;
        /**
         * <code>PIGMENT_BLACK = 10;</code>
         */
        public static final int PIGMENT_BLACK_VALUE = 10;
        /**
         * <code>MATTE_BLACK = 11;</code>
         */
        public static final int MATTE_BLACK_VALUE = 11;
        /**
         * <code>PHOTO_CYAN = 12;</code>
         */
        public static final int PHOTO_CYAN_VALUE = 12;
        /**
         * <code>PHOTO_MAGENTA = 13;</code>
         */
        public static final int PHOTO_MAGENTA_VALUE = 13;
        /**
         * <code>PHOTO_YELLOW = 14;</code>
         */
        public static final int PHOTO_YELLOW_VALUE = 14;
        /**
         * <code>PHOTO_GRAY = 15;</code>
         */
        public static final int PHOTO_GRAY_VALUE = 15;
        /**
         * <code>RED = 16;</code>
         */
        public static final int RED_VALUE = 16;
        /**
         * <code>GREEN = 17;</code>
         */
        public static final int GREEN_VALUE = 17;
        /**
         * <code>BLUE = 18;</code>
         */
        public static final int BLUE_VALUE = 18;


        public final int getNumber() { return value; }

        public static Type valueOf(int value) {
            switch (value) {
                case 0: return CUSTOM;
                case 1: return BLACK;
                case 2: return COLOR;
                case 3: return CYAN;
                case 4: return MAGENTA;
                case 5: return YELLOW;
                case 6: return LIGHT_CYAN;
                case 7: return LIGHT_MAGENTA;
                case 8: return GRAY;
                case 9: return LIGHT_GRAY;
                case 10: return PIGMENT_BLACK;
                case 11: return MATTE_BLACK;
                case 12: return PHOTO_CYAN;
                case 13: return PHOTO_MAGENTA;
                case 14: return PHOTO_YELLOW;
                case 15: return PHOTO_GRAY;
                case 16: return RED;
                case 17: return GREEN;
                case 18: return BLUE;
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

    public final Type type                                               = null;     /* Required */
    public final String custom_display_name                              = null;
    public final List<LocalizedString> custom_display_name_localized     = null;
}
