package com.compagnieaerienne.models;

import java.util.List;
import java.util.Scanner;

public class MainDB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Ajouter un avion");
            System.out.println("2. Supprimer un avion");
            System.out.println("3. Lister les avions");
            System.out.println("0. Quitter");

            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Immatriculation : ");
                    String immat = scanner.nextLine();
                    System.out.print("Modèle : ");
                    String modele = scanner.nextLine();
                    System.out.print("Capacité : ");
                    int capacite = scanner.nextInt();
                    scanner.nextLine();

                    Avion nouvelAvion = new Avion(immat, modele, capacite);
                    BaseOperations.ajouterAvion(nouvelAvion);
                    break;

                case 2:
                    System.out.print("Immatriculation de l'avion à supprimer : ");
                    String immatSupp = scanner.nextLine();
                    BaseOperations.supprimerAvion(immatSupp);
                    break;

                case 3:
                    List<Avion> avions = BaseOperations.listerAvions();
                    for (Avion a : avions) {
                        System.out.println("- " + a.getImmatriculation() + " | " + a.getModele() + " | " + a.getCapacite() + " places");
                    }
                    break;

                case 0:
                    System.out.println("Opération terminée");
                    return;

                default:
                    System.out.println("! Choix invalide !");
            }
        }
    }
}
