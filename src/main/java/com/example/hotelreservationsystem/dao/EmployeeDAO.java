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

    /**
     * Ažurira postojeće informacije o zaposlenom u bazi podataka.
     *
     * @param employee objekat zaposlenog sa ažuriranim informacijama
     * @return true ako je zaposleni uspešno ažuriran, false inače
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public boolean updateEmployee(Employee employee) throws SQLException {
        String query = "UPDATE Employees SET name = ?, position = ?, salary = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getPosition());
            stmt.setDouble(3, employee.getSalary());
            stmt.setInt(4, employee.getId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    /**
     * Briše zaposlenog iz baze podataka.
     *
     * @param employeeId ID zaposlenog koji se briše
     * @return true ako je zaposleni uspešno obrisan, false inače
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public boolean deleteEmployee(int employeeId) throws SQLException {
        String query = "DELETE FROM Employees WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, employeeId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        }
    }
}
