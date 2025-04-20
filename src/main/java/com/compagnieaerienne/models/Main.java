package com.compagnieaerienne.models;

import com.compagnieaerienne.models.Passager;
import com.compagnieaerienne.models.Reservation;
import com.compagnieaerienne.models.Vol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        List<Passager> passagers = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();

        List<Vol> vols = Vol.importerVolsDepuisTxt("vols.txt");

        if (vols.isEmpty()) {
            System.out.println("Aucun vol n'a été importé. Vérifiez le fichier TXT.");
            return;
        }

        System.out.println("Nombre de vols importés: " + vols.size());

        Passager passager1 = new Passager("123", "Jean Dupont", "123 rue de Paris", "0123456789", "P12345678");
        Passager passager2 = new Passager("124", "Marie Dupont", "456 rue de Lyon", "0123456790", "P98765432");
        passagers.add(passager1);
        passagers.add(passager2);

        passager1.reserverVol(vols.get(0));
        passager2.reserverVol(vols.get(1));

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
            System.out.println("Réservation non trouvée.");
        }

        Vol.ajouterVol(new Vol("HP567", "Paris", "Bordeaux",
                        new Date(2025, 7, 27, 12, 30),
                        new Date(2025, 7, 27, 13, 5),
                        "Planifié", new Avion("LT68955", "Boeing", 200)),
                "vols.txt");
    }
}