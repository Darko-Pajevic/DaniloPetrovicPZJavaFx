package com.example.hotelreservationsystem.models;

/**
 * Model klase za hotel.
 */
public class Hotel {
    private int id;
    private String name;
    private String address;
    private float rating;

    /**
     * Konstruktor za klasu Hotel.
     *
     * @param id      ID hotela
     * @param name    Ime hotela
     * @param address Adresa hotela
     * @param rating  Ocena hotela
     */
    public Hotel(int id, String name, String address, float rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    /**
     * Vraća ID hotela.
     *
     * @return ID hotela
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja ID hotela.
     *
     * @param id ID hotela
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća ime hotela.
     *
     * @return ime hotela
     */
    public String getName() {
        return name;
    }

    /**
     * Postavlja ime hotela.
     *
     * @param name ime hotela
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Vraća adresu hotela.
     *
     * @return adresa hotela
     */
    public String getAddress() {
        return address;
    }

    /**
     * Postavlja adresu hotela.
     *
     * @param address adresa hotela
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Vraća ocenu hotela.
     *
     * @return ocena hotela
     */
    public float getRating() {
        return rating;
    }

    /**
     * Postavlja ocenu hotela.
     *
     * @param rating ocena hotela
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Hotel{id=" + id + ", name='" + name + "', address='" + address + "', rating=" + rating + "}";
    }
}
