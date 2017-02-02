package com.jmrodrigg;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.http.*;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.Calendar;
import java.util.Collections;

import static com.jmrodrigg.Common.HTTP_TRANSPORT;
import static com.jmrodrigg.Common.JSON_FACTORY;

/**
 * Author: jrodriguezg
 * Date: 7/15/16
 */
public class OAuth {

    private static final String OAUTH_URL = "https://accounts.google.com/o/oauth2/token";
    private final String GRANT_TYPE_AUTH = "authorization_code";
    private final String GRANT_TYPE_REFRESH = "refresh_token";

    private final String redirect_uri = "oob";

    private Credential user_credentials;

    private Credentials credentials;

    public OAuth(InputStream json_url) {
        credentials = new Credentials(json_url);
    }

    public String getAccessToken() {
        return this.user_credentials.getAccessToken();
    }

    public String getRefreshToken() {
        return this.user_credentials.getRefreshToken();
    }

    public boolean authorize(String user) throws SocketTimeoutException {

        try {
            final int PORT = 8080;
            final String DOMAIN = "127.0.0.1";
            final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".store/gcp_auth_store");
            FileDataStoreFactory DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
            final String SCOPE = "https://www.googleapis.com/auth/cloudprint";
            final String AUTHORIZATION_SERVER_URL = "https://accounts.google.com/o/oauth2/v2/auth";

            // set up authorization code flow:
            AuthorizationCodeFlow flow = new AuthorizationCodeFlow.Builder(BearerToken
                    .authorizationHeaderAccessMethod(),
                    HTTP_TRANSPORT,
                    JSON_FACTORY,
                    new GenericUrl(OAUTH_URL),
                    new ClientParametersAuthentication(
                            credentials.getClientID(), credentials.getClientSecret()),
                    credentials.getClientID(),
                    AUTHORIZATION_SERVER_URL).setScopes(Collections.singletonList(SCOPE))
                    .setDataStoreFactory(DATA_STORE_FACTORY).build();
            // authorize
            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setHost(
                    DOMAIN).setPort(PORT).build();
            user_credentials = new AuthorizationCodeInstalledApp(flow, receiver).authorize(user);

            // Check expiration time to see if we need to renew token:
            if (user_credentials.getExpirationTimeMilliseconds() < Calendar.getInstance().getTimeInMillis()) {
                return user_credentials.refreshToken();
            } else return true;

        } catch (SocketTimeoutException ex) {
            throw ex;
        } catch (IOException ex) {
            return false;
        }
    }
}
