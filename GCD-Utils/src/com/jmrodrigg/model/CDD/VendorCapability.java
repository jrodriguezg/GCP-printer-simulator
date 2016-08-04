package com.jmrodrigg.model.CDD;

/**
 * Author: jrodriguezg
 * Date: 7/29/16
 */
public class VendorCapability {

    public enum Type {
        /**
         * <code>RANGE = 0;</code>
         */
        RANGE(0, 0),
        /**
         * <code>SELECT = 1;</code>
         */
        SELECT(1, 1),
        /**
         * <code>TYPED_VALUE = 2;</code>
         */
        TYPED_VALUE(2, 2),
        ;

        /**
         * <code>RANGE = 0;</code>
         */
        public static final int RANGE_VALUE = 0;
        /**
         * <code>SELECT = 1;</code>
         */
        public static final int SELECT_VALUE = 1;
        /**
         * <code>TYPED_VALUE = 2;</code>
         */
        public static final int TYPED_VALUE_VALUE = 2;


        public final int getNumber() { return value; }

        public static Type valueOf(int value) {
            switch (value) {
                case 0: return RANGE;
                case 1: return SELECT;
                case 2: return TYPED_VALUE;
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

    public final String id                  = null;
    public final String Type                = null;
    public final Type type                  = null;

    // TODO

}
