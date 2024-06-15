package com.example.hotelreservationsystem.models;

/**
 * Model klase za korisnika.
 */
public class Customer {
    private int id;
    private String name;
    private String email;
    private String phone;

    /**
     * Konstruktor za klasu Customer.
     *
     * @param id    ID korisnika
     * @param name  Ime korisnika
     * @param email Email korisnika
     * @param phone Telefon korisnika
     */
    public Customer(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Vraća ID korisnika.
     *
     * @return ID korisnika
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja ID korisnika.
     *
     * @param id ID korisnika
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća ime korisnika.
     *
     * @return ime korisnika
     */
    public String getName() {
        return name;
    }

    /**
     * Postavlja ime korisnika.
     *
     * @param name ime korisnika
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Vraća email korisnika.
     *
     * @return email korisnika
     */
    public String getEmail() {
        return email;
    }

    /**
     * Postavlja email korisnika.
     *
     * @param email email korisnika
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Vraća telefon korisnika.
     *
     * @return telefon korisnika
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Postavlja telefon korisnika.
     *
     * @param phone telefon korisnika
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{id=" + id + ", name='" + name + "', email='" + email + "', phone='" + phone + "'}";
    }
}
