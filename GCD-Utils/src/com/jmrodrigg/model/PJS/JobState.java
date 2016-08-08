package com.jmrodrigg.model.PJS;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class JobState {

    public enum Type {
        /**
         * <code>DRAFT = 0;</code>
         *
         * <pre>
         * Job is being created and is not ready for processing yet.
         * </pre>
         */
        DRAFT(0, 0),
        /**
         * <code>HELD = 1;</code>
         *
         * <pre>
         * Submitted and ready, but should not be processed yet.
         * </pre>
         */
        HELD(1, 1),
        /**
         * <code>QUEUED = 2;</code>
         *
         * <pre>
         * Ready for processing.
         * </pre>
         */
        QUEUED(2, 2),
        /**
         * <code>IN_PROGRESS = 3;</code>
         *
         * <pre>
         * Currently being processed.
         * </pre>
         */
        IN_PROGRESS(3, 3),
        /**
         * <code>STOPPED = 4;</code>
         *
         * <pre>
         * Was in progress, but stopped due to error or user intervention.
         * </pre>
         */
        STOPPED(4, 4),
        /**
         * <code>DONE = 5;</code>
         *
         * <pre>
         * Processed successfully.
         * </pre>
         */
        DONE(5, 5),
        /**
         * <code>ABORTED = 6;</code>
         *
         * <pre>
         * Aborted due to error or by user action (cancelled).
         * </pre>
         */
        ABORTED(6, 6),
        ;

        /**
         * <code>DRAFT = 0;</code>
         *
         * <pre>
         * Job is being created and is not ready for processing yet.
         * </pre>
         */
        public static final int DRAFT_VALUE = 0;
        /**
         * <code>HELD = 1;</code>
         *
         * <pre>
         * Submitted and ready, but should not be processed yet.
         * </pre>
         */
        public static final int HELD_VALUE = 1;
        /**
         * <code>QUEUED = 2;</code>
         *
         * <pre>
         * Ready for processing.
         * </pre>
         */
        public static final int QUEUED_VALUE = 2;
        /**
         * <code>IN_PROGRESS = 3;</code>
         *
         * <pre>
         * Currently being processed.
         * </pre>
         */
        public static final int IN_PROGRESS_VALUE = 3;
        /**
         * <code>STOPPED = 4;</code>
         *
         * <pre>
         * Was in progress, but stopped due to error or user intervention.
         * </pre>
         */
        public static final int STOPPED_VALUE = 4;
        /**
         * <code>DONE = 5;</code>
         *
         * <pre>
         * Processed successfully.
         * </pre>
         */
        public static final int DONE_VALUE = 5;
        /**
         * <code>ABORTED = 6;</code>
         *
         * <pre>
         * Aborted due to error or by user action (cancelled).
         * </pre>
         */
        public static final int ABORTED_VALUE = 6;


        public final int getNumber() { return value; }

        public static Type valueOf(int value) {
            switch (value) {
                case 0: return DRAFT;
                case 1: return HELD;
                case 2: return QUEUED;
                case 3: return IN_PROGRESS;
                case 4: return STOPPED;
                case 5: return DONE;
                case 6: return ABORTED;
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

    public final Type type                                          = null;

    // Exactly one of the following four fields must be set if and only if the
    // state type is ABORTED or STOPPED.
    // For example:
    // - {"type": "ABORTED", "user_action_cause": {"action_code": "CANCELLED"}}
    //   interpreted as the job was cancelled by the user.
    // - {"type": "STOPPED", "device_state_cause": {"error_code": "MEDIA_PATH"}}
    //   interpreted as the job was stopped due to a temporary problem with the
    //   media path, such as paper jam (the specific cause will be discerned from
    //   the device state by the server).
    // - {"type": "ABORTED",
    //    "device_action_cause": {"error_code": "DOWNLOAD_FAILURE"}}
    //   interpreted as the job was aborted due to a download failure.c

    // If present, job state was changed due to user action.
    public final UserActionCause user_action_cause                  = null;

    // If present, job state was changed due to device state change.
    public final DeviceStateCause device_state_cause                = null;

    // If present, job state was changed due to device action.
    public final DeviceActionCause device_action_cause              = null;

    // If present, job state was changed due to service (Cloud Print) action.
    // Should only be set by the Cloud Print server.
    public final ServiceActionCause service_action_cause            = null;

}
