package com.compagnieaerienne.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonnelCabineTest {

    @Test
    void creationPersonnelCabine() {
        PersonnelCabine p = new PersonnelCabine("ID001", "Sophie", "Paris", "0600000000", "EMP001", "2024-01-10", "Sécurité");
        assertEquals("ID001", p.getIdentifiant());
        assertEquals("Sophie", p.getNom());
        assertEquals("Paris", p.getAdresse());
        assertEquals("0600000000", p.getContact());
        assertEquals("EMP001", p.getNumeroEmploye());
        assertEquals("2024-01-10", p.getDateEmbauche());
        assertEquals("Sécurité", p.getQualification());
    }

    @Test
    void ajouterEtChercherPersonnelCabine() {
        PersonnelCabine p = new PersonnelCabine("ID002", "Léo", "Lyon", "0611111111", "EMP002", "2023-06-15", "Accueil");
        PersonnelCabine.ajouterPersonnelCabine(p);
        PersonnelCabine trouve = PersonnelCabine.chercherPersonnelCabine("ID002");
        assertNotNull(trouve);
    }

    @Test
    void modifierPersonnelCabine() {
        PersonnelCabine p = new PersonnelCabine("ID003", "Maya", "Marseille", "0622222222", "EMP003", "2022-04-01", "Service");
        PersonnelCabine.ajouterPersonnelCabine(p);
        PersonnelCabine modif = new PersonnelCabine("ID003", "Maya", "Nice", "0622222222", "EMP003", "2022-04-01", "Service");
        PersonnelCabine.modifierPersonnelCabine("ID003", modif);
        PersonnelCabine trouve = PersonnelCabine.chercherPersonnelCabine("ID003");
        assertEquals("Nice", trouve.getAdresse());
    }

    @Test
    void supprimerPersonnelCabine() {
        PersonnelCabine p = new PersonnelCabine("ID004", "Lucas", "Toulouse", "0633333333", "EMP004", "2021-09-20", "Langues");
        PersonnelCabine.ajouterPersonnelCabine(p);
        PersonnelCabine.supprimerPersonnelCabine("ID004");
        assertNull(PersonnelCabine.chercherPersonnelCabine("ID004"));
    }
}
