package com.example.hotelreservationsystem.controllers;

import com.example.hotelreservationsystem.dao.RoomDAO;
import com.example.hotelreservationsystem.models.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Kontroler za upravljanje sobama.
 */
public class ManageRoomsController {

    @FXML
    private TextField hotelIdField;
    @FXML
    private TextField roomNumberField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField priceField;
    @FXML
    private TableView<Room> roomsTable;
    @FXML
    private TableColumn<Room, Integer> idColumn;
    @FXML
    private TableColumn<Room, Integer> hotelIdColumn;
    @FXML
    private TableColumn<Room, String> roomNumberColumn;
    @FXML
    private TableColumn<Room, String> typeColumn;
    @FXML
    private TableColumn<Room, Double> priceColumn;

    private ObservableList<Room> roomList = FXCollections.observableArrayList();
    private RoomDAO roomDAO = new RoomDAO();

    /**
     * Inicijalizacija kontrolera.
     * Postavlja vrednosti za kolone u tabeli i učitava podatke iz baze.
     */
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        hotelIdColumn.setCellValueFactory(new PropertyValueFactory<>("hotelId"));
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));

        try {
            roomList.addAll(roomDAO.getAllRooms());
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error loading rooms from the database.");
        }

        roomsTable.setItems(roomList);
    }

    /**
     * Rukovanje događajem za dodavanje sobe.
     * Proverava ispravnost unosa i dodaje novu sobu u bazu podataka.
     */
    @FXML
    private void handleAddRoom() {
        int hotelId;
        String roomNumber = roomNumberField.getText();
        String type = typeField.getText();
        double price;

        try {
            hotelId = Integer.parseInt(hotelIdField.getText());
            price = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numbers for hotel ID and price.");
            return;
        }

        Room room = new Room(0, hotelId, roomNumber, type, price);

        try {
            if (roomDAO.createRoom(room)) {
                roomList.add(room);
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Room added successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Error adding room to the database.");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error adding room to the database.");
        }
    }

    /**
     * Rukovanje događajem za ažuriranje sobe.
     * Proverava ispravnost unosa i ažurira postojeću sobu u bazi podataka.
     */
    @FXML
    private void handleUpdateRoom() {
        Room selectedRoom = roomsTable.getSelectionModel().getSelectedItem();
        if (selectedRoom == null) {
            showAlert(Alert.AlertType.ERROR, "No Selection", "Please select a room to update.");
            return;
        }

        int hotelId;
        String roomNumber = roomNumberField.getText();
        String type = typeField.getText();
        double price;

        try {
            hotelId = Integer.parseInt(hotelIdField.getText());
            price = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numbers for hotel ID and price.");
            return;
        }

        selectedRoom.setHotelId(hotelId);
        selectedRoom.setRoomNumber(roomNumber);
        selectedRoom.setType(type);
        selectedRoom.setPricePerNight(price);

        try {
            if (roomDAO.updateRoom(selectedRoom)) {
                roomsTable.refresh();
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Room updated successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Error updating room in the database.");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error updating room in the database.");
        }
    }

    /**
     * Rukovanje događajem za brisanje sobe.
     * Briše odabranu sobu iz baze podataka.
     */
    @FXML
    private void handleDeleteRoom() {
        Room selectedRoom = roomsTable.getSelectionModel().getSelectedItem();
        if (selectedRoom == null) {
            showAlert(Alert.AlertType.ERROR, "No Selection", "Please select a room to delete.");
            return;
        }

        try {
            if (roomDAO.deleteRoom(selectedRoom.getId())) {
                roomList.remove(selectedRoom);
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Room deleted successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Error deleting room from the database.");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error deleting room from the database.");
        }
    }

    /**
     * Čišćenje polja za unos.
     */
    private void clearFields() {
        hotelIdField.clear();
        roomNumberField.clear();
        typeField.clear();
        priceField.clear();
    }

    /**
     * Prikazivanje poruke o grešci.
     * @param alertType tip alerta (ERROR, INFORMATION, itd.)
     * @param title naslov alerta
     * @param message poruka koja će biti prikazana u alert dijalogu
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
     * @param event događaj akcije
     * @throws IOException ako se ne može učitati dashboard.fxml
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
