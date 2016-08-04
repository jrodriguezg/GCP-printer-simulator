package com.jmrodrigg;

import com.google.api.client.http.*;
import com.google.gson.internal.Pair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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

    public static Pair<Integer,String> submit(String access_token, String printerid, String jobType) throws IOException, URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");
        headers.setAuthorization("OAuth " + access_token);

        String ticket;

        if (jobType.equals("J")) {
            // Open the bitmap and adjust paper size to match roll width:
            int resolution = 300;
            int roll_width = 609600;

            BufferedImage picture = ImageIO.read(new File("GCD-Client/samples/Barcelona.jpg"));
            int height_microns = (int) ((picture.getHeight() * 25.4f * 1000) / resolution);
            int width_microns = (int) ((picture.getWidth() * 25.4f * 1000) / resolution);

            float scale = (float) roll_width / width_microns;

            int scaled_height = (int) (scale * height_microns);

            ticket = "{" +
                        "\"version\": " + "\"1.0\"," +
                        "\"print\": {" +
                            "\"media_size\": {" +
                                "\"width_microns\": " + roll_width + "," +
                                "\"height_microns\": " + scaled_height + "," +
                                "\"is_continuous_feed\": true" +
                            "}," +
                            "\"dpi\": {" +
                                "\"horizontal_dpi\": " + resolution + "," +
                                "\"vertical_dpi\": " + resolution +
                            "}" +
                        "}" +
                    "}";
        } else {
            ticket = "{" +
                        "\"version\": " + "\"1.0\"," +
                        "\"print\": {" +
                            "\"media_size\": {" +
                                "\"width_microns\": 609600," +  //TODO adjust paper width to media loaded in printer.
                                "\"height_microns\": 220000," + //TODO adjust paper length to document needs.
                                "\"is_continuous_feed\": true" +
                            "}" +
                        "}" +
                    "}";
        }

        Map<String, String> parameters = new HashMap<>();
        parameters.put("printerid",printerid);
        parameters.put("title","CloudPrint Job");

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
        File file;

        if (jobType.equals("J")) file = new File((Main.class.getClassLoader().getResource("samples/Barcelona.jpg")).toURI());
        else file = new File((Main.class.getClassLoader().getResource("samples/A4_land.pdf")).toURI());

        FileContent fileContent = new FileContent("application/octet-stream",file);
        MultipartContent.Part part = new MultipartContent.Part(fileContent);
        part.setHeaders(new HttpHeaders().set("Content-Disposition",String.format("form-data; name=content; filename=\"%s\"", file.getName())));
        content.addPart(part);


        HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(PRINT_URL + SUBMIT), content).setHeaders(headers).execute();
        return new Pair<>(response.getStatusCode(),response.parseAsString());
    }
}
