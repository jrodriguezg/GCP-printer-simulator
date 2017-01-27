package com.jmrodrigg;

import com.jmrodrigg.model.CDD.PrinterDescription;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author: jrodriguezg
 * Date: 1/26/17
 */
public class PrivetDevice {
    public enum Type {
        PRIVET_TYPE_PRINTER
    }

    public enum Api {
        API_PRIVET_INFO,
        API_PRIVET_REGISTER,
        API_PRIVET_ACCESSTOKEN,
        API_PRIVET_CAPABILITIES
    }

    public enum PrinterApi {
        API_PRIVET_PRINTER_CREATEJOB,
        API_PRIVET_PRINTER_SUBMITDOC,
        API_PRIVET_PRINTER_JOBSTATE
    }

    private String deviceIp;

    private String deviceId;
    private String privetToken;
    private String name;
    private String status;

    private Type type;
    private HashMap<Api,String> supportedApis;
    private HashMap<PrinterApi,String> supportedPrinterApis;

    // Printer-related:
    PrinterDescription printerDescription;

    public PrivetDevice(String deviceIp, String deviceId, String name, String privetToken, String status, String type, ArrayList<String> supportedApis) {
        this.deviceIp = deviceIp;

        this.deviceId = deviceId;
        this.name = name;
        this.privetToken = privetToken;
        this.status = status;

        switch (type) {
            case PrivetConsts.PRIVET_TYPE_PRINTER:
                this.type = Type.PRIVET_TYPE_PRINTER;
        }

        this.supportedApis = new HashMap<>();
        this.supportedPrinterApis = new HashMap<>();

        for (String apiURL : supportedApis) {
            if (apiURL.equals(PrivetConsts.PRIVET_INFO))            this.supportedApis.put(Api.API_PRIVET_INFO,apiURL);
            if (apiURL.equals(PrivetConsts.PRIVET_REGISTER))        this.supportedApis.put(Api.API_PRIVET_REGISTER,apiURL);
            if (apiURL.equals(PrivetConsts.PRIVET_ACCESSTOKEN))     this.supportedApis.put(Api.API_PRIVET_ACCESSTOKEN,apiURL);
            if (apiURL.equals(PrivetConsts.PRIVET_CAPABILITIES))    this.supportedApis.put(Api.API_PRIVET_CAPABILITIES,apiURL);

            if (apiURL.equals(PrivetConsts.PRIVET_PRINT_CREATEJOB)) this.supportedPrinterApis.put(PrinterApi.API_PRIVET_PRINTER_CREATEJOB,apiURL);
            if (apiURL.equals(PrivetConsts.PRIVET_PRINT_SUBMITDOC)) this.supportedPrinterApis.put(PrinterApi.API_PRIVET_PRINTER_SUBMITDOC,apiURL);
            if (apiURL.equals(PrivetConsts.PRIVET_PRINT_JOBSTATE))  this.supportedPrinterApis.put(PrinterApi.API_PRIVET_PRINTER_JOBSTATE,apiURL);
        }
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Type getType() {
        return type;
    }

    public String getPrivetToken() {
        return privetToken;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public boolean supportsCapabilities() {
        return supportedApis.containsKey(Api.API_PRIVET_CAPABILITIES);
    }

    public boolean supportsCreateJob() {
        return supportedPrinterApis.containsKey(PrinterApi.API_PRIVET_PRINTER_CREATEJOB);
    }

    public void setCapabilities(PrinterDescription printerDescription) {
        this.printerDescription = printerDescription;
    }

    public String getTypeStr() {
        String strType = null;

        switch (type) {
            case PRIVET_TYPE_PRINTER:
                strType = PrivetConsts.PRIVET_TYPE_PRINTER;
                break;
        }

        return strType;
    }
}
