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
    public TextField nameField;
    @FXML
    public TextField positionField;
    @FXML
    public TextField salaryField;
    @FXML
    private TableView<Employee> employeesTable;
    @FXML
    public TableColumn<Employee, Integer> idColumn;
    @FXML
    public TableColumn<Employee, String> nameColumn;
    @FXML
    public TableColumn<Employee, String> positionColumn;
    @FXML
    public TableColumn<Employee, Double> salaryColumn;

    public ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    public EmployeeDAO employeeDAO = new EmployeeDAO();

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

        try {
            employeeList.addAll(employeeDAO.getAllEmployees());
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error loading employees from the database.");
        }

        employeesTable.setItems(employeeList);
    }

    /**
     * Rukovanje događajem za dodavanje zaposlenog.
     * Proverava ispravnost unosa i dodaje novog zaposlenog u bazu podataka.
     */
    @FXML
    public void handleAddEmployee() {
        String name = nameField.getText();
        String position = positionField.getText();
        double salary;

        try {
            salary = Double.parseDouble(salaryField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid salary.");
            return;
        }

        Employee employee = new Employee(0, name, position, salary);

        try {
            if (employeeDAO.createEmployee(employee)) {
                employeeList.add(employee);
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Employee added successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Error adding employee to the database.");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error adding employee to the database.");
        }
    }

    /**
     * Rukovanje događajem za ažuriranje zaposlenog.
     * Proverava ispravnost unosa i ažurira postojećeg zaposlenog u bazi podataka.
     */
    @FXML
    public void handleUpdateEmployee() {
        Employee selectedEmployee = employeesTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            showAlert(Alert.AlertType.ERROR, "No Selection", "Please select an employee to update.");
            return;
        }

        String name = nameField.getText();
        String position = positionField.getText();
        double salary;

        try {
            salary = Double.parseDouble(salaryField.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid salary.");
            return;
        }

        selectedEmployee.setName(name);
        selectedEmployee.setPosition(position);
        selectedEmployee.setSalary(salary);

        try {
            if (employeeDAO.updateEmployee(selectedEmployee)) {
                employeesTable.refresh();
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Employee updated successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Error updating employee in the database.");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error updating employee in the database.");
        }
    }

    /**
     * Rukovanje događajem za brisanje zaposlenog.
     * Briše odabranog zaposlenog iz baze podataka.
     */
    @FXML
    public void handleDeleteEmployee() {
        Employee selectedEmployee = employeesTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            showAlert(Alert.AlertType.ERROR, "No Selection", "Please select an employee to delete.");
            return;
        }

        try {
            if (employeeDAO.deleteEmployee(selectedEmployee.getId())) {
                employeeList.remove(selectedEmployee);
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Employee deleted successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Error deleting employee from the database.");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error deleting employee from the database.");
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
