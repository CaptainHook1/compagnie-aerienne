package com.compagnieaerienne.models;

import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PassagerTest {

    @Test
    void creationPassager() {
        Passager p = new Passager("ID001", "Alice", "Paris", "0600000000", "P123456");
        assertEquals("ID001", p.getIdentifiant());
        assertEquals("Alice", p.getNom());
        assertEquals("Paris", p.getAdresse());
        assertEquals("0600000000", p.getContact());
        assertEquals("P123456", p.getPassport());
    }

    @Test
    void ajouterEtChercherPassager() {
        Passager p = new Passager("ID002", "Bob", "Lyon", "0611111111", "P654321");
        Passager.ajouterPassager(p, "test_passagers.txt");
        Passager trouve = Passager.chercherPassager("ID002");
        assertNotNull(trouve);
    }

    @Test
    void modifierPassager() {
        Passager p = new Passager("ID003", "Clara", "Marseille", "0622222222", "P789123");
        Passager.ajouterPassager(p, "test_passagers.txt");
        Passager modif = new Passager("ID003", "Clara", "Nice", "0622222222", "P789123");
        Passager.modifierPassager("ID003", modif);
        Passager trouve = Passager.chercherPassager("ID003");
        assertEquals("Nice", trouve.getAdresse());
    }

    @Test
    void supprimerPassager() {
        Passager p = new Passager("ID004", "David", "Toulouse", "0633333333", "P321987");
        Passager.ajouterPassager(p, "test_passagers.txt");
        Passager.supprimerPassager("ID004");
        assertNull(Passager.chercherPassager("ID004"));
    }

    @Test
    void importerPassagersDepuisTxt() {
        String chemin = "test_passagers.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(chemin));
            writer.write("identifiant;nom;adresse;contact;passport");
            writer.newLine();
            writer.write("ID005;Emma;Bordeaux;0644444444;P112233");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            fail("Erreur lors de l'écriture du fichier de test");
        }

        List<Passager> liste = Passager.importerPassagersDepuisTxt(chemin);
        boolean contient = false;
        for (Passager p : liste) {
            if (p.getIdentifiant().equals("ID005")) {
                contient = true;
                break;
            }
        }
        assertTrue(contient);
    }
}
