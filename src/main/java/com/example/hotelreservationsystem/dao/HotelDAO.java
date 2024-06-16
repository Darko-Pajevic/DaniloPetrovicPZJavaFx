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
