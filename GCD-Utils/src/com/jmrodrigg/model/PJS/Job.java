package com.jmrodrigg.model.PJS;

import com.google.gson.JsonObject;
import java.util.List;

/**
 * Author: jrodriguezg
 * Date: 8/8/16
 */
public class Job {

    public String id                          = null;
    public String printerName                 = null;
    public String title                       = null;
    public String contentType                 = null;
    public String fileUrl                     = null;
    public String rasterUrl                   = null;
    @Deprecated
    public String ticketUrl                   = null;
    public Long createTime                    = null;
    public Long updateTime                    = null;
    public PrintJobState semanticState        = null;
    public PrintJobUIState uiState            = null;
    @Deprecated
    public LegacyJobStatus status             = null;
    @Deprecated
    public String errorCode                   = null;
    @Deprecated
    public String message                     = null;
    public List<String> tags                  = null;
    public String ownerId                     = null;
    public Integer numberOfPages              = null;
    public String printerType                 = null;
    public String driveUrl                    = null;

    public Job(JsonObject object) {
        System.out.println("new");

        this.id = object.get("id").getAsString();
        // TODO printerId.
        this.printerName = object.get("printerName").getAsString();
        this.title = object.get("title").getAsString();
        this.contentType = object.get("contentType").getAsString();
        this.fileUrl = object.get("fileUrl").getAsString();
        this.rasterUrl = object.get("rasterUrl").getAsString();

        this.ticketUrl = object.get("ticketUrl").getAsString();
        this.createTime = object.get("createTime").getAsLong();
        this.updateTime = object.get("updateTime").getAsLong();
        // TODO semanticState.
        // TODO uiState.

        // TODO tags.
        this.ownerId = object.get("ownerId").getAsString();
        this.numberOfPages = object.get("numberOfPages").getAsInt();
        this.printerType = object.get("printerType").getAsString();
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
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
