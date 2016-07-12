package com.jmrodrigg;

import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.internal.Pair;

import java.io.InputStreamReader;

/**
 * Author: jrodriguezg
 * Date: 7/10/16.
 */
public class Credentials {

    /*
     * Use OAuth credentials obtained from https://console.developers.google.com after creating your project.
     * Place them in /res/keys/api_key.json
     */
    public static Pair<String,String> getCredentials() {
        JsonObject obj =  new JsonParser().parse(new InputStreamReader(Credentials.class.getClassLoader().getResourceAsStream("keys/api_key.json"))).getAsJsonObject();

        if (obj.get("client_id").getAsString().startsWith("Enter ") || obj.get("client_id").getAsString().startsWith("Enter "))
            handleCredentialsNotDefineError();

        return new Pair<>(obj.get("client_id").getAsString(),obj.get("client_secret").getAsString());
    }

    public static String getClientID() {
        JsonObject obj =  new JsonParser().parse(new InputStreamReader(Credentials.class.getClassLoader().getResourceAsStream("keys/api_key.json"))).getAsJsonObject();

        if (obj.get("client_id").getAsString().startsWith("Enter "))
            handleCredentialsNotDefineError();

        return obj.get("client_id").getAsString();
    }

    public static String getClientSecret() {
        JsonObject obj =  new JsonParser().parse(new InputStreamReader(Credentials.class.getClassLoader().getResourceAsStream("keys/api_key.json"))).getAsJsonObject();

        if (obj.get("client_secret").getAsString().startsWith("Enter "))
            handleCredentialsNotDefineError();

        return obj.get("client_secret").getAsString();
    }

    private static void handleCredentialsNotDefineError() {
        System.out.println("Enter client_id and client_secret from https://console.developers.google.com into /res/keys/api-key.json");
        System.exit(0);
    }
}
