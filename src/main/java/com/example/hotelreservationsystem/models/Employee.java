package com.example.hotelreservationsystem.models;

/**
 * Model klase za zaposlenog.
 */
public class Employee {
    private int id;
    private String name;
    private String position;
    private double salary;

    /**
     * Konstruktor za klasu Employee.
     *
     * @param id       ID zaposlenog
     * @param name     Ime zaposlenog
     * @param position Pozicija zaposlenog
     * @param salary   Plata zaposlenog
     */
    public Employee(int id, String name, String position, double salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    /**
     * Vraća ID zaposlenog.
     *
     * @return ID zaposlenog
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja ID zaposlenog.
     *
     * @param id ID zaposlenog
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća ime zaposlenog.
     *
     * @return ime zaposlenog
     */
    public String getName() {
        return name;
    }

    /**
     * Postavlja ime zaposlenog.
     *
     * @param name ime zaposlenog
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Vraća poziciju zaposlenog.
     *
     * @return pozicija zaposlenog
     */
    public String getPosition() {
        return position;
    }

    /**
     * Postavlja poziciju zaposlenog.
     *
     * @param position pozicija zaposlenog
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Vraća platu zaposlenog.
     *
     * @return plata zaposlenog
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Postavlja platu zaposlenog.
     *
     * @param salary plata zaposlenog
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', position='" + position + "', salary=" + salary + "}";
    }
}
