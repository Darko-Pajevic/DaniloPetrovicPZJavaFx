package com.example.hotelreservationsystem.dao;

import com.example.hotelreservationsystem.models.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) za entitet Employee.
 */
public class EmployeeDAO {

    /**
     * Dohvata sve zaposlene iz baze podataka.
     *
     * @return lista svih zaposlenih
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employees";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("position"), rs.getDouble("salary")));
            }
        }
        return employees;
    }

    /**
     * Kreira novog zaposlenog u bazi podataka.
     *
     * @param employee objekat zaposlenog koji se dodaje
     * @return true ako je zaposleni uspešno dodat, false inače
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public boolean createEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO Employees (name, position, salary) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getPosition());
            stmt.setDouble(3, employee.getSalary());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

    // Implementirajte update i delete metode
}
