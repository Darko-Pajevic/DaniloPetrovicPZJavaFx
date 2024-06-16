package com.example.hotelreservationsystem.models;

import java.time.LocalDate;

/**
 * Model klase za rezervaciju.
 */
public class Reservation {
    private int id;
    private int userId;
    private int roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;

    /**
     * Konstruktor za klasu Reservation.
     *
     * @param id         ID rezervacije
     * @param userId     ID korisnika
     * @param roomId     ID sobe
     * @param startDate  Datum početka rezervacije
     * @param endDate    Datum završetka rezervacije
     * @param totalPrice Ukupna cena rezervacije
     */
    public Reservation(int id, int userId, int roomId, LocalDate startDate, LocalDate endDate, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    /**
     * Vraća ID rezervacije.
     *
     * @return ID rezervacije
     */
    public int getId() {
        return id;
    }

    /**
     * Vraća ID korisnika.
     *
     * @return ID korisnika
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Postavlja ID korisnika.
     *
     * @param userId ID korisnika
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Vraća ID sobe.
     *
     * @return ID sobe
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * Postavlja ID sobe.
     *
     * @param roomId ID sobe
     */
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    /**
     * Vraća datum početka rezervacije.
     *
     * @return datum početka rezervacije
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Postavlja datum početka rezervacije.
     *
     * @param startDate datum početka rezervacije
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Vraća datum završetka rezervacije.
     *
     * @return datum završetka rezervacije
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Postavlja datum završetka rezervacije.
     *
     * @param endDate datum završetka rezervacije
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Vraća ukupnu cenu rezervacije.
     *
     * @return ukupna cena rezervacije
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Postavlja ukupnu cenu rezervacije.
     *
     * @param totalPrice ukupna cena rezervacije
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", userId=" + userId +
                ", roomId=" + roomId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
