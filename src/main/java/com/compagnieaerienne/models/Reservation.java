package com.compagnieaerienne.models;

import java.util.Date;

public class Reservation {
    private String numeroReservation;
    private Date dateReservation;
    private String statut;

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
}