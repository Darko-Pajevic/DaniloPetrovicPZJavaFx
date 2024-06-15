package com.example.hotelreservationsystem.controllers;

import com.example.hotelreservationsystem.dao.EmployeeDAO;
import com.example.hotelreservationsystem.models.Employee;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Kontroler za upravljanje zaposlenima.
 */
public class ManageEmployeesController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField positionField;
    @FXML
    private TextField salaryField;
    @FXML
    private TableView<Employee> employeesTable;
    @FXML
    private TableColumn<Employee, Integer> idColumn;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> positionColumn;
    @FXML
    private TableColumn<Employee, Double> salaryColumn;
    @FXML
    private Button backButton;

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    /**
     * Inicijalizacija kontrolera.
     * Postavlja vrednosti za kolone u tabeli i učitava podatke iz baze.
     */
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        // Učitavanje svih zaposlenih iz baze podataka
        try {
            employeeList.addAll(employeeDAO.getAllEmployees());
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Greška u bazi podataka", "Greška pri učitavanju zaposlenih iz baze podataka.");
        }

        employeesTable.setItems(employeeList);
    }

    /**
     * Rukovanje događajem za dodavanje zaposlenog.
     * Proverava ispravnost unosa i dodaje novog zaposlenog u bazu podataka.
     */
    @FXML
    private void handleAddEmployee() {
        String name = nameField.getText();
        String position = positionField.getText();
        double salary;

        // Provera ispravnosti unosa plate
        try {
            salary = Double.parseDouble(salaryField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Neispravan unos", "Unesite validnu platu.");
            return;
        }

        Employee employee = new Employee(0, name, position, salary);

        // Dodavanje zaposlenog u bazu podataka
        try {
            if (employeeDAO.createEmployee(employee)) {
                employeeList.add(employee);
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Uspeh", "Zaposleni je uspešno dodat.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Greška u bazi podataka", "Greška pri dodavanju zaposlenog u bazu podataka.");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Greška u bazi podataka", "Greška pri dodavanju zaposlenog u bazu podataka.");
        }
    }

    /**
     * Čišćenje polja za unos.
     */
    private void clearFields() {
        nameField.clear();
        positionField.clear();
        salaryField.clear();
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
