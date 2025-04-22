package com.compagnieaerienne.models;

import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;

public class AmadeusAuth {
    private static final String API_KEY = "numGNahbGU1j5m2f8unfLYvM3xlnx6bt";
    private static final String API_SECRET = "epomjhxuxYEjcNHn";
    private static final String TOKEN_URL = "https://test.api.amadeus.com/v1/security/oauth2/token";

    public static String getAccessToken() throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("grant_type", "client_credentials")
                .add("client_id", API_KEY)
                .add("client_secret", API_SECRET)
                .build();

        Request request = new Request.Builder()
                .url(TOKEN_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> json = mapper.readValue(response.body().string(), Map.class);
                return (String) json.get("access_token");
            } else {
                System.err.println("Erreur d'authentification : " + response.code() + " - " + response.message());
                return null;
            }
        }
    }
}
