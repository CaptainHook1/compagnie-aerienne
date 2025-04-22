package com.compagnieaerienne.models;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fichierAvions = "avions.txt";
        String fichierVols = "vols.txt";
        String fichierPassagers = "passagers.txt";
        String fichierReservations = "reservations.txt";

        System.out.println("=== BIENVENUE DANS LE SYSTÈME DE RÉSERVATION DE COMPAGNIE AÉRIENNE ===");
        System.out.println("\nChargement des données depuis les fichiers...");

        try {
            ImportationVols.importerVolsDepuisAPI(fichierVols);
        } catch (Exception e) {
            System.err.println("Échec lors de l'importation des vols depuis l'API.");
        }

        List<Vol> vols = Vol.importerVolsDepuisTxt(fichierVols);
        List<Avion> avions = Avion.importerAvionsDepuisTxt(fichierAvions);
        List<Passager> passagers = Passager.importerPassagersDepuisTxt(fichierPassagers);

        System.out.println("> Vols importés depuis " + fichierVols + " : " + vols.size() + " vols disponibles");
        System.out.println("> Passagers existants importés depuis " + fichierPassagers + " : " + passagers.size() + " passagers enregistrés");

        System.out.println("\n---------------------------------------------------");
        System.out.println("Liste des vols disponibles :");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM");
        SimpleDateFormat heureFormat = new SimpleDateFormat("HH:mm");
        for (Vol vol : vols) {
            System.out.println("[ID: " + vol.getNumeroVol() + "] " + vol.getOrigine() + " -> " + vol.getDestination()
                    + " | Date: " + dateFormat.format(vol.getDateHeureDepart())
                    + " | Heure: " + heureFormat.format(vol.getDateHeureDepart()));
        }
        System.out.println("---------------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEntrez votre numéro de passeport : ");
        String passportSaisi = scanner.nextLine().trim();

        Passager passagerTrouve = null;
        for (Passager p : passagers) {
            if (p.getPassport().equalsIgnoreCase(passportSaisi)) {
                passagerTrouve = p;
                break;
            }
        }

        if (passagerTrouve != null) {
            System.out.println("Passager reconnu : " + passagerTrouve.getNom());

            System.out.print("\nSouhaitez-vous réserver un vol ? (oui/non) : ");
            String reponse = scanner.nextLine().trim();

            if (reponse.equalsIgnoreCase("oui")) {
                System.out.print("Entrez le code du vol souhaité : ");
                String codeVol = scanner.nextLine().trim();

                Vol volChoisi = Vol.chercherVol(codeVol);
                if (volChoisi != null) {
                    System.out.println("> Vérification de la disponibilité...");
                    Reservation nouvelleReservation = new Reservation(volChoisi.getNumeroVol(), volChoisi.getDateHeureDepart(), "Réservée", passportSaisi);
                    Reservation.sauvegarderReservationDansTxt(fichierReservations, nouvelleReservation);

                    System.out.println("> Réservation en cours...");
                    System.out.println("> Réservation confirmée ! 🎉");

                    System.out.println("\nInformations de la réservation :");
                    System.out.println("-------------------------------------");
                    System.out.println("Numéro de réservation : " + nouvelleReservation.getNumeroReservation());
                    System.out.println("Passager : " + passagerTrouve.getNom());
                    System.out.println("Vol : " + volChoisi.getNumeroVol() + " | " + volChoisi.getOrigine() + " -> " + volChoisi.getDestination()
                            + " | " + dateFormat.format(volChoisi.getDateHeureDepart()) + " à " + heureFormat.format(volChoisi.getDateHeureDepart()));
                    System.out.println("Avion assigné : " + volChoisi.getAvion().getModele());
                    System.out.println("-------------------------------------");
                } else {
                    System.out.println("Vol non trouvé.");
                }
            }

            System.out.print("\nSouhaitez-vous consulter vos réservations ? (oui/non) : ");
            String voirRes = scanner.nextLine().trim();
            if (voirRes.equalsIgnoreCase("oui")) {
                System.out.println("\n> Vos réservations :");
                List<Reservation> toutes = Reservation.importerReservationsDepuisTxt(fichierReservations);
                int compteur = 1;
                for (Reservation r : toutes) {
                    if (r.getPassport().equals(passportSaisi)) {
                        Vol v = Vol.chercherVol(r.getNumeroReservation());
                        if (v != null) {
                            System.out.println("[" + compteur + "] Vol " + v.getNumeroVol() + " | " + v.getOrigine() + " -> " + v.getDestination()
                                    + " | " + dateFormat.format(v.getDateHeureDepart()) + " à " + heureFormat.format(v.getDateHeureDepart()));
                            compteur++;
                        }
                    }
                }
                if (compteur == 1) {
                    System.out.println("Aucune réservation trouvée.");
                }
            }
        } else {
            System.out.println("Aucun passager trouvé avec ce numéro de passeport.");
        }

        System.out.println("\nMerci d'avoir utilisé notre système ✈️");
    }
}
