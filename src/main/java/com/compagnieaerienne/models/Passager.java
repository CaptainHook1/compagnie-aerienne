package com.compagnieaerienne.models;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Passager extends Personne {
    private String passport;
    private List<Reservation> reservations;
    private static List<Passager> listePassagers = new ArrayList<>();

    public Passager(String identifiant, String nom, String adresse, String contact, String passport) {
        super(identifiant, nom, adresse, contact);
        this.passport = passport;
        this.reservations = new ArrayList<>();
    }

    public void reserverVol(Vol vol, String cheminFichier) {
        Reservation reservation = new Reservation(vol.getNumeroVol(), vol.getDateHeureDepart(), "Réservée", this.getPassport());
        reservations.add(reservation);
        Reservation.sauvegarderReservationDansTxt(cheminFichier, reservation);
    }

    public void annulerReservation(String numeroReservation) {
        reservations.removeIf(reservation -> reservation.getNumeroReservation().equals(numeroReservation));
    }

    public List<Reservation> obtenirReservations() {
        return reservations;
    }


    public Reservation obtenirReservations(String numeroReservation) {
        for (Reservation reservation : reservations) {
            if (reservation.getNumeroReservation().equals(numeroReservation)) {
                return reservation;
            }
        }
        return null;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public static void ajouterPassager(Passager passager, String cheminFichier) {
        for (Passager p : listePassagers) {
            if (p.getIdentifiant().equals(passager.getIdentifiant())) return;
        }
        listePassagers.add(passager);
        sauvegarderPassagerDansTxt(cheminFichier, passager);
    }


    public static Passager chercherPassager(String identifiant) {
        for (Passager p : listePassagers) {
            if (p.getIdentifiant().equals(identifiant)) return p;
        }
        return null;
    }

    public static void modifierPassager(String identifiant, Passager nouveauPassager) {
        for (int i = 0; i < listePassagers.size(); i++) {
            if (listePassagers.get(i).getIdentifiant().equals(identifiant)) {
                listePassagers.set(i, nouveauPassager);
                break;
            }
        }
    }

    public static void supprimerPassager(String identifiant) {
        listePassagers.removeIf(p -> p.getIdentifiant().equals(identifiant));
    }

    public static void sauvegarderPassagerDansTxt(String cheminFichier, Passager passager) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier, true))) {
            String ligne = passager.getIdentifiant() + ";" +
                    passager.getNom() + ";" +
                    passager.getAdresse() + ";" +
                    passager.getContact() + ";" +
                    passager.getPassport();
            writer.write(ligne);
            writer.newLine();
            // System.out.println("Passager sauvegardé : " + ligne);
        } catch (IOException e) {
            // System.err.println("Erreur lors de l'écriture dans le fichier TXT : " + e.getMessage());
        }
    }

    public static List<Passager> importerPassagersDepuisTxt(String cheminFichier) {
        List<Passager> passagersImportes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            reader.readLine();
            while ((ligne = reader.readLine()) != null) {
                if (ligne.trim().isEmpty()) continue;
                String[] data = ligne.split(";");
                if (data.length != 5) {
                    // System.out.println("Ligne invalide : " + ligne);
                    continue;
                }
                Passager p = new Passager(data[0], data[1], data[2], data[3], data[4]);
                if (chercherPassager(p.getIdentifiant()) == null) {
                    listePassagers.add(p);
                    passagersImportes.add(p);
                }
            }
        } catch (IOException e) {
            // System.err.println("Erreur lors de la lecture du fichier TXT : " + e.getMessage());
        }
        return passagersImportes;
    }

    public static List<Passager> getListePassagers() {
        return listePassagers;
    }

    public Reservation obtenirDerniereReservation() {
        if (!reservations.isEmpty()) {
            return reservations.get(reservations.size() - 1);
        }
        return null;
    }

}

