package com.example.hotelreservationsystem.models;

/**
 * Model klase za uslugu.
 */
public class Service {
    private int id;
    private String name;
    private double price;

    /**
     * Konstruktor za klasu Service.
     *
     * @param id    ID usluge
     * @param name  Ime usluge
     * @param price Cena usluge
     */
    public Service(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Vraća ID usluge.
     *
     * @return ID usluge
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja ID usluge.
     *
     * @param id ID usluge
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća ime usluge.
     *
     * @return ime usluge
     */
    public String getName() {
        return name;
    }

    /**
     * Postavlja ime usluge.
     *
     * @param name ime usluge
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Vraća cenu usluge.
     *
     * @return cena usluge
     */
    public double getPrice() {
        return price;
    }

    /**
     * Postavlja cenu usluge.
     *
     * @param price cena usluge
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Service{id=" + id + ", name='" + name + "', price=" + price + "}";
    }
}
