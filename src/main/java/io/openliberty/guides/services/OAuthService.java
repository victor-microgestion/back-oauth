package io.openliberty.guides.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.openliberty.guides.providers.OAuthProvider;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class OAuthService {

    private final OAuthProvider provider;
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OAuthService(OAuthProvider provider) {
        this.provider = provider;
    }

    public Map<String, Object> getUserInfo(String accessToken) throws Exception {
        HttpRequest userInfoRequest = HttpRequest.newBuilder()
                .uri(URI.create(provider.getUserInfoEndpoint()))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> userInfoResponse = client.send(userInfoRequest, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(userInfoResponse.body(), Map.class);
    }
}
