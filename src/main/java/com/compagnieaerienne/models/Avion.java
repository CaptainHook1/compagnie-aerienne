package com.compagnieaerienne.models;

import java.util.ArrayList;
import java.util.List;

public class Avion {
    private String immatriculation;
    private String modele;
    private int capacite;
    private static List<Avion> listeAvions = new ArrayList<>();

    public Avion(String immatriculation, String modele, int capacite) {
        this.immatriculation = immatriculation;
        this.modele = modele;
        this.capacite = capacite;
    }

    public void affecterVol() {
        System.out.println("L'avion " + immatriculation + " a été affecté à un vol.");
    }

    public void verifierDisponibilite() {
        System.out.println("Vérification de la disponibilité de l'avion " + immatriculation + ".");
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public static void ajouterAvion(Avion avion) {
        listeAvions.add(avion);
    }

    public static Avion chercherAvion(String immatriculation) {
        for (Avion a : listeAvions) {
            if (a.getImmatriculation().equals(immatriculation)) return a;
        }
        return null;
    }

    public static void modifierAvion(String immatriculation, Avion nouvelAvion) {
        for (int i = 0; i < listeAvions.size(); i++) {
            if (listeAvions.get(i).getImmatriculation().equals(immatriculation)) {
                listeAvions.set(i, nouvelAvion);
                break;
            }
        }
    }

    public static void supprimerAvion(String immatriculation) {
        listeAvions.removeIf(a -> a.getImmatriculation().equals(immatriculation));
    }
}
