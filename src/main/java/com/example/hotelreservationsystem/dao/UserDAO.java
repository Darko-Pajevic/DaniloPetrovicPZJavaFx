package com.example.hotelreservationsystem.dao;

import com.example.hotelreservationsystem.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) za entitet User.
 */
public class UserDAO {

    /**
     * Metoda za autentifikaciju korisnika.
     *
     * @param username korisničko ime
     * @param password lozinka
     * @return true ako je korisnik uspešno autentifikovan, false inače
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public boolean authenticateUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Vraća true ako postoji red sa zadatim korisničkim imenom i lozinkom
            }
        }
    }

    /**
     * Dohvata sve korisnike iz baze podataka.
     *
     * @return lista svih korisnika
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                users.add(new User(id, username, password, email));
            }
        }
        return users;
    }
}
