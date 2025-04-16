package com.compagnieaerienne.models;

import com.compagnieaerienne.models.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Vol> vols = new ArrayList<>();
        List<Passager> passagers = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();

        Avion avion1 = new Avion("AB123", "Boeing 737", 200);
        Avion avion2 = new Avion("AB124", "Airbus A320", 180);
        Avion avion3 = new Avion("AB125", "Boeing 777", 300);

        Vol vol1 = new Vol("AF123", "Paris", "New York", new Date(), new Date(), "Planifié", avion1);
        Vol vol2 = new Vol("AF124", "Paris", "Tokyo", new Date(), new Date(), "Planifié", avion2);
        Vol vol3 = new Vol("AF125", "Paris", "Tokyo", new Date(), new Date(), "Planifié", avion3);

        vols.add(vol1);
        vols.add(vol2);
        vols.add(vol3);

        Passager passager1 = new Passager("123", "Jean Dupont", "123 rue de Paris", "0123456789", "P12345678");
        Passager passager2 = new Passager("124", "Marie Dupont", "456 rue de Lyon", "0123456790", "P98765432");
        passagers.add(passager1);
        passagers.add(passager2);

        passager1.reserverVol(vol1);
        passager2.reserverVol(vol2);

        Reservation reservation1 = new Reservation("R123", new Date(), "Confirmée");
        Reservation reservation2 = new Reservation("R124", new Date(), "Confirmée");
        reservations.add(reservation1);
        reservations.add(reservation2);

        int nombreDeVols = vols.size();
        int passagersTransportes = passagers.size();
        double revenusGeneres = nombreDeVols * 1000;

        System.out.println("Rapport des vols :");
        System.out.println("Nombre de vols : " + nombreDeVols);
        System.out.println("Passagers transportés : " + passagersTransportes);
        System.out.println("Revenus générés : " + revenusGeneres + "€");

        Map<String, Integer> destinations = new HashMap<>();
        for (Vol vol : vols) {
            destinations.put(vol.getDestination(), destinations.getOrDefault(vol.getDestination(), 0) + 1);
        }

        System.out.println("\nDestinations les plus populaires :");
        destinations.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " vol(s)"));

        passager1.obtenirReservations();
        passager2.obtenirReservations();

        Reservation reservationTrouvee = passager1.obtenirReservations("R123");
        if (reservationTrouvee != null) {
            System.out.println("Réservation trouvée : " + reservationTrouvee.getNumeroReservation());
        } else {
            System.out.println("Réservation non trouvée."); //ça renverra non trouvée car elle R123 n'existe pas :)
        }
    }
}


