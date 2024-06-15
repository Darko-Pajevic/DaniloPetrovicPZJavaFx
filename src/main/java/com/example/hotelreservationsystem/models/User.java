package com.example.hotelreservationsystem.models;

/**
 * Model klase za korisnika sistema.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String email;

    /**
     * Konstruktor za klasu User.
     *
     * @param id       ID korisnika
     * @param username Korisničko ime
     * @param password Lozinka
     * @param email    Email adresa
     */
    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
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
     * Vraća korisničko ime.
     *
     * @return korisničko ime
     */
    public String getUsername() {
        return username;
    }

    /**
     * Postavlja korisničko ime.
     *
     * @param username korisničko ime
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Vraća lozinku.
     *
     * @return lozinka
     */
    public String getPassword() {
        return password;
    }

    /**
     * Postavlja lozinku.
     *
     * @param password lozinka
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Vraća email adresu.
     *
     * @return email adresa
     */
    public String getEmail() {
        return email;
    }

    /**
     * Postavlja email adresu.
     *
     * @param email email adresa
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', email='" + email + "'}";
    }
}
