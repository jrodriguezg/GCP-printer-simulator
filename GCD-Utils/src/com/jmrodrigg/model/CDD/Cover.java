package com.jmrodrigg.model.CDD;

import com.jmrodrigg.model.Utils.LocalizedString;

import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 7/29/16
 */
public class Cover {

    public enum Type {
        /**
         * <code>CUSTOM = 0;</code>
         */
        CUSTOM(0, 0),
        /**
         * <code>DOOR = 1;</code>
         */
        DOOR(1, 1),
        /**
         * <code>COVER = 2;</code>
         */
        COVER(2, 2),
        ;

        /**
         * <code>CUSTOM = 0;</code>
         */
        public static final int CUSTOM_VALUE = 0;
        /**
         * <code>DOOR = 1;</code>
         */
        public static final int DOOR_VALUE = 1;
        /**
         * <code>COVER = 2;</code>
         */
        public static final int COVER_VALUE = 2;


        public final int getNumber() { return value; }

        public static Type valueOf(int value) {
            switch (value) {
                case 0: return CUSTOM;
                case 1: return DOOR;
                case 2: return COVER;
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

    public final String vendor_id                                               = null;     /* Required */
    public final Type type                                                      = null;     /* Required */
    public final Long index                                                     = null;
    public final String custom_display_name                                     = null;
    public final List<LocalizedString> custom_display_name_localized            = null;

    @Override
    public String toString() {
        return "Cover{" +
                "vendor_id='" + vendor_id + '\'' +
                ", type=" + type +
                ", index=" + index +
                ", custom_display_name='" + custom_display_name + '\'' +
                ", custom_display_name_localized=" + custom_display_name_localized +
                '}';
    }
}
