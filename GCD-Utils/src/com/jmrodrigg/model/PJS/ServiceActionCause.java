package com.jmrodrigg.model.PJS;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class ServiceActionCause {

    public enum ErrorCode {
        /**
         * <code>COMMUNICATION_WITH_DEVICE_ERROR = 0;</code>
         */
        COMMUNICATION_WITH_DEVICE_ERROR(0, 0),
        /**
         * <code>CONVERSION_ERROR = 1;</code>
         */
        CONVERSION_ERROR(1, 1),
        /**
         * <code>CONVERSION_FILE_TOO_BIG = 2;</code>
         */
        CONVERSION_FILE_TOO_BIG(2, 2),
        /**
         * <code>CONVERSION_UNSUPPORTED_CONTENT_TYPE = 3;</code>
         */
        CONVERSION_UNSUPPORTED_CONTENT_TYPE(3, 3),
        /**
         * <code>DELIVERY_FAILURE = 11;</code>
         */
        DELIVERY_FAILURE(4, 11),
        /**
         * <code>EXPIRATION = 14;</code>
         */
        EXPIRATION(5, 14),
        /**
         * <code>FETCH_DOCUMENT_FORBIDDEN = 4;</code>
         */
        FETCH_DOCUMENT_FORBIDDEN(6, 4),
        /**
         * <code>FETCH_DOCUMENT_NOT_FOUND = 5;</code>
         */
        FETCH_DOCUMENT_NOT_FOUND(7, 5),
        /**
         * <code>GOOGLE_DRIVE_QUOTA = 15;</code>
         */
        GOOGLE_DRIVE_QUOTA(8, 15),
        /**
         * <code>INCONSISTENT_JOB = 6;</code>
         */
        INCONSISTENT_JOB(9, 6),
        /**
         * <code>INCONSISTENT_PRINTER = 13;</code>
         */
        INCONSISTENT_PRINTER(10, 13),
        /**
         * <code>PRINTER_DELETED = 12;</code>
         */
        PRINTER_DELETED(11, 12),
        /**
         * <code>REMOTE_JOB_NO_LONGER_EXISTS = 7;</code>
         */
        REMOTE_JOB_NO_LONGER_EXISTS(12, 7),
        /**
         * <code>REMOTE_JOB_ERROR = 8;</code>
         */
        REMOTE_JOB_ERROR(13, 8),
        /**
         * <code>REMOTE_JOB_TIMEOUT = 9;</code>
         */
        REMOTE_JOB_TIMEOUT(14, 9),
        /**
         * <code>REMOTE_JOB_ABORTED = 10;</code>
         */
        REMOTE_JOB_ABORTED(15, 10),
        /**
         * <code>OTHER = 100;</code>
         */
        OTHER(16, 100),
        ;

        /**
         * <code>COMMUNICATION_WITH_DEVICE_ERROR = 0;</code>
         */
        public static final int COMMUNICATION_WITH_DEVICE_ERROR_VALUE = 0;
        /**
         * <code>CONVERSION_ERROR = 1;</code>
         */
        public static final int CONVERSION_ERROR_VALUE = 1;
        /**
         * <code>CONVERSION_FILE_TOO_BIG = 2;</code>
         */
        public static final int CONVERSION_FILE_TOO_BIG_VALUE = 2;
        /**
         * <code>CONVERSION_UNSUPPORTED_CONTENT_TYPE = 3;</code>
         */
        public static final int CONVERSION_UNSUPPORTED_CONTENT_TYPE_VALUE = 3;
        /**
         * <code>DELIVERY_FAILURE = 11;</code>
         */
        public static final int DELIVERY_FAILURE_VALUE = 11;
        /**
         * <code>EXPIRATION = 14;</code>
         */
        public static final int EXPIRATION_VALUE = 14;
        /**
         * <code>FETCH_DOCUMENT_FORBIDDEN = 4;</code>
         */
        public static final int FETCH_DOCUMENT_FORBIDDEN_VALUE = 4;
        /**
         * <code>FETCH_DOCUMENT_NOT_FOUND = 5;</code>
         */
        public static final int FETCH_DOCUMENT_NOT_FOUND_VALUE = 5;
        /**
         * <code>GOOGLE_DRIVE_QUOTA = 15;</code>
         */
        public static final int GOOGLE_DRIVE_QUOTA_VALUE = 15;
        /**
         * <code>INCONSISTENT_JOB = 6;</code>
         */
        public static final int INCONSISTENT_JOB_VALUE = 6;
        /**
         * <code>INCONSISTENT_PRINTER = 13;</code>
         */
        public static final int INCONSISTENT_PRINTER_VALUE = 13;
        /**
         * <code>PRINTER_DELETED = 12;</code>
         */
        public static final int PRINTER_DELETED_VALUE = 12;
        /**
         * <code>REMOTE_JOB_NO_LONGER_EXISTS = 7;</code>
         */
        public static final int REMOTE_JOB_NO_LONGER_EXISTS_VALUE = 7;
        /**
         * <code>REMOTE_JOB_ERROR = 8;</code>
         */
        public static final int REMOTE_JOB_ERROR_VALUE = 8;
        /**
         * <code>REMOTE_JOB_TIMEOUT = 9;</code>
         */
        public static final int REMOTE_JOB_TIMEOUT_VALUE = 9;
        /**
         * <code>REMOTE_JOB_ABORTED = 10;</code>
         */
        public static final int REMOTE_JOB_ABORTED_VALUE = 10;
        /**
         * <code>OTHER = 100;</code>
         */
        public static final int OTHER_VALUE = 100;


        public final int getNumber() { return value; }

        public static ErrorCode valueOf(int value) {
            switch (value) {
                case 0: return COMMUNICATION_WITH_DEVICE_ERROR;
                case 1: return CONVERSION_ERROR;
                case 2: return CONVERSION_FILE_TOO_BIG;
                case 3: return CONVERSION_UNSUPPORTED_CONTENT_TYPE;
                case 11: return DELIVERY_FAILURE;
                case 14: return EXPIRATION;
                case 4: return FETCH_DOCUMENT_FORBIDDEN;
                case 5: return FETCH_DOCUMENT_NOT_FOUND;
                case 15: return GOOGLE_DRIVE_QUOTA;
                case 6: return INCONSISTENT_JOB;
                case 13: return INCONSISTENT_PRINTER;
                case 12: return PRINTER_DELETED;
                case 7: return REMOTE_JOB_NO_LONGER_EXISTS;
                case 8: return REMOTE_JOB_ERROR;
                case 9: return REMOTE_JOB_TIMEOUT;
                case 10: return REMOTE_JOB_ABORTED;
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
