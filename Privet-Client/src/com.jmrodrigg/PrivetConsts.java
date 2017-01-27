package com.jmrodrigg;

/**
 * Author: jrodriguezg
 * Date: 1/25/17
 */
public interface PrivetConsts {

    String PRIVET_TYPE_PRINTER = "printer";

    String PRIVET_INFO                      = "/privet/info";
    String PRIVET_REGISTER                  = "/privet/register";
    String PRIVET_ACCESSTOKEN               = "/privet/accesstoken";
    String PRIVET_CAPABILITIES              = "/privet/capabilities";

    String PRIVET_PRINT_CREATEJOB           = "/privet/printer/createjob";
    String PRIVET_PRINT_SUBMITDOC           = "/privet/printer/submitdoc";
    String PRIVET_PRINT_JOBSTATE            = "/privet/printer/jobstate";

    String PRIVET_ERROR_INVALID_TICKET      = "invalid_ticket";
    String PRIVET_ERROR_PRINTER_BUSY        = "printer_busy";
    String PRIVET_ERROR_PRINTER_ERROR       = "printer_error";
    String PRIVET_INVALID_PRIVET_TOKEN      = "invalid_x_privet_token";
}
