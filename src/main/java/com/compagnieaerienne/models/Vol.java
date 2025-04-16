package com.compagnieaerienne.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vol {
    private String numeroVol;
    private String origine;
    private String destination;
    private Date dateHeureDepart;
    private Date dateHeureArrivee;
    private String etat;
    private Avion avion;
    private static List<Vol> listeVols = new ArrayList<>();

    public Vol(String numeroVol, String origine, String destination,
               Date dateHeureDepart, Date dateHeureArrivee, String etat, Avion avion) {
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = etat;
        this.avion = avion;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public void planifierVol() {
        System.out.println("Le vol " + numeroVol + " est maintenant planifié.");
    }

    public void annulerVol() {
        this.etat = "Annulé";
        System.out.println("Le vol " + numeroVol + " a été annulé.");
    }

    public void modifierVol(String origine, String destination, Date dateHeureDepart, Date dateHeureArrivee) {
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        System.out.println("Le vol " + numeroVol + " a été modifié.");
    }

    public void listingPassager() {
        System.out.println("Liste des passagers pour le vol " + numeroVol);
    }

    public String getNumeroVol() {
        return numeroVol;
    }

    public void setNumeroVol(String numeroVol) {
        this.numeroVol = numeroVol;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDateHeureDepart() {
        return dateHeureDepart;
    }

    public void setDateHeureDepart(Date dateHeureDepart) {
        this.dateHeureDepart = dateHeureDepart;
    }

    public Date getDateHeureArrivee() {
        return dateHeureArrivee;
    }

    public void setDateHeureArrivee(Date dateHeureArrivee) {
        this.dateHeureArrivee = dateHeureArrivee;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public static void ajouterVol(Vol vol) {
        listeVols.add(vol);
    }

    public static Vol chercherVol(String numeroVol) {
        for (Vol v : listeVols) {
            if (v.getNumeroVol().equals(numeroVol)) return v;
        }
        return null;
    }

    public static void modifierVol(String numeroVol, Vol nouveauVol) {
        for (int i = 0; i < listeVols.size(); i++) {
            if (listeVols.get(i).getNumeroVol().equals(numeroVol)) {
                listeVols.set(i, nouveauVol);
                break;
            }
        }
    }

    public static void supprimerVol(String numeroVol) {
        listeVols.removeIf(v -> v.getNumeroVol().equals(numeroVol));
    }
}
