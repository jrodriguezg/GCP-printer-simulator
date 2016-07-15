package com.jmrodrigg;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.http.*;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.jmrodrigg.Common.HTTP_TRANSPORT;
import static com.jmrodrigg.Common.JSON_FACTORY;

/**
 * Author: jrodriguezg
 * Date: 7/10/16
 */
public class OAuth {

    private static final String OAUTH_URL = "https://accounts.google.com/o/oauth2/token";
    private final String GRANT_TYPE_AUTH = "authorization_code";
    private final String GRANT_TYPE_REFRESH = "refresh_token";

    private final String redirect_uri = "oob";

    private String code;
    private String access_token;
    private String refresh_token;
    private long expiresInSecs;

    private Credentials credentials;

    public OAuth(String code, String refresh_token,InputStream json_url) {
        credentials = new Credentials(json_url);

        this.code = code;
        this.refresh_token = refresh_token;
    }

    public void setAuthorizationCode(String code) {
        this.code = code;
    }

    public void setRefreshToken(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public boolean authorize(boolean refresh) {

        // TODO check null credentials,code,refresh_token

        HttpHeaders headers = new HttpHeaders();
        headers.put("X-CloudPrint-Proxy","");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("client_id",credentials.getClientID());
        parameters.put("redirect_uri",redirect_uri);
        parameters.put("client_secret",credentials.getClientSecret());
        if (!refresh) {
            parameters.put("grant_type",GRANT_TYPE_AUTH);
            parameters.put("code",code);
        } else {
            parameters.put("grant_type",GRANT_TYPE_REFRESH);
            parameters.put("refresh_token",refresh_token);
        }

        UrlEncodedContent content = new UrlEncodedContent(parameters);

        try {
            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(request -> request.setParser(new JsonObjectParser(JSON_FACTORY)));
            HttpResponse response = requestFactory.buildPostRequest(new GenericUrl(OAUTH_URL), content).setHeaders(headers).execute();

            JsonObject object = new JsonParser().parse(response.parseAsString()).getAsJsonObject();
            access_token = object.get("access_token").getAsString();
            if (!refresh) refresh_token = object.get("refresh_token").getAsString();
            expiresInSecs = object.get("expires_in").getAsLong();
            return true;

        } catch (IOException ex) {
            return false;
        }
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public String getAccessToken() {
        return this.access_token;
    }

    public String getRefreshToken() {
        return this.refresh_token;
    }



}
