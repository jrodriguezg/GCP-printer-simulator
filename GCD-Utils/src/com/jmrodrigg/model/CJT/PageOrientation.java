package com.jmrodrigg.model.CJT;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class PageOrientation {

    public enum Type {
        /**
         * <code>PORTRAIT = 0;</code>
         */
        PORTRAIT(0, 0),
        /**
         * <code>LANDSCAPE = 1;</code>
         */
        LANDSCAPE(1, 1),
        /**
         * <code>AUTO = 2;</code>
         */
        AUTO(2, 2),
        ;

        /**
         * <code>PORTRAIT = 0;</code>
         */
        public static final int PORTRAIT_VALUE = 0;
        /**
         * <code>LANDSCAPE = 1;</code>
         */
        public static final int LANDSCAPE_VALUE = 1;
        /**
         * <code>AUTO = 2;</code>
         */
        public static final int AUTO_VALUE = 2;


        public final int getNumber() { return value; }

        public static Type valueOf(int value) {
            switch (value) {
                case 0: return PORTRAIT;
                case 1: return LANDSCAPE;
                case 2: return AUTO;
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
        return "PageOrientation{" +
                "type=" + type +
                '}';
    }

}
