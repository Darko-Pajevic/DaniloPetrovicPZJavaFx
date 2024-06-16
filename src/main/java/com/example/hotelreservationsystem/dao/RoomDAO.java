package com.example.hotelreservationsystem.dao;

import com.example.hotelreservationsystem.models.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) za entitet Room.
 */
public class RoomDAO {

    /**
     * Dohvata sve sobe iz baze podataka.
     *
     * @return lista svih soba
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM Rooms";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                rooms.add(new Room(rs.getInt("id"), rs.getInt("hotel_id"), rs.getString("room_number"), rs.getString("type"), rs.getDouble("price_per_night")));
            }
        }
        return rooms;
    }

    /**
     * Kreira novu sobu u bazi podataka.
     *
     * @param room objekat sobe koji se dodaje
     * @return true ako je soba uspešno dodata, false inače
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public boolean createRoom(Room room) throws SQLException {
        String query = "INSERT INTO Rooms (hotel_id, room_number, type, price_per_night) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, room.getHotelId());
            stmt.setString(2, room.getRoomNumber());
            stmt.setString(3, room.getType());
            stmt.setDouble(4, room.getPricePerNight());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

    /**
     * Ažurira postojeću sobu u bazi podataka.
     *
     * @param room objekat sobe sa ažuriranim podacima
     * @return true ako je soba uspešno ažurirana, false inače
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public boolean updateRoom(Room room) throws SQLException {
        String query = "UPDATE Rooms SET hotel_id = ?, room_number = ?, type = ?, price_per_night = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, room.getHotelId());
            stmt.setString(2, room.getRoomNumber());
            stmt.setString(3, room.getType());
            stmt.setDouble(4, room.getPricePerNight());
            stmt.setInt(5, room.getId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    /**
     * Briše sobu iz baze podataka.
     *
     * @param roomId ID sobe koja se briše
     * @return true ako je soba uspešno obrisana, false inače
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public boolean deleteRoom(int roomId) throws SQLException {
        String query = "DELETE FROM Rooms WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, roomId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        }
    }
}
