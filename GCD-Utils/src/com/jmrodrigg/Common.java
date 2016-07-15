package com.jmrodrigg;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;

/**
 * Author: jrodriguezg
 * Date: 7/10/16.
 */
public class Common {
    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new JacksonFactory();

    static final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(request -> request.setParser(new JsonObjectParser(JSON_FACTORY)));
}
