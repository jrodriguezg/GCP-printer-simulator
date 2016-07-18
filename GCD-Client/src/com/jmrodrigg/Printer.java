package com.jmrodrigg;

/**
 * Author: jrodriguezg
 * Date: 7/18/16
 */
public class Printer {

    private String printerid;
    private String status;
    private String displayName;

    public Printer(String printerid, String status, String displayName) {
        this.printerid = printerid;
        this.status = status;
        this.displayName = displayName;
    }


    @Override
    public String toString() {
        return "[" + status + "] - " + displayName + " {" + printerid + "}";
    }
}
