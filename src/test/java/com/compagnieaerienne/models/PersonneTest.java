package com.compagnieaerienne.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonneTest {

    @Test
    void creationPersonne() {
        Personne p = new Personne("ID001", "Alice", "Paris", "0600000000");
        assertEquals("ID001", p.getIdentifiant());
        assertEquals("Alice", p.getNom());
        assertEquals("Paris", p.getAdresse());
        assertEquals("0600000000", p.getContact());
    }

    @Test
    void ajouterEtChercherPersonne() {
        Personne p = new Personne("ID002", "Bob", "Lyon", "0611111111");
        Personne.ajouterPersonne(p);
        Personne trouve = Personne.chercherPersonne("ID002");
        assertNotNull(trouve);
    }

    @Test
    void modifierPersonne() {
        Personne p = new Personne("ID003", "Clara", "Marseille", "0622222222");
        Personne.ajouterPersonne(p);
        Personne nouveau = new Personne("ID003", "Clara", "Nice", "0622222222");
        Personne.modifierPersonne("ID003", nouveau);
        Personne trouve = Personne.chercherPersonne("ID003");
        assertEquals("Nice", trouve.getAdresse());
    }

    @Test
    void supprimerPersonne() {
        Personne p = new Personne("ID004", "David", "Toulouse", "0633333333");
        Personne.ajouterPersonne(p);
        Personne.supprimerPersonne("ID004");
        assertNull(Personne.chercherPersonne("ID004"));
    }
}
