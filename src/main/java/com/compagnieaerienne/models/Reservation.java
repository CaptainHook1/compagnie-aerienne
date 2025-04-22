package com.compagnieaerienne.models;

import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

public class Reservation {
    private String numeroReservation;
    private Date dateReservation;
    private String statut;
    private String passport;

    private static List<Reservation> listeReservations = new ArrayList<>();

    public Reservation(String numeroReservation, Date dateReservation, String statut, String passport) {
        this.numeroReservation = numeroReservation;
        this.dateReservation = dateReservation;
        this.statut = statut;
        this.passport = passport;
    }

    public void confirmerReservation() {
        this.statut = "Confirmée";
    }

    public void annulerReservation() {
        this.statut = "Annulée";
    }

    public void modifierReservation(Date nouvelleDate) {
        this.dateReservation = nouvelleDate;
    }

    public String getNumeroReservation() {
        return numeroReservation;
    }

    public void setNumeroReservation(String numeroReservation) {
        this.numeroReservation = numeroReservation;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getstatut() {
        return statut;
    }

    public void setstatut(String statut) {
        this.statut = statut;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public static void ajouterReservation(Reservation reservation) {
        for (Reservation r : listeReservations) {
            if (r.getNumeroReservation().equals(reservation.getNumeroReservation())) return;
        }
        listeReservations.add(reservation);
        sauvegarderReservationDansTxt("reservations.txt", reservation);
    }

    public static Reservation chercherReservation(String numeroReservation) {
        for (Reservation r : listeReservations) {
            if (r.getNumeroReservation().equals(numeroReservation)) return r;
        }
        return null;
    }

    public static void modifierReservation(String numeroReservation, Reservation nouvelleReservation) {
        for (int i = 0; i < listeReservations.size(); i++) {
            if (listeReservations.get(i).getNumeroReservation().equals(numeroReservation)) {
                listeReservations.set(i, nouvelleReservation);
                break;
            }
        }
    }

    public static void supprimerReservation(String numeroReservation) {
        listeReservations.removeIf(r -> r.getNumeroReservation().equals(numeroReservation));
    }

    public static void sauvegarderReservationDansTxt(String cheminFichier, Reservation reservation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier, true))) {
            String ligne = reservation.getNumeroReservation() + ";" +
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").format(reservation.getDateReservation()) + ";" +
                    reservation.getstatut() + ";" +
                    reservation.getPassport();
            writer.write(ligne);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier réservation : " + e.getMessage());
        }
    }

    public static List<Reservation> importerReservationsDepuisTxt(String cheminFichier) {
        List<Reservation> reservations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (ligne.trim().isEmpty()) continue;
                String[] data = ligne.split(";");
                if (data.length != 4) continue;

                String numero = data[0];
                Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data[1]);
                String statut = data[2];
                String passport = data[3];

                reservations.add(new Reservation(numero, date, statut, passport));
            }
        } catch (IOException | ParseException e) {
            System.err.println("Erreur lors de l'importation des réservations : " + e.getMessage());
        }
        return reservations;
    }

    public static List<Reservation> chercherReservationsParPassport(String passeport, String cheminFichier) {
        List<Reservation> resultat = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            reader.readLine(); // ignorer l'en-tête
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(";");
                if (data.length == 4 && data[3].equalsIgnoreCase(passeport)) {
                    Reservation r = new Reservation(data[0], new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data[1]), data[2], data[3]);
                    resultat.add(r);
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture des réservations : " + e.getMessage());
        }
        return resultat;
    }

}
