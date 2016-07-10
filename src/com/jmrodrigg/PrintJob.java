package com.jmrodrigg;

import com.google.gson.JsonElement;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jrodriguez on 10/07/16.
 */
public class PrintJob {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd - HH:mm");

    private String title;
    private int pages;
    private String contentType;

    private String status;
    private Date updateTime;

    private String fileUrl;
    private String rasterUrl;

    public PrintJob(JsonElement jsonJob) {
        title = jsonJob.getAsJsonObject().get("title").getAsString();
        fileUrl = jsonJob.getAsJsonObject().get("fileUrl").getAsString();
        rasterUrl = jsonJob.getAsJsonObject().get("rasterUrl").getAsString();
        pages = jsonJob.getAsJsonObject().get("numberOfPages").getAsInt();

        contentType = jsonJob.getAsJsonObject().get("contentType").getAsString();
        status = jsonJob.getAsJsonObject().get("status").getAsString();
        updateTime = new Date(jsonJob.getAsJsonObject().get("updateTime").getAsLong());
    }

    public String toString() {
        return sdf.format(updateTime) + " - " + status + " - " + title + " - " + pages + " pages - " + contentType
            + "\n\t File Url: " + fileUrl
            + "\n\t Raster Url: " + rasterUrl
            + "\n--------------";
    }
}
