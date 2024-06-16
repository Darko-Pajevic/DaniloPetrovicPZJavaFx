package com.example.hotelreservationsystem.dao;

import com.example.hotelreservationsystem.models.Hotel;
import com.example.hotelreservationsystem.service.WeatherService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) za entitet Hotel.
 */
public class HotelDAO {

    /**
     * Dohvata sve hotele iz baze podataka.
     *
     * @return lista svih hotela
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public List<Hotel> getAllHotels() throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM Hotels";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                hotels.add(new Hotel(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getFloat("rating")));
            }
        }
        return hotels;
    }

    /**
     * Kreira novi hotel u bazi podataka.
     *
     * @param hotel objekat hotela koji se dodaje
     * @return true ako je hotel uspešno dodat, false inače
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public boolean createHotel(Hotel hotel) throws SQLException {
        String query = "INSERT INTO Hotels (name, address, rating) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getAddress());
            stmt.setFloat(3, hotel.getRating());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

    /**
     * Ažurira postojeće informacije o hotelu u bazi podataka.
     *
     * @param hotel objekat hotela sa ažuriranim informacijama
     * @return true ako je hotel uspešno ažuriran, false inače
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public boolean updateHotel(Hotel hotel) throws SQLException {
        String query = "UPDATE Hotels SET name = ?, address = ?, rating = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getAddress());
            stmt.setFloat(3, hotel.getRating());
            stmt.setInt(4, hotel.getId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    /**
     * Briše hotel iz baze podataka.
     *
     * @param hotelId ID hotela koji se briše
     * @return true ako je hotel uspešno obrisan, false inače
     * @throws SQLException ako dođe do greške prilikom pristupa bazi podataka
     */
    public boolean deleteHotel(int hotelId) throws SQLException {
        String query = "DELETE FROM Hotels WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, hotelId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    /**
     * Dohvata vremenske informacije za određenu lokaciju hotela.
     *
     * @param hotelLocation lokacija hotela
     * @return vremenske informacije
     */
    public String getWeatherForHotel(String hotelLocation) {
        try {
            return WeatherService.getWeather(hotelLocation);
        } catch (Exception e) {
            e.printStackTrace();
            return "Unable to retrieve weather information.";
        }
    }
}
