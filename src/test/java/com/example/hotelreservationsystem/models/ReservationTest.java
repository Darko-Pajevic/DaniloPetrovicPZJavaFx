package com.example.hotelreservationsystem.models;

import com.example.hotelreservationsystem.models.Reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTest {

    @Test
    public void testReservation() {
        Reservation reservation = new Reservation(1, 1, 101, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10), 1000.0);

        assertEquals(1, reservation.getId());
        assertEquals(1, reservation.getUserId());
        assertEquals(101, reservation.getRoomId());
        assertEquals(LocalDate.of(2022, 1, 1), reservation.getStartDate());
        assertEquals(LocalDate.of(2022, 1, 10), reservation.getEndDate());
        assertEquals(1000.0, reservation.getTotalPrice());

        String expectedToString = "Reservation{id=1, userId=1, roomId=101, startDate=2022-01-01, endDate=2022-01-10, totalPrice=1000.0}";
        assertEquals(expectedToString, reservation.toString());
    }
}