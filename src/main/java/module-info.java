module com.example.danilopetrovicpz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.hotelreservationsystem to javafx.fxml;
    opens com.example.hotelreservationsystem.models to javafx.base;
    exports com.example.hotelreservationsystem;
    exports com.example.hotelreservationsystem.controllers;
    opens com.example.hotelreservationsystem.controllers to javafx.fxml;
}
