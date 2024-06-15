package com.example.hotelreservationsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Kontroler za glavnu komandnu tablu.
 */
public class DashboardController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Rukuje događajem za upravljanje hotelima.
     * @param event događaj akcije
     * @throws IOException ako dođe do greške prilikom učitavanja FXML datoteke
     */
    @FXML
    public void handleManageHotels(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/manage_hotels.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Rukuje događajem za upravljanje sobama.
     * @param event događaj akcije
     * @throws IOException ako dođe do greške prilikom učitavanja FXML datoteke
     */
    @FXML
    public void handleManageRooms(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/manage_rooms.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Rukuje događajem za upravljanje rezervacijama.
     * @param event događaj akcije
     * @throws IOException ako dođe do greške prilikom učitavanja FXML datoteke
     */
    @FXML
    public void handleManageReservations(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/manage_reservations.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Rukuje događajem za upravljanje zaposlenima.
     * @param event događaj akcije
     * @throws IOException ako dođe do greške prilikom učitavanja FXML datoteke
     */
    @FXML
    public void handleManageEmployees(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/manage_employees.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Rukuje događajem za odjavu korisnika.
     * @param event događaj akcije
     * @throws IOException ako dođe do greške prilikom učitavanja FXML datoteke
     */
    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
