package com.compagnieaerienne.models;

import java.util.ArrayList;
import java.util.List;

public class Personne {
    private String identifiant;
    private String nom;
    private String adresse;
    private String contact;
    private static List<Personne> listePersonnes = new ArrayList<>();

    public Personne(String identifiant, String nom, String adresse, String contact) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
    }

    public void obtenirInfos() {
        System.out.println("Identifiant : " + identifiant);
        System.out.println("Nom : " + nom);
        System.out.println("Adresse : " + adresse);
        System.out.println("Contact : " + contact);
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public static void ajouterPersonne(Personne personne) {
        listePersonnes.add(personne);
    }

    public static Personne chercherPersonne(String identifiant) {
        for (Personne p : listePersonnes) {
            if (p.getIdentifiant().equals(identifiant)) return p;
        }
        return null;
    }

    public static void modifierPersonne(String identifiant, Personne nouvellePersonne) {
        for (int i = 0; i < listePersonnes.size(); i++) {
            if (listePersonnes.get(i).getIdentifiant().equals(identifiant)) {
                listePersonnes.set(i, nouvellePersonne);
                break;
            }
        }
    }

    public static void supprimerPersonne(String identifiant) {
        listePersonnes.removeIf(p -> p.getIdentifiant().equals(identifiant));
    }
}
