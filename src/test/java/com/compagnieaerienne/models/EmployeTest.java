package com.compagnieaerienne.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeTest {

    @Test
    void creationEmploye() {
        Employe e = new Employe("ID123", "Jean Dupont", "Paris", "0601020304", "EMP001", "2023-05-12");
        assertEquals("ID123", e.getIdentifiant());
        assertEquals("Jean Dupont", e.getNom());
        assertEquals("Paris", e.getAdresse());
        assertEquals("0601020304", e.getContact());
        assertEquals("EMP001", e.getNumeroEmploye());
        assertEquals("2023-05-12", e.getDateEmbauche());
    }

    @Test
    void ajouterEtChercherEmploye() {
        Employe e = new Employe("ID456", "Claire Martin", "Lyon", "0611223344", "EMP002", "2022-08-01");
        Employe.ajouterEmploye(e);
        Employe trouve = Employe.chercherEmploye("ID456");
        assertNotNull(trouve);
    }

    @Test
    void modifierEmploye() {
        Employe e = new Employe("ID789", "Paul Durand", "Marseille", "0622334455", "EMP003", "2021-01-10");
        Employe.ajouterEmploye(e);
        Employe nouveau = new Employe("ID789", "Paul Durand", "Nice", "0622334455", "EMP003", "2021-01-10");
        Employe.modifierEmploye("ID789", nouveau);
        Employe modifie = Employe.chercherEmploye("ID789");
        assertEquals("Nice", modifie.getAdresse());
    }

    @Test
    void supprimerEmploye() {
        Employe e = new Employe("ID999", "Laura Blanc", "Toulouse", "0633445566", "EMP004", "2020-12-20");
        Employe.ajouterEmploye(e);
        Employe.supprimerEmploye("ID999");
        assertNull(Employe.chercherEmploye("ID999"));
    }
}
