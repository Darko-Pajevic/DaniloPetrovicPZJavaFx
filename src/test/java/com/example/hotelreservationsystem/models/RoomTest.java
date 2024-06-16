package com.example.hotelreservationsystem.models;

import com.example.hotelreservationsystem.models.Room;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomTest {

    @Test
    public void testRoom() {
        Room room = new Room(1, 1, "101", "Single", 100.0);

        assertEquals(1, room.getId());
        assertEquals(1, room.getHotelId());
        assertEquals("101", room.getRoomNumber());
        assertEquals("Single", room.getType());
        assertEquals(100.0, room.getPricePerNight());

        room.setId(2);
        room.setHotelId(2);
        room.setRoomNumber("102");
        room.setType("Double");
        room.setPricePerNight(200.0);

        assertEquals(2, room.getId());
        assertEquals(2, room.getHotelId());
        assertEquals("102", room.getRoomNumber());
        assertEquals("Double", room.getType());
        assertEquals(200.0, room.getPricePerNight());

        String expectedToString = "Room{id=2, hotelId=2, roomNumber='102', type='Double', pricePerNight=200.0}";
        assertEquals(expectedToString, room.toString());
    }
}