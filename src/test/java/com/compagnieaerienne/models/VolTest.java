package com.compagnieaerienne.models;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class VolTest {

    @Test
    void creationVol() throws Exception {
        Avion avion = new Avion("A001", "Boeing 737", 180);
        Date depart = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("10/05/2025 14:00");
        Date arrivee = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("10/05/2025 18:00");
        Vol v = new Vol("V001", "Paris", "Rome", depart, arrivee, "Prévu", avion);
        assertEquals("V001", v.getNumeroVol());
        assertEquals("Paris", v.getOrigine());
        assertEquals("Rome", v.getDestination());
        assertEquals(depart, v.getDateHeureDepart());
        assertEquals(arrivee, v.getDateHeureArrivee());
        assertEquals("Prévu", v.getEtat());
        assertEquals(avion, v.getAvion());
    }

    @Test
    void modifierEtAnnulerVol() throws Exception {
        Avion avion = new Avion("A002", "Airbus A320", 150);
        Date depart = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("11/05/2025 09:00");
        Date arrivee = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("11/05/2025 12:00");
        Vol v = new Vol("V002", "Lyon", "Madrid", depart, arrivee, "Prévu", avion);

        v.annulerVol();
        assertEquals("Annulé", v.getEtat());

        Date nouveauDepart = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("12/05/2025 09:00");
        Date nouvelleArrivee = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("12/05/2025 12:00");
        v.modifierVol("Marseille", "Lisbonne", nouveauDepart, nouvelleArrivee);
        assertEquals("Marseille", v.getOrigine());
        assertEquals("Lisbonne", v.getDestination());
        assertEquals(nouveauDepart, v.getDateHeureDepart());
        assertEquals(nouvelleArrivee, v.getDateHeureArrivee());
    }

    @Test
    void ajouterEtChercherVol() throws Exception {
        Avion avion = new Avion("A003", "Boeing 747", 350);
        Vol v = new Vol("V003", "Nice", "Berlin", new Date(), new Date(), "Prévu", avion);
        Vol.ajouterVol(v);
        Vol trouve = Vol.chercherVol("V003");
        assertNotNull(trouve);
    }

    @Test
    void supprimerVol() throws Exception {
        Avion avion = new Avion("A004", "Embraer", 100);
        Vol v = new Vol("V004", "Toulouse", "Bruxelles", new Date(), new Date(), "Prévu", avion);
        Vol.ajouterVol(v);
        Vol.supprimerVol("V004");
        assertNull(Vol.chercherVol("V004"));
    }

    @Test
    void sauvegarderEtImporterVolDepuisTxt() throws Exception {
        String chemin = "test_vols.txt";
        Avion a = new Avion("A005", "Boeing 737", 180);
        Avion.ajouterAvion(a);
        Date depart = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("13/05/2025 08:00");
        Date arrivee = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("13/05/2025 11:30");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(chemin))) {
            writer.write("numeroVol;origine;destination;dateHeureDepart;dateHeureArrivee;etat;avion");
            writer.newLine();
            writer.write("V005;Nantes;Amsterdam;13/05/2025 08:00;13/05/2025 11:30;Prévu;A005");
            writer.newLine();
        }

        List<Vol> vols = Vol.importerVolsDepuisTxt(chemin);
        boolean contient = false;
        for (Vol v : vols) {
            if (v.getNumeroVol().equals("V005")) {
                contient = true;
                break;
            }
        }
        assertTrue(contient);
        new File(chemin).delete();
    }
}
