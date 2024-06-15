package com.example.hotelreservationsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Glavna klasa za pokretanje aplikacije Hotel Reservation System.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hotel Reservation System");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Glavna metoda za pokretanje aplikacije.  USERNAME: admin, PASSWORD: admin123 ili USERNAME: user1, PASSWORD: password1 ili USERNAME: user2, PASSWORD: password2
     *
     * @param args argumenti komandne linije
     */
    public static void main(String[] args) {
        launch(args);
    }
}
