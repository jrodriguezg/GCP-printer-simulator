package com.jmrodrigg;

/**
 * Author: jrodriguezg
 * Date: 7/11/16
 */
public interface CloudPrintConsts {
    String GET_AUTH_CODE_URL = "/getauthcode?printerid=%s&oauth_client_id=%s";
    String PRINT_URL = "https://www.google.com/cloudprint";

    String PRINTER = "/printer";

    String CONTROL = "/control";
    String FETCH = "/fetch";
    String LIST = "/list";
    String REGISTER = "/register";
    String TICKET = "/ticket";
    String UPDATE = "/update";
}
