package com.jmrodrigg.model.PJS;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class UserActionCause {

    public enum ActionCode {
        /**
         * <code>CANCELLED = 0;</code>
         *
         * <pre>
         * User has cancelled the job.
         * </pre>
         */
        CANCELLED(0, 0),
        /**
         * <code>PAUSED = 1;</code>
         *
         * <pre>
         * User has paused the job.
         * </pre>
         */
        PAUSED(1, 1),
        /**
         * <code>OTHER = 100;</code>
         *
         * <pre>
         * User has performed some other action.
         * </pre>
         */
        OTHER(2, 100),
        ;

        /**
         * <code>CANCELLED = 0;</code>
         *
         * <pre>
         * User has cancelled the job.
         * </pre>
         */
        public static final int CANCELLED_VALUE = 0;
        /**
         * <code>PAUSED = 1;</code>
         *
         * <pre>
         * User has paused the job.
         * </pre>
         */
        public static final int PAUSED_VALUE = 1;
        /**
         * <code>OTHER = 100;</code>
         *
         * <pre>
         * User has performed some other action.
         * </pre>
         */
        public static final int OTHER_VALUE = 100;


        public final int getNumber() { return value; }

        public static ActionCode valueOf(int value) {
            switch (value) {
                case 0: return CANCELLED;
                case 1: return PAUSED;
                case 100: return OTHER;
                default: return null;
            }
        }

        private static final ActionCode[] VALUES = values();

        private final int index;
        private final int value;

        private ActionCode(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public final ActionCode action_code         = null;
}
