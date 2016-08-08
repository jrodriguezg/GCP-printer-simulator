package com.jmrodrigg.model.CJT;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class FitToPage {

    public enum Type {
        /**
         * <code>NO_FITTING = 0;</code>
         */
        NO_FITTING(0, 0),
        /**
         * <code>FIT_TO_PAGE = 1;</code>
         */
        FIT_TO_PAGE(1, 1),
        /**
         * <code>GROW_TO_PAGE = 2;</code>
         */
        GROW_TO_PAGE(2, 2),
        /**
         * <code>SHRINK_TO_PAGE = 3;</code>
         */
        SHRINK_TO_PAGE(3, 3),
        /**
         * <code>FILL_PAGE = 4;</code>
         */
        FILL_PAGE(4, 4),
        ;

        /**
         * <code>NO_FITTING = 0;</code>
         */
        public static final int NO_FITTING_VALUE = 0;
        /**
         * <code>FIT_TO_PAGE = 1;</code>
         */
        public static final int FIT_TO_PAGE_VALUE = 1;
        /**
         * <code>GROW_TO_PAGE = 2;</code>
         */
        public static final int GROW_TO_PAGE_VALUE = 2;
        /**
         * <code>SHRINK_TO_PAGE = 3;</code>
         */
        public static final int SHRINK_TO_PAGE_VALUE = 3;
        /**
         * <code>FILL_PAGE = 4;</code>
         */
        public static final int FILL_PAGE_VALUE = 4;


        public final int getNumber() { return value; }

        public static Type valueOf(int value) {
            switch (value) {
                case 0: return NO_FITTING;
                case 1: return FIT_TO_PAGE;
                case 2: return GROW_TO_PAGE;
                case 3: return SHRINK_TO_PAGE;
                case 4: return FILL_PAGE;
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
        return "FitToPage{" +
                "type=" + type +
                '}';
    }
}
