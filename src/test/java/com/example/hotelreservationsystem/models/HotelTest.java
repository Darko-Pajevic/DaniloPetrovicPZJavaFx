package com.example.hotelreservationsystem.models;

import com.example.hotelreservationsystem.models.Hotel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HotelTest {

    @Test
    public void testHotel() {
        Hotel hotel = new Hotel(1, "Test Hotel", "Test Address", 4.5f);

        assertEquals(1, hotel.getId());
        assertEquals("Test Hotel", hotel.getName());
        assertEquals("Test Address", hotel.getAddress());
        assertEquals(4.5f, hotel.getRating());

        hotel.setId(2);
        hotel.setName("New Test Hotel");
        hotel.setAddress("New Test Address");
        hotel.setRating(4.0f);

        assertEquals(2, hotel.getId());
        assertEquals("New Test Hotel", hotel.getName());
        assertEquals("New Test Address", hotel.getAddress());
        assertEquals(4.0f, hotel.getRating());

        String expectedToString = "Hotel{id=2, name='New Test Hotel', address='New Test Address', rating=4.0}";
        assertEquals(expectedToString, hotel.toString());
    }
}