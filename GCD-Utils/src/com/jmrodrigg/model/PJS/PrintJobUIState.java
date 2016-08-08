package com.jmrodrigg.model.PJS;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class PrintJobUIState {

    public enum Summary {
        /**
         * <code>DRAFT = 0;</code>
         */
        DRAFT(0, 0),
        /**
         * <code>QUEUED = 1;</code>
         */
        QUEUED(1, 1),
        /**
         * <code>IN_PROGRESS = 2;</code>
         */
        IN_PROGRESS(2, 2),
        /**
         * <code>PAUSED = 3;</code>
         */
        PAUSED(3, 3),
        /**
         * <code>DONE = 4;</code>
         */
        DONE(4, 4),
        /**
         * <code>CANCELLED = 5;</code>
         */
        CANCELLED(5, 5),
        /**
         * <code>ERROR = 6;</code>
         */
        ERROR(6, 6),
        /**
         * <code>EXPIRED = 7;</code>
         */
        EXPIRED(7, 7),
        ;

        /**
         * <code>DRAFT = 0;</code>
         */
        public static final int DRAFT_VALUE = 0;
        /**
         * <code>QUEUED = 1;</code>
         */
        public static final int QUEUED_VALUE = 1;
        /**
         * <code>IN_PROGRESS = 2;</code>
         */
        public static final int IN_PROGRESS_VALUE = 2;
        /**
         * <code>PAUSED = 3;</code>
         */
        public static final int PAUSED_VALUE = 3;
        /**
         * <code>DONE = 4;</code>
         */
        public static final int DONE_VALUE = 4;
        /**
         * <code>CANCELLED = 5;</code>
         */
        public static final int CANCELLED_VALUE = 5;
        /**
         * <code>ERROR = 6;</code>
         */
        public static final int ERROR_VALUE = 6;
        /**
         * <code>EXPIRED = 7;</code>
         */
        public static final int EXPIRED_VALUE = 7;


        public final int getNumber() { return value; }

        public static Summary valueOf(int value) {
            switch (value) {
                case 0: return DRAFT;
                case 1: return QUEUED;
                case 2: return IN_PROGRESS;
                case 3: return PAUSED;
                case 4: return DONE;
                case 5: return CANCELLED;
                case 6: return ERROR;
                case 7: return EXPIRED;
                default: return null;
            }
        }

        private static final Summary[] VALUES = values();

        private final int index;
        private final int value;

        private Summary(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public final Summary summary                = null;
    public final String progress                = null;
    public final String cause                   = null;
}
