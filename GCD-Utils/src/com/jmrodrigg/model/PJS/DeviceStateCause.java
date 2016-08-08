package com.jmrodrigg.model.PJS;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class DeviceStateCause {

    public enum ErrorCode {
        /**
         * <code>INPUT_TRAY = 0;</code>
         *
         * <pre>
         * Error due to input tray problem.
         * </pre>
         */
        INPUT_TRAY(0, 0),
        /**
         * <code>MARKER = 1;</code>
         *
         * <pre>
         * Error due to marker problem.
         * </pre>
         */
        MARKER(1, 1),
        /**
         * <code>MEDIA_PATH = 2;</code>
         *
         * <pre>
         * Error due to a problem in the media path.
         * </pre>
         */
        MEDIA_PATH(2, 2),
        /**
         * <code>MEDIA_SIZE = 3;</code>
         *
         * <pre>
         * Error due to media size problem.
         * </pre>
         */
        MEDIA_SIZE(3, 3),
        /**
         * <code>MEDIA_TYPE = 4;</code>
         *
         * <pre>
         * Error due to media type problem.
         * </pre>
         */
        MEDIA_TYPE(4, 4),
        /**
         * <code>OTHER = 100;</code>
         *
         * <pre>
         * Error due to some other device state.
         * </pre>
         */
        OTHER(5, 100),
        ;

        /**
         * <code>INPUT_TRAY = 0;</code>
         *
         * <pre>
         * Error due to input tray problem.
         * </pre>
         */
        public static final int INPUT_TRAY_VALUE = 0;
        /**
         * <code>MARKER = 1;</code>
         *
         * <pre>
         * Error due to marker problem.
         * </pre>
         */
        public static final int MARKER_VALUE = 1;
        /**
         * <code>MEDIA_PATH = 2;</code>
         *
         * <pre>
         * Error due to a problem in the media path.
         * </pre>
         */
        public static final int MEDIA_PATH_VALUE = 2;
        /**
         * <code>MEDIA_SIZE = 3;</code>
         *
         * <pre>
         * Error due to media size problem.
         * </pre>
         */
        public static final int MEDIA_SIZE_VALUE = 3;
        /**
         * <code>MEDIA_TYPE = 4;</code>
         *
         * <pre>
         * Error due to media type problem.
         * </pre>
         */
        public static final int MEDIA_TYPE_VALUE = 4;
        /**
         * <code>OTHER = 100;</code>
         *
         * <pre>
         * Error due to some other device state.
         * </pre>
         */
        public static final int OTHER_VALUE = 100;


        public final int getNumber() { return value; }

        public static ErrorCode valueOf(int value) {
            switch (value) {
                case 0: return INPUT_TRAY;
                case 1: return MARKER;
                case 2: return MEDIA_PATH;
                case 3: return MEDIA_SIZE;
                case 4: return MEDIA_TYPE;
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
