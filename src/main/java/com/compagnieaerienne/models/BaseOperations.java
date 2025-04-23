package com.compagnieaerienne.models;

import java.sql.*;
import java.util.*;

    public class BaseOperations {

        public static void ajouterAvion(Avion avion) {
            String sql = "INSERT INTO avions VALUES (?, ?, ?)";
            try (Connection conn = DataBase.connectDB();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, avion.getImmatriculation());
                stmt.setString(2, avion.getModele());
                stmt.setInt(3, avion.getCapacite());
                stmt.executeUpdate();
                System.out.println("Avion ajouté !");
            } catch (SQLException e) {
                System.err.println("! Erreur ajout avion : " + e.getMessage());
            }
        }

        public static List<Avion> listerAvions() {
            List<Avion> avions = new ArrayList<>();
            try (Connection conn = DataBase.connectDB();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM avions")) {
                while (rs.next()) {
                    avions.add(new Avion(
                            rs.getString("immatriculation"),
                            rs.getString("modele"),
                            rs.getInt("capacite")));
                }
            } catch (SQLException e) {
                System.err.println("! Erreur liste avions : " + e.getMessage());
            }
            return avions;
        }

        public static void supprimerAvion(String immatriculation) {
            String sql = "DELETE FROM avions WHERE immatriculation = ?";
            try (Connection conn = DataBase.connectDB();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, immatriculation);
                stmt.executeUpdate();
                System.out.println("Avion supprimé.");
            } catch (SQLException e) {
                System.err.println("! Erreur suppression avion : " + e.getMessage());
            }
        }
}
