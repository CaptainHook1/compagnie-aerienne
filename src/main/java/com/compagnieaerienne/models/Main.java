package com.compagnieaerienne.models;

import com.compagnieaerienne.models.Passager;
import com.compagnieaerienne.models.Reservation;
import com.compagnieaerienne.models.Vol;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String fichierAvions = "avions.txt";
        String fichierVols = "vols.txt";
        String fichierPassagers = "passagers.txt";

        Avion avionAjoute = new Avion("F-TEST2", "Embraer E190", 100);
        Avion.sauvegarderAvionDansTxt(fichierAvions, avionAjoute);

        List<Avion> avionsImportes = Avion.importerAvionsDepuisTxt(fichierAvions);
        System.out.println("Avions importés :");
        for (Avion a : avionsImportes) {
            System.out.println(a.getImmatriculation() + " - " + a.getModele() + " - " + a.getCapacite());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date depart = dateFormat.parse("27/08/3925 12:30");
            Date arrivee = dateFormat.parse("27/08/3925 13:05");
            Avion avionPourVol = Avion.chercherAvion("LT68955");
            if (avionPourVol != null) {
                Vol volAjoute = new Vol("HP567", "Paris", "Bordeaux", depart, arrivee, "Planifié", avionPourVol);
                Vol.ajouterVol(volAjoute, fichierVols);
            }
        } catch (Exception e) {
            System.err.println("Erreur de parsing des dates");
        }

        List<Vol> volsImportes = Vol.importerVolsDepuisTxt(fichierVols);
        System.out.println("Vols importés :");
        for (Vol v : volsImportes) {
            System.out.println(v.getNumeroVol() + " - " + v.getOrigine() + " -> " + v.getDestination());
        }

        Passager passagerAjoute = new Passager("62438", "Manon George", "12 rue Victor Hugo", "0674894632", "FR197536");
        Passager.sauvegarderPassagerDansTxt(fichierPassagers, passagerAjoute);

        List<Passager> passagersImportes = Passager.importerPassagersDepuisTxt(fichierPassagers);
        System.out.println("Passagers importés :");
        for (Passager p : passagersImportes) {
            p.obtenirInfos();
        }
    }
}