package com.jmrodrigg.model.CDD;

/**
 * Author: jrodriguezg
 * Date: 7/29/16
 */
public class SupportedContentType {
    public final String content_type            = null; /* Required */
    public final String min_version             = null;
    public final String max_version             = null;

    @Override
    public String toString() {
        return "SupportedContentType{" +
                "content_type='" + content_type + '\'' +
                ", min_version='" + min_version + '\'' +
                ", max_version='" + max_version + '\'' +
                '}';
    }
}
