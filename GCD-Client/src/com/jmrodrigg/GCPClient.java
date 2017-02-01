package com.jmrodrigg;

import com.google.api.client.http.*;
import com.google.gson.Gson;
import com.google.gson.internal.Pair;
import com.jmrodrigg.model.CDD.MediaSize;
import com.jmrodrigg.model.CJT.*;
import com.jmrodrigg.model.Utils.PrintingColor;
import org.apache.http.HttpStatus;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
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

    public static Pair<Integer,String> submit(String access_token, Printer printer, String jobType) throws IOException, URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");
        headers.setAuthorization("OAuth " + access_token);

        PrintTicket printTicket;

        if (jobType.equals("J")) {

            int resolution = 300;
            int scaled_width;
            int scaled_height;
            boolean is_continuous_feed;

            // Open the bitmap and adjust paper size to match roll width:
            BufferedImage picture = ImageIO.read(new File((Main.class.getClassLoader().getResource("samples/Barcelona.jpg")).toURI()));
            int height_microns = (int) ((picture.getHeight() * 25.4f * 1000) / resolution);
            int width_microns = (int) ((picture.getWidth() * 25.4f * 1000) / resolution);

            Pair<Integer,Integer> jobMediaSize = null;

            // Search a roll media size:
            if (printer.getPrinterDescription() != null) jobMediaSize = findMediaSize(printer.getPrinterDescription().mediaSize.option);

            if (jobMediaSize == null) {
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

                com.jmrodrigg.model.CJT.MediaSize mediaSize = new com.jmrodrigg.model.CJT.MediaSize();
                mediaSize.height_microns = scaled_height;
                mediaSize.width_microns = scaled_width;
                mediaSize.is_continuous_feed = is_continuous_feed;

                com.jmrodrigg.model.CJT.Dpi dpi = new com.jmrodrigg.model.CJT.Dpi();
                dpi.horizontal_dpi = 300;
                dpi.vertical_dpi = 300;

                printTicket = new PrintTicket.PrintTicketBuilder()
                        .dpi(dpi)
                        .mediaSize(mediaSize)
                        .build();
            } else {
                // No valid PrinterDescription/MediaSize information. Aborting print job.
                // TODO May be improved:
                return new Pair<>(-1," No valid PrinterDescription/MediaSize information. Job submission aborted.");
            }
        } else {
            com.jmrodrigg.model.CJT.MediaSize mediaSize = new com.jmrodrigg.model.CJT.MediaSize();
            mediaSize.width_microns = 914400; // 36-in roll.
            mediaSize.height_microns = 220000;
            mediaSize.is_continuous_feed = true;

            com.jmrodrigg.model.CJT.Color color = new com.jmrodrigg.model.CJT.Color();
            color.type = PrintingColor.Type.STANDARD_COLOR;

            printTicket = new PrintTicket.PrintTicketBuilder()
                    .mediaSize(mediaSize)
                    .color(color)
                    .build();
            // TODO Work In Progress
        }

        CloudJobTicket jobTicket = new CloudJobTicket.CloudJobTicketBuilder()
                .version("1.0")
                .printTicket(printTicket)
                .build();

        Gson g = new Gson();
        String jsonTicket = g.toJson(jobTicket);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("printerid",printer.getPrinterId());
        parameters.put("title","CloudPrint Job");

        parameters.put("ticket",jsonTicket);

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


    private static Pair<Integer,Integer> findMediaSize(List<MediaSize.Option> options) {
        Integer width, height;
        // Priority 1 - Find Roll media size:
        for (MediaSize.Option opt : options) {
            if (opt.is_continuous_feed) {
                width = opt.width_microns;
                height = opt.height_microns;

                return new Pair<>(width,height);
            }
        }

        // Priority 2 - Find A4 media size:
        for (MediaSize.Option opt : options) {
            if (opt.name == MediaSize.Name.ISO_A4) {
                width = opt.width_microns;
                height = opt.height_microns;

                return new Pair<>(width,height);
            }
        }

        // Priority 3 - return default media size:
        for (MediaSize.Option opt : options) {
            if (opt.is_default) {
                width = opt.width_microns;
                height = opt.height_microns;

                return new Pair<>(width,height);
            }
        }

        // Fallback - return the first media size:
        if (!options.isEmpty()) {
            width = options.get(0).width_microns;
            height = options.get(0).height_microns;
            return new Pair<>(width, height);
        }

        return null;
    }
}
