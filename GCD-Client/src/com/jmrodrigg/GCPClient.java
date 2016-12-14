package com.jmrodrigg;

import com.google.api.client.http.*;
import com.google.gson.internal.Pair;
import com.jmrodrigg.model.CDD.*;

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

    private static Pair<Integer,Integer> findMediaSize(Printer printer) {
        Integer width, height;
        // Priority 1 - Find Roll media size:
        for (MediaSize.Option opt : printer.getPrinterDescription().media_size.option) {
            if (opt.is_continuous_feed) {
                width = opt.width_microns;
                height = opt.height_microns;

                return new Pair<>(width,height);
            }
        }

        // Priority 2 - Find A4 media size:
        for (MediaSize.Option opt : printer.getPrinterDescription().media_size.option) {
            if (opt.name == MediaSize.Name.ISO_A4) {
                width = opt.width_microns;
                height = opt.height_microns;

                return new Pair<>(width,height);
            }
        }

        // Fallback - return first media size:
        for (MediaSize.Option opt : printer.getPrinterDescription().media_size.option) {
            if (opt.is_default) {
                width = opt.width_microns;
                height = opt.height_microns;

                return new Pair<>(width,height);
            }
        }

        // ideally unreachable:
        return null;
    }

    public static Pair<Integer,String> submit(String access_token, Printer printer, String jobType) throws IOException, URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");
        headers.setAuthorization("OAuth " + access_token);

        String ticket;

        if (jobType.equals("J")) {

            int resolution = 300;
            int scaled_width;
            int scaled_height;
            boolean is_continuous_feed;

            // Open the bitmap and adjust paper size to match roll width:
            BufferedImage picture = ImageIO.read(new File((Main.class.getClassLoader().getResource("samples/Barcelona.jpg")).toURI()));
            int height_microns = (int) ((picture.getHeight() * 25.4f * 1000) / resolution);
            int width_microns = (int) ((picture.getWidth() * 25.4f * 1000) / resolution);

            // Search a roll media size:
            Pair<Integer,Integer> jobMediaSize = findMediaSize(printer);

            if (jobMediaSize.first == null) {
                scaled_height = jobMediaSize.second;

                float scale = (float) scaled_height / width_microns;
                scaled_width = (int) (scale * height_microns);

                is_continuous_feed = true;
            } else if (jobMediaSize.second == null) {
                scaled_width = jobMediaSize.first;

                float scale = (float) scaled_width / width_microns;
                scaled_height = (int) (scale * height_microns);

                is_continuous_feed = true;
            } else {
                // Standard size:
                scaled_width = jobMediaSize.first;
                scaled_height = jobMediaSize.second;
                is_continuous_feed = false;
            }

            ticket = "{" +
                        "\"version\": " + "\"1.0\"," +
                        "\"print\": {" +
                            "\"media_size\": {" +
                                "\"width_microns\": " + scaled_width + "," +
                                "\"height_microns\": " + scaled_height + "," +
                                "\"is_continuous_feed\": " + is_continuous_feed + "," +
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
        parameters.put("printerid",printer.getPrinterId());
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
