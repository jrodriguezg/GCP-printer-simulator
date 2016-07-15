package com.jmrodrigg;

import com.google.api.client.http.*;
import com.google.gson.internal.Pair;

import java.io.IOException;

import static com.jmrodrigg.Common.requestFactory;

/**
 * Author: jrodriguezg
 * Date: 7/15/16
 */
public class GCPClient implements CloudPrintConsts {

    public static Pair<Integer,String> search(String access_token) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");
        headers.setAuthorization("OAuth " + access_token);

        HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(PRINT_URL + SEARCH), new EmptyContent()).setHeaders(headers).execute();
        return new Pair<>(response.getStatusCode(),response.parseAsString());
    }
}
