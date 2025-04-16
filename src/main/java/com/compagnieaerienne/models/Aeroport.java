package com.compagnieaerienne.models;

import java.util.ArrayList;
import java.util.List;

public class Aeroport {
    private String nom;
    private String ville;
    private String description;
    private List<Vol> volsArrivee;
    private List<Vol> volsDepart;
    private static List<Aeroport> listeAeroports = new ArrayList<>();

    public Aeroport(String nom, String ville, String description) {
        this.nom = nom;
        this.ville = ville;
        this.description = description;
        this.volsArrivee = new ArrayList<>();
        this.volsDepart = new ArrayList<>();
    }

    public void affecterVol(Vol vol, boolean estDepart) {
        if (estDepart) {
            volsDepart.add(vol);
        } else {
            volsArrivee.add(vol);
        }
        System.out.println("Le vol " + vol.getNumeroVol() + " a été affecté à l'aéroport " + nom);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Vol> getVolsArrivee() {
        return volsArrivee;
    }

    public void setVolsArrivee(List<Vol> volsArrivee) {
        this.volsArrivee = volsArrivee;
    }

    public List<Vol> getVolsDepart() {
        return volsDepart;
    }

    public void setVolsDepart(List<Vol> volsDepart) {
        this.volsDepart = volsDepart;
    }

    public static void ajouterAeroport(Aeroport aeroport) {
        listeAeroports.add(aeroport);
    }

    public static Aeroport chercherAeroport(String nom) {
        for (Aeroport a : listeAeroports) {
            if (a.getNom().equals(nom)) return a;
        }
        return null;
    }

    public static void modifierAeroport(String nom, Aeroport nouvelAeroport) {
        for (int i = 0; i < listeAeroports.size(); i++) {
            if (listeAeroports.get(i).getNom().equals(nom)) {
                listeAeroports.set(i, nouvelAeroport);
                break;
            }
        }
    }

    public static void supprimerAeroport(String nom) {
        listeAeroports.removeIf(a -> a.getNom().equals(nom));
    }
}
