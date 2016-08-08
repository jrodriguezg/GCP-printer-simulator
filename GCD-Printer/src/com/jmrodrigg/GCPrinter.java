package com.jmrodrigg;

import com.google.api.client.http.*;
import com.google.api.client.util.IOUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Pair;
import com.jmrodrigg.model.CJT.CloudJobTicket;
import com.jmrodrigg.model.PJS.Job;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.jmrodrigg.Common.requestFactory;

/**
 * Author: jrodriguezg
 * Date: 7/08/16
 */
public class GCPrinter implements CloudPrintConsts {

    public static Pair<Integer,String> register(String type) throws IOException, URISyntaxException {

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
                "\"state\": \"IDLE\"," +
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
        File file;

        switch (type) {
            case "A":
                file = new File((Main.class.getClassLoader().getResource("samples/capabilities_roll_A0.json")).toURI());
                break;
            case "R":
                file = new File((Main.class.getClassLoader().getResource("samples/capabilities_roll.json")).toURI());
                break;
            case "S":
            default:
                file = new File((Main.class.getClassLoader().getResource("samples/capabilities.json")).toURI());
                break;
        }

        FileContent fileContent = new FileContent("application/octet-stream",file);
        MultipartContent.Part part = new MultipartContent.Part(fileContent);
        part.setHeaders(new HttpHeaders().set("Content-Disposition",String.format("form-data; name=capabilities; filename=\"%s\"", file.getName())));
        content.addPart(part);

        HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(PRINT_URL + REGISTER),content).execute();
        return new Pair<>(response.getStatusCode(),response.parseAsString());
    }

    public static Pair<Integer,String> getAuthCode(String printerid, String clientid) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");

        String get_auth_code_url = String.format(GCPrinter.PRINT_URL + GCPrinter.GET_AUTH_CODE_URL,printerid,clientid);

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

    public static CloudJobTicket getJobTicket(String access_token, String jobId) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");
        headers.setAuthorization("OAuth " + access_token);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("jobid",jobId);
        parameters.put("use_cjt","true");

        UrlEncodedContent content = new UrlEncodedContent(parameters);

        HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(PRINT_URL + TICKET), content).setHeaders(headers).execute();

        Gson gson = new Gson();
        JsonObject printJobTicket = new JsonParser().parse(response.parseAsString()).getAsJsonObject();

        return gson.fromJson(printJobTicket, CloudJobTicket.class);
    }

    public static void downloadFile(String access_token, Job job, boolean force_raster) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");
        headers.setAuthorization("OAuth " + access_token);

        GenericUrl url;

        if (force_raster) url = new GenericUrl(job.rasterUrl);
        else url = new GenericUrl(job.fileUrl);

        HttpResponse response = requestFactory.buildPostRequest(url, new EmptyContent()).setHeaders(headers).execute();

        if (response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {

            String extension;

            switch (job.contentType) {
                case PrintJob.CONTENT_TYPE_JPG:
                    extension = ".jpg";
                    break;
                case PrintJob.CONTENT_TYPE_PDF:
                    extension = ".pdf";
                    break;
                default:
                    throw new IOException();
            }

            // Overwrite extension if flag "force_raster" is set to true:
            extension = force_raster ? ".pwg" : extension;

            InputStream is = response.getContent();
            FileOutputStream fos = new FileOutputStream(new File(job.title + extension));
            IOUtils.copy(is,fos);
            fos.close();
        } else System.out.println("Error downloading file.");
    }

    public static void printJob(String access_token, PrintJob job) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");
        headers.setAuthorization("OAuth " + access_token);

        // 1.- Update job status to "IN PROGRESS":
        Map<String, String> parameters = new HashMap<>();
        parameters.put("jobid",job.getJobId());
        parameters.put("semantic_state_diff","{\"state\": {\"type\": \"IN_PROGRESS\"}}");

        UrlEncodedContent content = new UrlEncodedContent(parameters);
        HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(PRINT_URL + CONTROL), content).setHeaders(headers).execute();

        if (response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
            int i = 0;

            try {
                // 2.- For each page, update the count:
                for (i=0; i<job.getPages(); i++) {
                    Thread.sleep(5000);

                    parameters = new HashMap<>();
                    parameters.put("jobid", job.getJobId());
                    parameters.put("semantic_state_diff", "{\"pages_printed\": " + (i + 1) + "}");

                    content = new UrlEncodedContent(parameters);
                    response = requestFactory.buildPostRequest(new GenericUrl(PRINT_URL + CONTROL), content).setHeaders(headers).execute();
                    if (response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                        System.out.println("Page " + (i + 1) + " printed.");

                        if ((i+1) < job.getPages()) {
                            System.out.print("(C)ontinue or (A)bort? ");
                            String action = new Scanner(System.in).next();
                            if (action.charAt(0) == 'A') throw new Exception();
                        }
                    } else {
                        // An error has occurred. Throw exception to abort the job.
                        throw new Exception();
                    }
                }

                // 3.- Set the job status to "DONE":
                parameters = new HashMap<>();
                parameters.put("jobid",job.getJobId());
                parameters.put("semantic_state_diff","{\"state\": {\"type\": \"DONE\"},\n\"pages_printed\": "+ i +"}");

                content = new UrlEncodedContent(parameters);
                requestFactory.buildPostRequest(new GenericUrl(PRINT_URL + CONTROL), content).setHeaders(headers).execute();

            } catch (Exception ex) {
                System.out.println("Print job aborted.");

                // 3.- Set the job status to "DONE":
                parameters = new HashMap<>();
                parameters.put("jobid",job.getJobId());
                parameters.put("semantic_state_diff","{\"state\": {\"type\": \"ABORTED\",\"user_action_cause\": {\"action_code\": \"CANCELLED\"}},\n\"pages_printed\": "+ i +"}");

                content = new UrlEncodedContent(parameters);
                requestFactory.buildPostRequest(new GenericUrl(PRINT_URL + CONTROL), content).setHeaders(headers).execute();
            }
        }
    }

    public static Pair<Integer,String> updatePrinterState(String access_token, String printerId, String printerstate) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");
        headers.setAuthorization("OAuth " + access_token);

        // 1.- Update job status to "IN PROGRESS":
        Map<String, String> parameters = new HashMap<>();
        parameters.put("printerid",printerId);
        parameters.put("semantic_state_diff","{\"printer\": {\"state\": \" " + printerstate + "\"}}");

        UrlEncodedContent content = new UrlEncodedContent(parameters);
        HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(PRINT_URL + UPDATE), content).setHeaders(headers).execute();

        return new Pair<>(response.getStatusCode(),response.parseAsString());
    }

    public static Pair<Integer,String> printer(String access_token,String printerid) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");
        headers.setAuthorization("OAuth " + access_token);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("printerid",printerid);

        UrlEncodedContent content = new UrlEncodedContent(parameters);

        HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(PRINT_URL + PRINTER), content).setHeaders(headers).execute();
        return new Pair<>(response.getStatusCode(),response.parseAsString());
    }
}
