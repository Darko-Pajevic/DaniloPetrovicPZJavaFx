package com.example.hotelreservationsystem.models;

/**
 * Model klase za sobu.
 */
public class Room {
    private int id;
    private int hotelId;
    private String roomNumber;
    private String type;
    private double pricePerNight;

    /**
     * Konstruktor za klasu Room.
     *
     * @param id            ID sobe
     * @param hotelId       ID hotela
     * @param roomNumber    Broj sobe
     * @param type          Tip sobe
     * @param pricePerNight Cena po noći
     */
    public Room(int id, int hotelId, String roomNumber, String type, double pricePerNight) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    /**
     * Vraća ID sobe.
     *
     * @return ID sobe
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja ID sobe.
     *
     * @param id ID sobe
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća ID hotela.
     *
     * @return ID hotela
     */
    public int getHotelId() {
        return hotelId;
    }

    /**
     * Postavlja ID hotela.
     *
     * @param hotelId ID hotela
     */
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * Vraća broj sobe.
     *
     * @return broj sobe
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Postavlja broj sobe.
     *
     * @param roomNumber broj sobe
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Vraća tip sobe.
     *
     * @return tip sobe
     */
    public String getType() {
        return type;
    }

    /**
     * Postavlja tip sobe.
     *
     * @param type tip sobe
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Vraća cenu po noći.
     *
     * @return cena po noći
     */
    public double getPricePerNight() {
        return pricePerNight;
    }

    /**
     * Postavlja cenu po noći.
     *
     * @param pricePerNight cena po noći
     */
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return "Room{id=" + id + ", hotelId=" + hotelId + ", roomNumber='" + roomNumber + "', type='" + type + "', pricePerNight=" + pricePerNight + "}";
    }
}
