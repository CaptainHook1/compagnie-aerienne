package com.compagnieaerienne.models;

import java.util.ArrayList;
import java.util.List;

public class PersonnelCabine extends Employe {
    private String qualification;
    private static List<PersonnelCabine> listePersonnelCabine = new ArrayList<>();

    public PersonnelCabine(String identifiant, String nom, String adresse, String contact, String numeroEmploye, String dateEmbauche, String qualification) {
        super(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.qualification = qualification;
    }

    public void affecterVol() {
        System.out.println("Le personnel cabine " + getNumeroEmploye() + " a été affecté à un vol.");
    }

    public void obtenirVol() {
        System.out.println("Vols affectés au personnel cabine " + getNumeroEmploye());
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public static void ajouterPersonnelCabine(PersonnelCabine personnel) {
        listePersonnelCabine.add(personnel);
    }

    public static PersonnelCabine chercherPersonnelCabine(String identifiant) {
        for (PersonnelCabine p : listePersonnelCabine) {
            if (p.getIdentifiant().equals(identifiant)) return p;
        }
        return null;
    }

    public static void modifierPersonnelCabine(String identifiant, PersonnelCabine nouveauPersonnel) {
        for (int i = 0; i < listePersonnelCabine.size(); i++) {
            if (listePersonnelCabine.get(i).getIdentifiant().equals(identifiant)) {
                listePersonnelCabine.set(i, nouveauPersonnel);
                break;
            }
        }
    }

    public static void supprimerPersonnelCabine(String identifiant) {
        listePersonnelCabine.removeIf(p -> p.getIdentifiant().equals(identifiant));
    }
}
