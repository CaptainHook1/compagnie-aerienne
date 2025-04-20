package com.compagnieaerienne.models;

import java.io.*;
import java.text.SimpleDateFormat;
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
        System.out.println("Ajout du vol: " + vol.getNumeroVol() + " - " + vol.getOrigine() + " -> " + vol.getDestination());
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

    public static List<Vol> importerVolsDepuisTxt(String cheminFichier) {
        List<Vol> volsImportes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(";");
                if (data.length != 7) {
                    System.out.println("Format invalide pour la ligne: " + line);
                    continue;
                }

                String numeroVol = data[0];
                String origine = data[1];
                String destination = data[2];
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date dateHeureDepart = null;
                Date dateHeureArrivee = null;

                try {
                    dateHeureDepart = dateFormat.parse(data[3]);
                    dateHeureArrivee = dateFormat.parse(data[4]);
                } catch (Exception e) {
                    System.out.println("Erreur de format de date pour la ligne: " + line);
                    continue;
                }

                String etat = data[5];
                Avion avion = Avion.chercherAvion(data[6]);
                Vol vol = new Vol(numeroVol, origine, destination, dateHeureDepart, dateHeureArrivee, etat, avion);
                volsImportes.add(vol);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier TXT : " + e.getMessage());
        }
        return volsImportes;
    }

    public static void ajouterVol(Vol vol, String cheminFichier) {
        listeVols.add(vol);
        sauvegarderVolDansTxt(cheminFichier, vol);
    }

    public static void sauvegarderVolDansTxt(String cheminFichier, Vol vol) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier, true))) {
            String volData = vol.getNumeroVol() + ";" +
                    vol.getOrigine() + ";" +
                    vol.getDestination() + ";" +
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").format(vol.getDateHeureDepart()) + ";" +
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").format(vol.getDateHeureArrivee()) + ";" +
                    vol.getEtat() + ";" +
                    vol.getAvion().getImmatriculation();
            writer.write(volData);
            writer.newLine();

            System.out.println("Ligne ajoutée dans le fichier: " + volData);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier TXT : " + e.getMessage());
        }
    }
}
