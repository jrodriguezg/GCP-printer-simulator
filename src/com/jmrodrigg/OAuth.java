package com.jmrodrigg;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jrodriguez on 10/07/16.
 */
public class OAuth {

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static final String OAUTH_URL = "https://accounts.google.com/o/oauth2/token";
    private final String GRANT_TYPE_AUTH = "authorization_code";
    private final String GRANT_TYPE_REFRESH = "refresh_token";

    private final String redirect_uri = "oob";

    private String code;
    private String access_token;
    private String refresh_token;
    private long expiresInSecs;


    public OAuth(String aCode) {
        code = aCode;
    }

    public boolean authorize() {

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("client_id",Credentials.CLIENT_ID);
        parameters.put("redirect_uri",redirect_uri);
        parameters.put("client_secret",Credentials.CLIENT_SECRET);
        parameters.put("grant_type",GRANT_TYPE_AUTH);
        parameters.put("code",code);

        UrlEncodedContent content = new UrlEncodedContent(parameters);

        try {
            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(request -> request.setParser(new JsonObjectParser(JSON_FACTORY)));
            HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(OAUTH_URL), content).setHeaders(headers).execute();

            JsonObject object = new JsonParser().parse(response.parseAsString()).getAsJsonObject();
            access_token = object.get("access_token").getAsString();
            refresh_token = object.get("refresh_token").getAsString();
            expiresInSecs = object.get("expires_in").getAsLong();
            return true;

        } catch (IOException ex) { return false; }
    }

    public String getAccessToken() {
        return this.access_token;
    }

}
