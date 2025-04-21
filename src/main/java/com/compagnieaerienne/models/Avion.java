package com.compagnieaerienne.models;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;

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

    public static void sauvegarderAvionDansTxt(String cheminFichier, Avion avion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier, true))) {
            String ligne = avion.getImmatriculation() + ";" +
                    avion.getModele() + ";" +
                    avion.getCapacite();
            writer.write(ligne);
            writer.newLine();
            System.out.println("Avion sauvegardé : " + ligne);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier TXT : " + e.getMessage());
        }
    }

    public static List<Avion> importerAvionsDepuisTxt(String cheminFichier) {
        List<Avion> avionsImportes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            reader.readLine();
            while ((ligne = reader.readLine()) != null) {
                if (ligne.trim().isEmpty()) continue;
                String[] data = ligne.split(";");
                if (data.length != 3) {
                    System.out.println("Ligne invalide : " + ligne);
                    continue;
                }
                String immatriculation = data[0];
                String modele = data[1];
                int capacite;
                try {
                    capacite = Integer.parseInt(data[2]);
                } catch (NumberFormatException e) {
                    System.out.println("Erreur de capacité sur la ligne : " + ligne);
                    continue;
                }
                Avion avion = new Avion(immatriculation, modele, capacite);
                avionsImportes.add(avion);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier TXT : " + e.getMessage());
        }
        return avionsImportes;
    }
}
