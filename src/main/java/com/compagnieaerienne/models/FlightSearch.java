package com.compagnieaerienne.models;
//c'est pour les tests manuels

import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class FlightSearch {
    private static final String API_URL = "https://test.api.amadeus.com/v2/shopping/flight-offers";

    public static void main(String[] args) throws IOException {
        String accessToken = AmadeusAuth.getAccessToken();
        if (accessToken == null) {
            System.err.println("Impossible d’obtenir le token.");
            return;
        }

        OkHttpClient client = new OkHttpClient();

        // 🔽 Corps JSON à envoyer dans la requête POST
        String jsonRequestBody = """
        {
          "currencyCode": "EUR",
          "originDestinations": [
            {
              "id": "1",
              "originLocationCode": "NYC",
              "destinationLocationCode": "MAD",
              "departureDateTimeRange": {
                "date": "2025-06-01",
                "time": "10:00:00"
              }
            }
          ],
          "travelers": [
            {
              "id": "1",
              "travelerType": "ADULT"
            }
          ],
          "sources": ["GDS"],
          "searchCriteria": {
            "maxFlightOffers": 10
          }
        }
        """;

        RequestBody body = RequestBody.create(
                jsonRequestBody, MediaType.get("application/vnd.amadeus+json")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + accessToken)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            String json = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> result = mapper.readValue(json, Map.class);
            System.out.println(result);
        } else {
            System.err.println("Erreur : " + response.code() + " - " + response.message());
            System.err.println("Réponse : " + response.body().string());
        }
    }
}