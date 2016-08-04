package com.jmrodrigg.model.CDD;

import com.jmrodrigg.model.Utils.MarkerColor;
import com.jmrodrigg.model.Utils.LocalizedString;

import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 7/29/16
 */
public class Marker {

    public enum Type {
        /**
         * <code>CUSTOM = 0;</code>
         */
        CUSTOM(0, 0),
        /**
         * <code>TONER = 1;</code>
         */
        TONER(1, 1),
        /**
         * <code>INK = 2;</code>
         */
        INK(2, 2),
        /**
         * <code>STAPLES = 3;</code>
         */
        STAPLES(3, 3),
        ;

        /**
         * <code>CUSTOM = 0;</code>
         */
        public static final int CUSTOM_VALUE = 0;
        /**
         * <code>TONER = 1;</code>
         */
        public static final int TONER_VALUE = 1;
        /**
         * <code>INK = 2;</code>
         */
        public static final int INK_VALUE = 2;
        /**
         * <code>STAPLES = 3;</code>
         */
        public static final int STAPLES_VALUE = 3;


        public final int getNumber() { return value; }

        public static Type valueOf(int value) {
            switch (value) {
                case 0: return CUSTOM;
                case 1: return TONER;
                case 2: return INK;
                case 3: return STAPLES;
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

    public final String vendor_id                                            = null;     /* Required */
    public final Type type                                                   = null;     /* Required */
    public final MarkerColor color                                           = null;
    public final String custom_display_name                                  = null;
    public final List<LocalizedString> custom_display_name_localized         = null;

    @Override
    public String toString() {
        return "Marker{" +
                "vendor_id='" + vendor_id + '\'' +
                ", type=" + type +
                ", color=" + color.toString() +
                ", custom_display_name='" + custom_display_name + '\'' +
                ", custom_display_name_localized=" + custom_display_name_localized +
                '}';
    }
}