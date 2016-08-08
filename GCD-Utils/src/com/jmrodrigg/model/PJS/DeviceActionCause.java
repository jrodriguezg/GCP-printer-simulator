package com.jmrodrigg.model.PJS;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class DeviceActionCause {

    public enum ErrorCode {
        /**
         * <code>DOWNLOAD_FAILURE = 0;</code>
         *
         * <pre>
         * Error while downloading job.
         * </pre>
         */
        DOWNLOAD_FAILURE(0, 0),
        /**
         * <code>INVALID_TICKET = 1;</code>
         *
         * <pre>
         * Error due to invalid job ticket.
         * </pre>
         */
        INVALID_TICKET(1, 1),
        /**
         * <code>PRINT_FAILURE = 2;</code>
         *
         * <pre>
         * A generic printing error occurred.
         * </pre>
         */
        PRINT_FAILURE(2, 2),
        /**
         * <code>OTHER = 100;</code>
         *
         * <pre>
         * Error due to some other device action.
         * </pre>
         */
        OTHER(3, 100),
        ;

        /**
         * <code>DOWNLOAD_FAILURE = 0;</code>
         *
         * <pre>
         * Error while downloading job.
         * </pre>
         */
        public static final int DOWNLOAD_FAILURE_VALUE = 0;
        /**
         * <code>INVALID_TICKET = 1;</code>
         *
         * <pre>
         * Error due to invalid job ticket.
         * </pre>
         */
        public static final int INVALID_TICKET_VALUE = 1;
        /**
         * <code>PRINT_FAILURE = 2;</code>
         *
         * <pre>
         * A generic printing error occurred.
         * </pre>
         */
        public static final int PRINT_FAILURE_VALUE = 2;
        /**
         * <code>OTHER = 100;</code>
         *
         * <pre>
         * Error due to some other device action.
         * </pre>
         */
        public static final int OTHER_VALUE = 100;


        public final int getNumber() { return value; }

        public static ErrorCode valueOf(int value) {
            switch (value) {
                case 0: return DOWNLOAD_FAILURE;
                case 1: return INVALID_TICKET;
                case 2: return PRINT_FAILURE;
                case 100: return OTHER;
                default: return null;
            }
        }

        private static final ErrorCode[] VALUES = values();

        private final int index;
        private final int value;

        private ErrorCode(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public final ErrorCode error_code           = null;
}