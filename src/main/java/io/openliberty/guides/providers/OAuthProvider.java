package io.openliberty.guides.providers;

public interface OAuthProvider {
    String getClientId();
    String getClientSecret();
    String getRedirectUri();
    String getTokenEndpoint();
    String getUserInfoEndpoint();
}