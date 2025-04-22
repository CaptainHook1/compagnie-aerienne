package com.compagnieaerienne.models;

import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.io.PrintWriter;

public class ImportationVols {
    private static final String API_URL = "https://test.api.amadeus.com/v2/shopping/flight-offers";

    public static void importerVolsDepuisAPI(String cheminFichier) throws IOException {
        String accessToken = AmadeusAuth.getAccessToken();
        if (accessToken == null) return;

        new PrintWriter(cheminFichier).close();

        OkHttpClient client = new OkHttpClient();

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

        RequestBody body = RequestBody.create(jsonRequestBody, MediaType.get("application/vnd.amadeus+json"));

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + accessToken)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            String json = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> donnees = mapper.readValue(json, Map.class);
            List<Map<String, Object>> vols = (List<Map<String, Object>>) donnees.get("data");
            Map<String, Object> dictionnaires = (Map<String, Object>) donnees.get("dictionaries");
            Map<String, String> aircraftDict = (Map<String, String>) dictionnaires.get("aircraft");

            int compteur = 1;
            for (Map<String, Object> vol : vols) {
                List<Map<String, Object>> itineraires = (List<Map<String, Object>>) vol.get("itineraries");
                Map<String, Object> itineraire = itineraires.get(0);
                List<Map<String, Object>> segments = (List<Map<String, Object>>) itineraire.get("segments");

                Map<String, Object> depart = (Map<String, Object>) segments.get(0).get("departure");
                Map<String, Object> arrivee = (Map<String, Object>) segments.get(segments.size() - 1).get("arrival");

                String origine = (String) depart.get("iataCode");
                String destination = (String) arrivee.get("iataCode");
                String dateDepartISO = (String) depart.get("at");
                String dateArriveeISO = (String) arrivee.get("at");

                try {
                    Date dateDepart = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateDepartISO);
                    Date dateArrivee = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateArriveeISO);

                    Map<String, Object> aircraft = (Map<String, Object>) segments.get(0).get("aircraft");
                    String aircraftCode = (String) aircraft.get("code");
                    String modeleAvion = aircraftDict.getOrDefault(aircraftCode, "Modèle inconnu");

                    String numeroVol = "API" + compteur;
                    compteur++;

                    Avion avionAssocie = new Avion("A" + numeroVol, modeleAvion, 180);
                    Avion.ajouterAvion(avionAssocie);

                    Vol nouveauVol = new Vol(numeroVol, origine, destination, dateDepart, dateArrivee, "Planifié", avionAssocie);
                    Vol.ajouterVol(nouveauVol, cheminFichier);
                } catch (ParseException e) {
                    // Erreur de parsing
                }
            }
        }
    }
}
