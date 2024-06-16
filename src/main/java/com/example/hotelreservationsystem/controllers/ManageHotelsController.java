package com.example.hotelreservationsystem.controllers;

import com.example.hotelreservationsystem.dao.HotelDAO;
import com.example.hotelreservationsystem.models.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Kontroler za upravljanje hotelima.
 */
public class ManageHotelsController {

    @FXML
    public TextField nameField;
    @FXML
    public TextField addressField;
    @FXML
    public TextField ratingField;
    @FXML
    private TableView<Hotel> hotelsTable;
    @FXML
    public TableColumn<Hotel, Integer> idColumn;
    @FXML
    public TableColumn<Hotel, String> nameColumn;
    @FXML
    public TableColumn<Hotel, String> addressColumn;
    @FXML
    public TableColumn<Hotel, Float> ratingColumn;
    @FXML
    private Label weatherInfoLabel;

    public ObservableList<Hotel> hotelList = FXCollections.observableArrayList();
    public HotelDAO hotelDAO = new HotelDAO();

    /**
     * Inicijalizacija kontrolera.
     * Postavlja vrednosti za kolone u tabeli i učitava podatke iz baze.
     */
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        try {
            hotelList.addAll(hotelDAO.getAllHotels());
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error loading hotels from the database.");
        }

        hotelsTable.setItems(hotelList);
    }

    /**
     * Rukovanje događajem za dodavanje hotela.
     * Proverava ispravnost unosa i dodaje novi hotel u bazu podataka.
     */
    @FXML
    public void handleAddHotel() {
        String name = nameField.getText();
        String address = addressField.getText();
        float rating;

        try {
            rating = Float.parseFloat(ratingField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid rating.");
            return;
        }

        Hotel hotel = new Hotel(0, name, address, rating);

        try {
            if (hotelDAO.createHotel(hotel)) {
                hotelList.add(hotel);
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Hotel added successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Error adding hotel to the database.");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error adding hotel to the database.");
        }
    }

    /**
     * Čišćenje polja za unos.
     */
    private void clearFields() {
        nameField.clear();
        addressField.clear();
        ratingField.clear();
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

    /**
     * Prikazivanje vremenskih informacija za odabranu lokaciju hotela.
     */
    @FXML
    private void handleShowWeather() {
        String selectedHotelLocation = "London"; // Dobijte stvarnu lokaciju od odabranog hotela
        String weatherInfo = hotelDAO.getWeatherForHotel(selectedHotelLocation);
        weatherInfoLabel.setText(weatherInfo);
    }
}
