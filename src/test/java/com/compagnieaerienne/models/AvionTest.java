package com.compagnieaerienne.models;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class AvionTest {

    @Test
    void creationAvion() {
        Avion a = new Avion("A123", "Boeing 737", 180);
        assertEquals("A123", a.getImmatriculation());
        assertEquals("Boeing 737", a.getModele());
        assertEquals(180, a.getCapacite());
    }

    @Test
    void ajouterEtChercherAvion() {
        Avion a = new Avion("A456", "Airbus A320", 150);
        Avion.ajouterAvion(a);
        Avion trouve = Avion.chercherAvion("A456");
        assertNotNull(trouve);
    }

    @Test
    void modifierAvion() {
        Avion a = new Avion("A789", "Boeing 747", 400);
        Avion.ajouterAvion(a);
        Avion nouveau = new Avion("A789", "Boeing 777", 350);
        Avion.modifierAvion("A789", nouveau);
        Avion modifie = Avion.chercherAvion("A789");
        assertEquals("Boeing 777", modifie.getModele());
    }

    @Test
    void supprimerAvion() {
        Avion a = new Avion("A999", "Embraer", 100);
        Avion.ajouterAvion(a);
        Avion.supprimerAvion("A999");
        assertNull(Avion.chercherAvion("A999"));
    }

    @Test
    void sauvegarderEtImporterAvion() {
        String chemin = "test_avion.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(chemin));
            writer.write("immatriculation;modele;capacite");
            writer.newLine();
            writer.close();

            Avion a = new Avion("B321", "Boeing 707", 120);
            Avion.sauvegarderAvionDansTxt(chemin, a);
            List<Avion> liste = Avion.importerAvionsDepuisTxt(chemin);
            boolean contient = false;
            for (Avion av : liste) {
                if (av.getImmatriculation().equals("B321")) {
                    contient = true;
                    break;
                }
            }
            assertTrue(contient);
        } catch (IOException e) {
            fail("Erreur lors de l'écriture du fichier de test");
        } finally {
            new File(chemin).delete();
        }
    }
}