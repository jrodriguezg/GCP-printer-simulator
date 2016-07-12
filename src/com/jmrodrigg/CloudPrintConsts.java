package com.jmrodrigg;

/**
 * Author: jrodriguezg
 * Date: 7/11/16
 */
public interface CloudPrintConsts {
    String GET_AUTH_CODE_URL = "/getauthcode?printerid=%s&oauth_client_id=%s";

    String PRINT_URL = "https://www.google.com/cloudprint";
    String REGISTER = "/register";
    String FETCH = "/fetch";
    String TICKET = "/ticket";
    String CONTROL = "/control";
    String UPDATE = "/update";
}
