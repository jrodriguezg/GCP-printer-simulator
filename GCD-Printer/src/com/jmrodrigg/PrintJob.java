package com.jmrodrigg;

import com.google.gson.JsonElement;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: jrodriguezg
 * Date: 7/10/16
 */
public class PrintJob {

    public static final String CONTENT_TYPE_PDF = "application/pdf";
    public static final String CONTENT_TYPE_JPG = "image/jpeg";

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd - HH:mm");

    private String jobid;

    private String title;
    private int pages;
    private String contentType;

    private String status;
    private Date updateTime;

    private String fileUrl;
    private String rasterUrl;

    public PrintJob(JsonElement jsonJob) {
        jobid = jsonJob.getAsJsonObject().get("id").getAsString();
        title = jsonJob.getAsJsonObject().get("title").getAsString();
        fileUrl = jsonJob.getAsJsonObject().get("fileUrl").getAsString();
        rasterUrl = jsonJob.getAsJsonObject().get("rasterUrl").getAsString();
        pages = jsonJob.getAsJsonObject().get("numberOfPages").getAsInt();

        contentType = jsonJob.getAsJsonObject().get("contentType").getAsString();
        status = jsonJob.getAsJsonObject().get("status").getAsString();
        updateTime = new Date(jsonJob.getAsJsonObject().get("updateTime").getAsLong());
    }

    public String getTitle() {
        return title;
    }

    public String getJobId() {
        return jobid;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getRasterUrl() {
        return rasterUrl;
    }

    public int getPages() {
        return pages;
    }

    public String getContentType() {
        return contentType;
    }

    public String toString() {
        return sdf.format(updateTime) + " - " + jobid + " - " + status
            + "\n\t Title: " + title
            + "\n\t Content-Type: " + contentType
            + "\n\t Pages: " + pages
            + "\n\t File Url: " + fileUrl
            + "\n\t Raster Url: " + rasterUrl
            + "\n--------------";
    }
}
