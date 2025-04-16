package com.compagnieaerienne.models;

import java.util.ArrayList;
import java.util.List;

public class Pilote extends Employe {
    private String licence;
    private int heuresDeVol;
    private static List<Pilote> listePilotes = new ArrayList<>();

    public Pilote(String identifiant, String nom, String adresse, String contact, String numeroEmploye, String dateEmbauche, String licence, int heuresDeVol) {
        super(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.licence = licence;
        this.heuresDeVol = heuresDeVol;
    }

    public void affecterVol() {
        System.out.println("Le pilote " + getNumeroEmploye() + " a été affecté à un vol.");
    }

    public void obtenirVol() {
        System.out.println("Vols affectés au pilote " + getNumeroEmploye());
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public int getHeuresDeVol() {
        return heuresDeVol;
    }

    public void setHeuresDeVol(int heuresDeVol) {
        this.heuresDeVol = heuresDeVol;
    }

    public static void ajouterPilote(Pilote pilote) {
        listePilotes.add(pilote);
    }

    public static Pilote chercherPilote(String identifiant) {
        for (Pilote p : listePilotes) {
            if (p.getIdentifiant().equals(identifiant)) return p;
        }
        return null;
    }

    public static void modifierPilote(String identifiant, Pilote nouveauPilote) {
        for (int i = 0; i < listePilotes.size(); i++) {
            if (listePilotes.get(i).getIdentifiant().equals(identifiant)) {
                listePilotes.set(i, nouveauPilote);
                break;
            }
        }
    }

    public static void supprimerPilote(String identifiant) {
        listePilotes.removeIf(p -> p.getIdentifiant().equals(identifiant));
    }
}
