package com.jmrodrigg;

/**
 * Created by jrodriguezg on 7/11/16.
 */
public interface CloudPrintConsts {
    String PRINT_URL = "https://www.google.com/cloudprint";
    String REGISTER = "/register";
    String FETCH = "/fetch";
    String TICKET = "/ticket";
    String GET_AUTH_CODE = "/getauthcode?printerid=%s&oauth_client_id=%s";
}
