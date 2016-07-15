package com.jmrodrigg;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Pair;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Author: jrodriguezg
 * Date: 7/10/16.
 */
public class Credentials {

    JsonObject credentials;
    
    public Credentials(InputStream json_url) {
        credentials = new JsonParser().parse(new InputStreamReader(json_url)).getAsJsonObject();
    }

    /*
     * Use OAuth credentials obtained from https://console.developers.google.com after creating your project.
     * Place them in /res/keys/api_key.json
     */
    public Pair<String,String> getCredentials() {
        if (credentials.get("client_id").getAsString().startsWith("Enter ") || credentials.get("client_id").getAsString().startsWith("Enter "))
            handleCredentialsNotDefineError();

        return new Pair<>(credentials.get("client_id").getAsString(),credentials.get("client_secret").getAsString());
    }

    public String getClientID() {
        if (credentials.get("client_id").getAsString().startsWith("Enter "))
            handleCredentialsNotDefineError();

        return credentials.get("client_id").getAsString();
    }

    public String getClientSecret() {
        if (credentials.get("client_secret").getAsString().startsWith("Enter "))
            handleCredentialsNotDefineError();

        return credentials.get("client_secret").getAsString();
    }

    private static void handleCredentialsNotDefineError() {
        System.out.println("Enter client_id and client_secret from https://console.developers.google.com into /res/keys/api-key.json");
        System.exit(0);
    }
}
