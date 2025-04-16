package com.compagnieaerienne.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {
    private String numeroReservation;
    private Date dateReservation;
    private String statut;
    private static List<Reservation> listeReservations = new ArrayList<>();

    public Reservation(String numeroReservation, Date dateReservation, String statut) {
        this.numeroReservation = numeroReservation;
        this.dateReservation = dateReservation;
        this.statut = statut;
    }

    public void confirmerReservation() {
        this.statut = "Confirmée";
        System.out.println("La réservation " + numeroReservation + " a été confirmée.");
    }

    public void annulerReservation() {
        this.statut = "Annulée";
        System.out.println("La réservation " + numeroReservation + " a été annulée.");
    }

    public void modifierReservation(Date nouvelleDate) {
        this.dateReservation = nouvelleDate;
        System.out.println("La réservation " + numeroReservation + " a été modifiée.");
    }

    public String getNumeroReservation() {
        return numeroReservation;
    }

    public void setNumeroReservation(String numeroReservation) {
        this.numeroReservation = numeroReservation;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getstatut() {
        return statut;
    }

    public void setstatut(String statut) {
        this.statut = statut;
    }

    public static void ajouterReservation(Reservation reservation) {
        listeReservations.add(reservation);
    }

    public static Reservation chercherReservation(String numeroReservation) {
        for (Reservation r : listeReservations) {
            if (r.getNumeroReservation().equals(numeroReservation)) return r;
        }
        return null;
    }

    public static void modifierReservation(String numeroReservation, Reservation nouvelleReservation) {
        for (int i = 0; i < listeReservations.size(); i++) {
            if (listeReservations.get(i).getNumeroReservation().equals(numeroReservation)) {
                listeReservations.set(i, nouvelleReservation);
                break;
            }
        }
    }

    public static void supprimerReservation(String numeroReservation) {
        listeReservations.removeIf(r -> r.getNumeroReservation().equals(numeroReservation));
    }
}
