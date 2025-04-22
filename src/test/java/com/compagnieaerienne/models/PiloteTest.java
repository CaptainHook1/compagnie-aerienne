package com.compagnieaerienne.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PiloteTest {

    @Test
    void creationPilote() {
        Pilote p = new Pilote("ID001", "Maxime", "Paris", "0600000000", "EMP001", "2024-01-10", "L12345", 1200);
        assertEquals("ID001", p.getIdentifiant());
        assertEquals("Maxime", p.getNom());
        assertEquals("Paris", p.getAdresse());
        assertEquals("0600000000", p.getContact());
        assertEquals("EMP001", p.getNumeroEmploye());
        assertEquals("2024-01-10", p.getDateEmbauche());
        assertEquals("L12345", p.getLicence());
        assertEquals(1200, p.getHeuresDeVol());
    }

    @Test
    void ajouterEtChercherPilote() {
        Pilote p = new Pilote("ID002", "Sarah", "Lyon", "0611111111", "EMP002", "2023-05-20", "L54321", 800);
        Pilote.ajouterPilote(p);
        Pilote trouve = Pilote.chercherPilote("ID002");
        assertNotNull(trouve);
    }

    @Test
    void modifierPilote() {
        Pilote p = new Pilote("ID003", "Thomas", "Marseille", "0622222222", "EMP003", "2022-09-01", "L22222", 950);
        Pilote.ajouterPilote(p);
        Pilote modif = new Pilote("ID003", "Thomas", "Nice", "0622222222", "EMP003", "2022-09-01", "L22222", 950);
        Pilote.modifierPilote("ID003", modif);
        Pilote trouve = Pilote.chercherPilote("ID003");
        assertEquals("Nice", trouve.getAdresse());
    }

    @Test
    void supprimerPilote() {
        Pilote p = new Pilote("ID004", "Emma", "Toulouse", "0633333333", "EMP004", "2021-12-15", "L99999", 600);
        Pilote.ajouterPilote(p);
        Pilote.supprimerPilote("ID004");
        assertNull(Pilote.chercherPilote("ID004"));
    }
}
