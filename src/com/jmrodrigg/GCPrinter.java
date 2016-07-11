package com.jmrodrigg;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gson.internal.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jrodriguez on 8/07/16.
 */
public class GCPrinter implements CloudPrintConsts {

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(request -> request.setParser(new JsonObjectParser(JSON_FACTORY)));

    public static Pair<Integer,String> register() throws IOException {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("name","Juanma GCD Printer");
        parameters.put("proxy","asdasd-asdasd-asdasd");
        parameters.put("uuid","1234-1234-1234");
        parameters.put("manufacturer","HP");
        parameters.put("model","Cloud Printer");
        parameters.put("gcp_version","2.0");
        parameters.put("setup_url","127.0.0.1:80/setup");
        parameters.put("support_url","127.0.0.1:80/support");
        parameters.put("update_url","127.0.0.1:80/update");
        parameters.put("firmware","GCP_01_02.01");
        parameters.put("local_settings","{" +
                "current: {" +
                "\"local_discovery\": true," +
                "\"access_token_enabled\": true," +
                "\"printer/local_printing_enabled\": true," +
                "\"printer/conversion_printing_enabled\": true," +
                "\"xmpp_timeout_value\": 300" +
                "}" +
                "}");
        parameters.put("semantic_state","{" +
                "\"version\": \"1.0\"," +
                "\"printer\": {" +
                "\"state\": \"STOPPED\"," +
                "\"marker_state\": {" +
                "\"item\": [" +
                "{\"vendor_id\": \"black\",\"state\": \"EXHAUSTED\",\"level_percent\": 0}," +
                "{\"vendor_id\": \"color\",\"state\": \"OK\",\"level_percent\": 88,\"level_pages\": 100}" +
                "]" +
                "}" +
                "}" +
                "}");
        parameters.put("use_cdd","true");

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

        // 2.- Capabilities:
        String capabilities = "{" +
                "\"version\": \"1.0\"," +
                "\"printer\": {" +
                "\"supported_content_type\": [" +
                "{\"content_type\": \"application/pdf\", \"min_version\": \"1.5\"}," +
                "{\"content_type\": \"image/jpeg\"}," +
                "{\"content_type\": \"text/plain\"}" +
                "]," +
                "\"input_tray_unit\": [" +
                "{\"vendor_id\": \"tray\",\"type\": \"INPUT_TRAY\"}" +
                "]," +
                "\"marker\": [" +
                "{\"vendor_id\": \"black\",\"type\": \"INK\",\"color\": {\"type\": \"BLACK\"}}," +
                "{\"vendor_id\": \"color\",\"type\": \"INK\",\"color\": {\"type\": \"COLOR\"}}" +
                "]," +
                "\"cover\": [" +
                "{\"vendor_id\": \"front\",\"type\": \"CUSTOM\",\"custom_display_name\": \"front cover\"}" +
                "]," +
                "\"vendor_capability\": []," +
                "\"color\": {" +
                "\"option\": [" +
                "{\"type\": \"STANDARD_MONOCHROME\"}," +
                "{\"type\": \"STANDARD_COLOR\", \"is_default\": true}," +
                "{\"vendor_id\": \"ultra-color\",\"type\": \"CUSTOM_COLOR\",\"custom_display_name\": \"Best Color\"}" +
                "]" +
                "}," +
                "\"copies\": {\"default\": 1,\"max\": 100}," +
                "\"media_size\": {" +
                "\"option\": [" +
                "{\"name\": \"ISO_A4\",\"width_microns\": 210000,\"height_microns\": 297000,\"is_default\": true}," +
                "{\"name\": \"NA_LEGAL\",\"width_microns\": 215900,\"height_microns\": 355600}," +
                "{\"name\": \"NA_LETTER\",\"width_microns\": 215900,\"height_microns\": 279400}" +
                "]" +
                "}" +
                "}" +
                "}";

        Files.write(Paths.get("capabilities.txt"), capabilities.getBytes(), StandardOpenOption.WRITE);
        File file = new File("capabilities.txt");

        FileContent fileContent = new FileContent("application/octet-stream",file);
        MultipartContent.Part part = new MultipartContent.Part(fileContent);
        part.setHeaders(new HttpHeaders().set("Content-Disposition",String.format("form-data; name=capabilities; filename=\"%s\"", file.getName())));
        content.addPart(part);

        HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(PRINT_URL + REGISTER),content).execute();
        return new Pair<>(response.getStatusCode(),response.parseAsString());
    }

    public static Pair<Integer,String> getAuthCode(String printerid) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");

        String get_auth_code_url = String.format(GCPrinter.PRINT_URL + GCPrinter.GET_AUTH_CODE,printerid,Credentials.CLIENT_ID);

        HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(get_auth_code_url),new EmptyContent()).setHeaders(headers).execute();
        return new Pair<>(response.getStatusCode(),response.parseAsString());
    }

    public static Pair<Integer,String> fetch(String access_token, String printerId) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");
        headers.setAuthorization("OAuth " + access_token);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("printerid",printerId);

        UrlEncodedContent content = new UrlEncodedContent(parameters);

        HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(PRINT_URL + FETCH), content).setHeaders(headers).execute();
        return new Pair<>(response.getStatusCode(),response.parseAsString());
    }
}
