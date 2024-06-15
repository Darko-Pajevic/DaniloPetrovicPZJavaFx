package com.example.hotelreservationsystem.dao;

import com.example.hotelreservationsystem.models.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) za entitet Customer.
 */
public class CustomerDAO {

    /**
     * Dohvata sve kupce iz baze podataka.
     *
     * @return lista svih kupaca
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customers";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                customers.add(new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("phone")));
            }
        }
        return customers;
    }

    /**
     * Kreira novog kupca u bazi podataka.
     *
     * @param customer objekat kupca koji se dodaje
     * @return true ako je kupac uspešno dodat, false inače
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public boolean createCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customers (name, email, phone) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPhone());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

    // Implementirajte update i delete metode
}
