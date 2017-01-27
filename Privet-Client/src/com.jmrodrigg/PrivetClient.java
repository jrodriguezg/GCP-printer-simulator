package com.jmrodrigg;

import com.google.api.client.http.*;
import com.google.gson.Gson;
import com.google.gson.internal.Pair;
import com.jmrodrigg.model.CJT.CloudJobTicket;
import com.jmrodrigg.model.CJT.PrintTicket;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import static com.jmrodrigg.Common.requestFactory;

/**
 * Author: jrodriguezg
 * Date: 1/25/17
 */
public class PrivetClient implements PrivetConsts {

    public static Pair<Integer,String> info(String printer_ip) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-Privet-Token","\"\"");

        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://" + printer_ip + PRIVET_INFO)).setHeaders(headers);
        HttpResponse response = request.execute();

        return new Pair<>(response.getStatusCode(),response.parseAsString());
    }

    public static Pair<Integer,String> capabilities(PrivetDevice device) throws IOException {

        if (device.supportsCapabilities()) {
            HttpHeaders headers = new HttpHeaders();
            headers.put("X-Privet-Token", device.getPrivetToken());

            HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://" + device.getDeviceIp() + PRIVET_CAPABILITIES)).setHeaders(headers);
            HttpResponse response = request.execute();

            return new Pair<>(response.getStatusCode(),response.parseAsString());
        } else {
            return null;
        }
    }

    public static Pair<Integer,String> create(PrivetDevice device, String jobType) throws IOException {

        // Cloud Job Ticket:
        com.jmrodrigg.model.CJT.MediaSize mediaSize = new com.jmrodrigg.model.CJT.MediaSize();
        mediaSize.width_microns = 914400;
        mediaSize.height_microns = 220000;
        mediaSize.is_continuous_feed = true;

        PrintTicket printTicket = new PrintTicket.PrintTicketBuilder()
                .mediaSize(mediaSize)
                .build();

        CloudJobTicket jobTicket = new CloudJobTicket.CloudJobTicketBuilder()
                .version("1.0")
                .printTicket(printTicket)
                .build();

        Gson g = new Gson();
        String jsonTicket = g.toJson(jobTicket);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("ticket",jsonTicket);

//        MultipartContent content = new MultipartContent().setMediaType(
//                new HttpMediaType("multipart/form-data")
//                        .setParameter("boundary", "__END_OF_PART__"));

        InputStreamContent content = new InputStreamContent("text/plain",
                new ByteArrayInputStream(jsonTicket.getBytes(StandardCharsets.UTF_8)));

//        // 1.- Parameters:
//        for (String name : parameters.keySet()) {
//            MultipartContent.Part part = new MultipartContent.Part(
//                    new ByteArrayContent(null, parameters.get(name).getBytes()));
//            part.setHeaders(new HttpHeaders().set("Content-Disposition", String.format("form-data; name=\"%s\"", name)));
//            content.addPart(part);
//        }

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-Privet-Token", device.getPrivetToken());

        HttpResponse response = requestFactory.buildPostRequest(new GenericUrl("http://" + device.getDeviceIp() + PRIVET_PRINT_CREATEJOB), content).setHeaders(headers).execute();
        return new Pair<>(response.getStatusCode(),response.parseAsString());
    }

    public static Pair<Integer,String> submit(PrivetDevice device, String jobType, Integer job_id) throws IOException, URISyntaxException {

        File file;
        if (jobType.equals("J")) file = new File((Main.class.getClassLoader().getResource("samples/Barcelona.jpg")).toURI());
        else file = new File((Main.class.getClassLoader().getResource("samples/A4_land.pdf")).toURI());

        String contentType = Files.probeContentType(file.toPath());

        MultipartContent content = new MultipartContent().setMediaType(
                new HttpMediaType("multipart/form-data")
                        .setParameter("boundary", "__END_OF_PART__"));

        FileContent fileContent = new FileContent("application/octet-stream",file);
        MultipartContent.Part part = new MultipartContent.Part(fileContent);
        part.setHeaders(new HttpHeaders().set("Content-Disposition",String.format("form-data; name=\"file\"; filename=\"%s\"; Content-Type:\"%s\"", file.getName(), contentType)));
        content.addPart(part);

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-Privet-Token", device.getPrivetToken());
        headers.setContentType(contentType);

        String submitUrl = "http://" + device.getDeviceIp() + PRIVET_PRINT_SUBMITDOC;
        if (job_id != null) submitUrl += "?job_id=" + job_id;

        HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(submitUrl), fileContent).setHeaders(headers).execute();

        return new Pair<>(response.getStatusCode(),response.parseAsString());

    }
}
