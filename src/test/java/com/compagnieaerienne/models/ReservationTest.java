package com.compagnieaerienne.models;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    @Test
    void creationReservation() throws Exception {
        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/05/2025 14:00");
        Reservation r = new Reservation("R001", date, "Réservée", "P12345");
        assertEquals("R001", r.getNumeroReservation());
        assertEquals(date, r.getDateReservation());
        assertEquals("Réservée", r.getstatut());
        assertEquals("P12345", r.getPassport());
    }

    @Test
    void modifierEtAnnulerReservation() throws Exception {
        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("02/05/2025 10:00");
        Reservation r = new Reservation("R002", date, "Réservée", "P54321");

        r.confirmerReservation();
        assertEquals("Confirmée", r.getstatut());

        r.annulerReservation();
        assertEquals("Annulée", r.getstatut());

        Date nouvelleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("03/05/2025 16:00");
        r.modifierReservation(nouvelleDate);
        assertEquals(nouvelleDate, r.getDateReservation());
    }

    @Test
    void ajouterEtChercherReservation() throws Exception {
        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("04/05/2025 09:30");
        Reservation r = new Reservation("R003", date, "Réservée", "P11111");
        Reservation.ajouterReservation(r);
        Reservation trouvee = Reservation.chercherReservation("R003");
        assertNotNull(trouvee);
        assertEquals("P11111", trouvee.getPassport());
    }

    @Test
    void supprimerReservation() throws Exception {
        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("05/05/2025 11:00");
        Reservation r = new Reservation("R004", date, "Réservée", "P22222");
        Reservation.ajouterReservation(r);
        Reservation.supprimerReservation("R004");
        assertNull(Reservation.chercherReservation("R004"));
    }

    @Test
    void sauvegarderEtImporterReservationsDepuisTxt() throws Exception {
        String chemin = "test_reservations.txt";
        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("06/05/2025 13:00");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(chemin))) {
            writer.write("R005;" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date) + ";Réservée;P33333");
            writer.newLine();
        }

        List<Reservation> reservations = Reservation.importerReservationsDepuisTxt(chemin);
        boolean contient = false;
        for (Reservation r : reservations) {
            if (r.getNumeroReservation().equals("R005")) {
                contient = true;
                break;
            }
        }
        assertTrue(contient);
        new File(chemin).delete();
    }
}
