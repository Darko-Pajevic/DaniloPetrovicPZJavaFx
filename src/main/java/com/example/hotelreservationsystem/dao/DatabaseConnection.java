package com.example.hotelreservationsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Klasa za upravljanje konekcijom na bazu podataka.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:F:/BROWSER DOWNLOADS/FAKS/DRUGA GODINA/Prvi semestar/CS202/vezba/Danilo-Petrovic-PZ/HotelReservationSystem.db";
    private static final String USER = "";
    private static final String PASSWORD = "";

    static {
        try {
            // Registracija drajvera
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load SQLite JDBC driver", e);
        }
    }

    /**
     * Vraća konekciju na bazu podataka.
     *
     * @return konekcija na bazu podataka
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connection to the database established successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
