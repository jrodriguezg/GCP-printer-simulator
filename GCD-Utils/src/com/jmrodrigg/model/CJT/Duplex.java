package com.jmrodrigg.model.CJT;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class Duplex {

    public enum Type {
        /**
         * <code>NO_DUPLEX = 0;</code>
         */
        NO_DUPLEX(0, 0),
        /**
         * <code>LONG_EDGE = 1;</code>
         */
        LONG_EDGE(1, 1),
        /**
         * <code>SHORT_EDGE = 2;</code>
         */
        SHORT_EDGE(2, 2),
        ;

        /**
         * <code>NO_DUPLEX = 0;</code>
         */
        public static final int NO_DUPLEX_VALUE = 0;
        /**
         * <code>LONG_EDGE = 1;</code>
         */
        public static final int LONG_EDGE_VALUE = 1;
        /**
         * <code>SHORT_EDGE = 2;</code>
         */
        public static final int SHORT_EDGE_VALUE = 2;


        public final int getNumber() { return value; }

        public static Type valueOf(int value) {
            switch (value) {
                case 0: return NO_DUPLEX;
                case 1: return LONG_EDGE;
                case 2: return SHORT_EDGE;
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

    public final Type type                         = null;

    @Override
    public String toString() {
        return "Duplex{" +
                "type=" + type +
                '}';
    }
}
