package io.openliberty.guides.providers;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class GoogleOAuthProvider implements OAuthProvider {

    @Override
    public String getClientId() {
        return "CLIENTE";
    }

    @Override
    public String getClientSecret() {
        return "SECRETO";
    }

    @Override
    public String getRedirectUri() {
        return "http://localhost:5173";
    }

    @Override
    public String getTokenEndpoint() {
        return "https://oauth2.googleapis.com/token";
    }

    @Override
    public String getUserInfoEndpoint() {
        return "https://openidconnect.googleapis.com/v1/userinfo";
    }
}