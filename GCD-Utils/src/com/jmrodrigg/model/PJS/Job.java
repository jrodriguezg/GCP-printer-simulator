package com.jmrodrigg.model.PJS;

import java.util.Date;
import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class Job {

    public final String id                          = null;
    public final String printerid                   = null;
    public final String printerName                 = null;
    public final String title                       = null;
    public final String contentType                 = null;
    public final String fileUrl                     = null;
    public final String rasterUrl                   = null;
    @Deprecated
    public final String ticketUrl                   = null;
    public final Long createTime                    = null;
    public final Long updateTime                    = null;
    public final PrintJobState semanticState        = null;
    public final PrintJobUIState uiState            = null;
    @Deprecated
    public final LegacyJobStatus status             = null;
    @Deprecated
    public final String errorCode                   = null;
    @Deprecated
    public final String message                     = null;
    public final List<String> tags                  = null;
    public final String ownerId                     = null;
    public final Integer numberOfPages              = null;
    public final String printerType                 = null;
    public final String driveUrl                    = null;

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", printerid='" + printerid + '\'' +
                ", printerName='" + printerName + '\'' +
                ", title='" + title + '\'' +
                ", contentType='" + contentType + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", rasterUrl='" + rasterUrl + '\'' +
                ", ticketUrl='" + ticketUrl + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", semanticState=" + semanticState +
                ", uiState=" + uiState +
                ", status=" + status +
                ", errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", tags=" + tags +
                ", ownerId='" + ownerId + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", printerType='" + printerType + '\'' +
                ", driveUrl='" + driveUrl + '\'' +
                '}';
    }
}
