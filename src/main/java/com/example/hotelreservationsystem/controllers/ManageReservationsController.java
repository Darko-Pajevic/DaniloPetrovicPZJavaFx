package com.example.hotelreservationsystem.controllers;

import com.example.hotelreservationsystem.dao.ReservationDAO;
import com.example.hotelreservationsystem.models.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kontroler za upravljanje rezervacijama.
 */
public class ManageReservationsController {

    @FXML
    private ComboBox<Integer> userIdComboBox;
    @FXML
    private ComboBox<Integer> roomIdComboBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextField totalPriceField;
    @FXML
    private TableView<Reservation> reservationsTable;
    @FXML
    private TableColumn<Reservation, Integer> idColumn;
    @FXML
    private TableColumn<Reservation, Integer> userIdColumn;
    @FXML
    private TableColumn<Reservation, Integer> roomIdColumn;
    @FXML
    private TableColumn<Reservation, LocalDate> startDateColumn;
    @FXML
    private TableColumn<Reservation, LocalDate> endDateColumn;
    @FXML
    private TableColumn<Reservation, Double> totalPriceColumn;
    @FXML
    private Button backButton;

    private ObservableList<Reservation> reservationList = FXCollections.observableArrayList();
    private ReservationDAO reservationDAO = new ReservationDAO();
    private static final Logger LOGGER = Logger.getLogger(ManageReservationsController.class.getName());

    /**
     * Inicijalizacija kontrolera.
     */
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        loadReservations();

        // Inicijalizacija vrednosti za ComboBox-ove (ovo bi trebalo da bude dinamički učitano iz baze)
        userIdComboBox.setItems(FXCollections.observableArrayList(1, 2, 3)); // primer vrednosti
        roomIdComboBox.setItems(FXCollections.observableArrayList(101, 102, 103)); // primer vrednosti
    }

    /**
     * Učitavanje rezervacija iz baze.
     */
    private void loadReservations() {
        reservationList.clear();
        try {
            reservationList.addAll(reservationDAO.getAllReservations());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Greška pri učitavanju rezervacija iz baze podataka.", e);
            showAlert(Alert.AlertType.ERROR, "Greška u bazi podataka", "Greška pri učitavanju rezervacija iz baze podataka.");
        }
        reservationsTable.setItems(reservationList);
    }

    /**
     * Rukovanje događajem za dodavanje rezervacije.
     */
    @FXML
    private void handleAddReservation() {
        Integer userId = userIdComboBox.getValue();
        Integer roomId = roomIdComboBox.getValue();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        double totalPrice;

        if (userId == null || roomId == null || startDate == null || endDate == null) {
            showAlert(Alert.AlertType.ERROR, "Neispravan unos", "Molimo popunite sva polja.");
            return;
        }

        try {
            totalPrice = Double.parseDouble(totalPriceField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Neispravan unos", "Unesite validnu ukupnu cenu.");
            return;
        }

        Reservation reservation = new Reservation(0, userId, roomId, startDate, endDate, totalPrice);

        try {
            reservationDAO.addReservation(reservation);
            reservationList.add(reservation);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Uspeh", "Rezervacija je uspešno dodata.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Greška pri dodavanju rezervacije u bazu podataka.", e);
            showAlert(Alert.AlertType.ERROR, "Greška u bazi podataka", "Greška pri dodavanju rezervacije u bazu podataka.");
        }
    }

    /**
     * Rukovanje događajem za ažuriranje rezervacije.
     */
    @FXML
    private void handleUpdateReservation() {
        Reservation selectedReservation = reservationsTable.getSelectionModel().getSelectedItem();
        if (selectedReservation == null) {
            showAlert(Alert.AlertType.ERROR, "No Selection", "Please select a reservation to update.");
            return;
        }

        Integer userId = userIdComboBox.getValue();
        Integer roomId = roomIdComboBox.getValue();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        double totalPrice;

        if (userId == null || roomId == null || startDate == null || endDate == null) {
            showAlert(Alert.AlertType.ERROR, "Neispravan unos", "Molimo popunite sva polja.");
            return;
        }

        try {
            totalPrice = Double.parseDouble(totalPriceField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Neispravan unos", "Unesite validnu ukupnu cenu.");
            return;
        }

        selectedReservation.setUserId(userId);
        selectedReservation.setRoomId(roomId);
        selectedReservation.setStartDate(startDate);
        selectedReservation.setEndDate(endDate);
        selectedReservation.setTotalPrice(totalPrice);

        try {
            reservationDAO.updateReservation(selectedReservation);
            reservationsTable.refresh();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Uspeh", "Rezervacija je uspešno ažurirana.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Greška pri ažuriranju rezervacije u bazi podataka.", e);
            showAlert(Alert.AlertType.ERROR, "Greška u bazi podataka", "Greška pri ažuriranju rezervacije u bazi podataka.");
        }
    }

    /**
     * Rukovanje događajem za brisanje rezervacije.
     */
    @FXML
    private void handleDeleteReservation() {
        Reservation selectedReservation = reservationsTable.getSelectionModel().getSelectedItem();
        if (selectedReservation == null) {
            showAlert(Alert.AlertType.ERROR, "No Selection", "Please select a reservation to delete.");
            return;
        }

        try {
            reservationDAO.deleteReservation(selectedReservation.getId());
            reservationList.remove(selectedReservation);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Uspeh", "Rezervacija je uspešno obrisana.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Greška pri brisanju rezervacije iz baze podataka.", e);
            showAlert(Alert.AlertType.ERROR, "Greška u bazi podataka", "Greška pri brisanju rezervacije iz baze podataka.");
        }
    }

    /**
     * Čišćenje polja za unos.
     */
    private void clearFields() {
        userIdComboBox.getSelectionModel().clearSelection();
        roomIdComboBox.getSelectionModel().clearSelection();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        totalPriceField.clear();
    }

    /**
     * Prikazivanje poruke o grešci.
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Povratak na prethodni ekran.
     */
    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
