package com.compagnieaerienne.models;

import java.util.ArrayList;
import java.util.List;

public class Passager extends Personne {
    private String passport;
    private List<Reservation> reservations;
    private static List<Passager> listePassagers = new ArrayList<>();

    public Passager(String identifiant, String nom, String adresse, String contact, String passport) {
        super(identifiant, nom, adresse, contact);
        this.passport = passport;
        this.reservations = new ArrayList<>();
    }

    public void reserverVol(Vol vol) {
        Reservation reservation = new Reservation(vol.getNumeroVol(), vol.getDateHeureDepart(), "Réservée");
        reservations.add(reservation);
    }

    public void annulerReservation(String numeroReservation) {
        reservations.removeIf(reservation -> reservation.getNumeroReservation().equals(numeroReservation));
    }

    public void obtenirReservations() {
        if (reservations.isEmpty()) {
            System.out.println("Aucune réservation trouvée pour " + getNom());
        } else {
            for (Reservation reservation : reservations) {
                System.out.println("Réservation n°: " + reservation.getNumeroReservation() + " | Date : " + reservation.getDateReservation() + " | Statut : " + reservation.getstatut());
            }
        }
    }

    public Reservation obtenirReservations(String numeroReservation) {
        for (Reservation reservation : reservations) {
            if (reservation.getNumeroReservation().equals(numeroReservation)) {
                return reservation;
            }
        }
        return null;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public static void ajouterPassager(Passager passager) {
        listePassagers.add(passager);
    }

    public static Passager chercherPassager(String identifiant) {
        for (Passager p : listePassagers) {
            if (p.getIdentifiant().equals(identifiant)) return p;
        }
        return null;
    }

    public static void modifierPassager(String identifiant, Passager nouveauPassager) {
        for (int i = 0; i < listePassagers.size(); i++) {
            if (listePassagers.get(i).getIdentifiant().equals(identifiant)) {
                listePassagers.set(i, nouveauPassager);
                break;
            }
        }
    }

    public static void supprimerPassager(String identifiant) {
        listePassagers.removeIf(p -> p.getIdentifiant().equals(identifiant));
    }
}
