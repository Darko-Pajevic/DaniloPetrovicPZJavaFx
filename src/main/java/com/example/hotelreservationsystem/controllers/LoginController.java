package com.example.hotelreservationsystem.controllers;

import com.example.hotelreservationsystem.dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Kontroler za upravljanje login funkcionalnošću.
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UserDAO userDAO = new UserDAO();

    /**
     * Rukuje događajem za login dugme.
     * @param event događaj akcije
     */
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            if (userDAO.authenticateUser(username, password)) {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                showAlert("Invalid username or password.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert("An error occurred during login.");
        }
    }

    /**
     * Prikazuje alert dijalog sa zadatom porukom.
     * @param message poruka koja će biti prikazana u alert dijalogu
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
