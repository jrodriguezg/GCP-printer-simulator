package com.jmrodrigg;

import com.google.api.client.http.*;
import com.google.gson.internal.Pair;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    public static Pair<Integer,String> printer(String access_token, String printerid) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");
        headers.setAuthorization("OAuth " + access_token);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("printerid",printerid);

        UrlEncodedContent content = new UrlEncodedContent(parameters);

        HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(PRINT_URL + PRINTER), content).setHeaders(headers).execute();
        return new Pair<>(response.getStatusCode(),response.parseAsString());
    }

    public static Pair<Integer,String> submit(String access_token, String printerid) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");
        headers.setAuthorization("OAuth " + access_token);


        Map<String, String> parameters = new HashMap<>();
        parameters.put("printerid",printerid);
        parameters.put("title","CloudPrint Job");

        String ticket = "{" +
                            "\"version\": " + "\"1.0\"," +
                            "\"print\": {" +
                                "\"media_size\": {" +
                                    "\"width_microns\": 609600," +
                                    "\"height_microns\": 203200," +
                                    "\"is_continuous_feed\": true" +
                                "}" +
                            "}" +
                        "}";

        parameters.put("ticket",ticket);

        MultipartContent content = new MultipartContent().setMediaType(
                new HttpMediaType("multipart/form-data")
                        .setParameter("boundary", "__END_OF_PART__"));

        // 1.- Parameters:
        for (String name : parameters.keySet()) {
            MultipartContent.Part part = new MultipartContent.Part(
                    new ByteArrayContent(null, parameters.get(name).getBytes()));
            part.setHeaders(new HttpHeaders().set("Content-Disposition", String.format("form-data; name=\"%s\"", name)));
            content.addPart(part);
        }

        // 2.- Document:
        File file = new File("GCD-Client/samples/test_24x8in.pdf");

        FileContent fileContent = new FileContent("application/octet-stream",file);
        MultipartContent.Part part = new MultipartContent.Part(fileContent);
        part.setHeaders(new HttpHeaders().set("Content-Disposition",String.format("form-data; name=content; filename=\"%s\"", file.getName())));
        content.addPart(part);


        HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(PRINT_URL + SUBMIT), content).setHeaders(headers).execute();
        return new Pair<>(response.getStatusCode(),response.parseAsString());
    }
}
