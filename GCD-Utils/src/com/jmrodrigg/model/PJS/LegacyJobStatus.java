package com.jmrodrigg.model.PJS;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public enum LegacyJobStatus {
    /**
     * <code>QUEUED = 0;</code>
     */
    QUEUED(0, 0),
    /**
     * <code>IN_PROGRESS = 1;</code>
     */
    IN_PROGRESS(1, 1),
    /**
     * <code>DONE = 2;</code>
     */
    DONE(2, 2),
    /**
     * <code>ERROR = 3;</code>
     */
    ERROR(3, 3),
    /**
     * <code>SUBMITTED = 4;</code>
     */
    SUBMITTED(4, 4),
    /**
     * <code>HELD = 5;</code>
     */
    HELD(5, 5),
    ;

    /**
     * <code>NO_FITTING = 0;</code>
     */
    public static final int QUEUED_VALUE = 0;
    /**
     * <code>FIT_TO_PAGE = 1;</code>
     */
    public static final int IN_PROGRESS_VALUE = 1;
    /**
     * <code>GROW_TO_PAGE = 2;</code>
     */
    public static final int DONE_VALUE = 2;
    /**
     * <code>SHRINK_TO_PAGE = 3;</code>
     */
    public static final int ERROR_VALUE = 3;
    /**
     * <code>FILL_PAGE = 4;</code>
     */
    public static final int SUBMITTED_VALUE = 4;

    /**
     * <code>HELD = 5;</code>
     */
    public static final int HELD_VALUE = 5;


    public final int getNumber() { return value; }

    public static LegacyJobStatus valueOf(int value) {
        switch (value) {
            case 0: return QUEUED;
            case 1: return IN_PROGRESS;
            case 2: return DONE;
            case 3: return ERROR;
            case 4: return SUBMITTED;
            case 5: return HELD;
            default: return null;
        }
    }

    private static final LegacyJobStatus[] VALUES = values();

    private final int index;
    private final int value;

    private LegacyJobStatus(int index, int value) {
        this.index = index;
        this.value = value;
    }
}
