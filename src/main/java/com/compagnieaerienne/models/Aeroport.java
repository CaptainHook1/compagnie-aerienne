package com.compagnieaerienne.models;

import java.util.List;

public class Aeroport {
    private String nom;
    private String ville;
    private String description;
    private List<Vol> volsArrivee;
    private List<Vol> volsDepart;

    public Aeroport(String nom, String ville, String description) {
        this.nom = nom;
        this.ville = ville;
        this.description = description;
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
}