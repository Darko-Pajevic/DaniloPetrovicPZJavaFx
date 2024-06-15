package com.example.hotelreservationsystem.dao;

import com.example.hotelreservationsystem.models.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) za entitet Reservation.
 */
public class ReservationDAO {

    /**
     * Dohvata sve rezervacije iz baze podataka.
     *
     * @return lista svih rezervacija
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservations";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int roomId = resultSet.getInt("room_id");
                long startDateTimestamp = resultSet.getLong("start_date");
                long endDateTimestamp = resultSet.getLong("end_date");
                double totalPrice = resultSet.getDouble("total_price");

                LocalDate startDate = Instant.ofEpochMilli(startDateTimestamp).atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate endDate = Instant.ofEpochMilli(endDateTimestamp).atZone(ZoneId.systemDefault()).toLocalDate();

                reservations.add(new Reservation(id, userId, roomId, startDate, endDate, totalPrice));
            }
        }
        return reservations;
    }

    /**
     * Dodaje novu rezervaciju u bazu podataka.
     *
     * @param reservation objekat rezervacije koji se dodaje
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public void addReservation(Reservation reservation) throws SQLException {
        String query = "INSERT INTO Reservations (user_id, room_id, start_date, end_date, total_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservation.getUserId());
            statement.setInt(2, reservation.getRoomId());
            statement.setLong(3, reservation.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
            statement.setLong(4, reservation.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
            statement.setDouble(5, reservation.getTotalPrice());
            statement.executeUpdate();
        }
    }
}
