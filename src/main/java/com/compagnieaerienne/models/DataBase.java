package com.compagnieaerienne.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/compagnie";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connectDB() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
            return null;
        }
    }
}
