package com.compagnieaerienne.models;

import java.util.ArrayList;
import java.util.List;

public class Employe extends Personne {
    private String numeroEmploye;
    private String dateEmbauche;
    private static List<Employe> listeEmployes = new ArrayList<>();

    public Employe(String identifiant, String nom, String adresse, String contact, String numeroEmploye, String dateEmbauche) {
        super(identifiant, nom, adresse, contact);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }

    public void obtenirRole() {
        if (this instanceof Pilote) {
            System.out.println("Rôle de l'employé : Pilote");
        } else if (this instanceof PersonnelCabine) {
            System.out.println("Rôle de l'employé : Personnel Cabine");
        } else {
            System.out.println("Rôle de l'employé : Autre");
        }
    }

    public String getNumeroEmploye() {
        return numeroEmploye;
    }

    public void setNumeroEmploye(String numeroEmploye) {
        this.numeroEmploye = numeroEmploye;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public static void ajouterEmploye(Employe employe) {
        listeEmployes.add(employe);
    }

    public static Employe chercherEmploye(String identifiant) {
        for (Employe e : listeEmployes) {
            if (e.getIdentifiant().equals(identifiant)) return e;
        }
        return null;
    }

    public static void modifierEmploye(String identifiant, Employe nouveau) {
        for (int i = 0; i < listeEmployes.size(); i++) {
            if (listeEmployes.get(i).getIdentifiant().equals(identifiant)) {
                listeEmployes.set(i, nouveau);
                break;
            }
        }
    }

    public static void supprimerEmploye(String identifiant) {
        listeEmployes.removeIf(e -> e.getIdentifiant().equals(identifiant));
    }
}
