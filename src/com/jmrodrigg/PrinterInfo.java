package com.jmrodrigg;

/**
 * Created by jrodriguezg on 7/11/16.
 */
public class PrinterInfo {
    String printerid;
    String authorization_code;
    String refresh_token;
    String user_email;

    public PrinterInfo(String pInfo) {

        String[] attrs = pInfo.split(";");

        printerid = attrs[0];
        authorization_code = attrs[1];
        refresh_token = attrs[2];
        user_email = attrs[3];
    }
}
