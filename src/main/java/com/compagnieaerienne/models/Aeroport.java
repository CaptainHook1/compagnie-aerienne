package com.compagnieaerienne.models;

import java.io.*;
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
        for (Aeroport a : listeAeroports) {
            if (a.getNom().equalsIgnoreCase(aeroport.getNom())) return;
        }
        listeAeroports.add(aeroport);
    }

    public static Aeroport chercherAeroport(String nom) {
        for (Aeroport a : listeAeroports) {
            if (a.getNom().equalsIgnoreCase(nom)) return a;
        }
        return null;
    }

    public static void modifierAeroport(String nom, Aeroport nouvelAeroport) {
        for (int i = 0; i < listeAeroports.size(); i++) {
            if (listeAeroports.get(i).getNom().equalsIgnoreCase(nom)) {
                listeAeroports.set(i, nouvelAeroport);
                break;
            }
        }
    }

    public static void supprimerAeroport(String nom) {
        listeAeroports.removeIf(a -> a.getNom().equalsIgnoreCase(nom));
    }

    public static void sauvegarderAeroportDansTxt(String cheminFichier, Aeroport aeroport) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier, true))) {
            String ligne = aeroport.getNom() + ";" + aeroport.getVille() + ";" + aeroport.getDescription();
            writer.write(ligne);
            writer.newLine();
        } catch (IOException e) {
        }
    }

    public static List<Aeroport> importerAeroportsDepuisTxt(String cheminFichier) {
        List<Aeroport> aeroportsImportes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (ligne.trim().isEmpty()) continue;
                String[] data = ligne.split(";");
                if (data.length != 3) continue;
                Aeroport aeroport = new Aeroport(data[0], data[1], data[2]);
                ajouterAeroport(aeroport);
                aeroportsImportes.add(aeroport);
            }
        } catch (IOException e) {
        }
        return aeroportsImportes;
    }
}
