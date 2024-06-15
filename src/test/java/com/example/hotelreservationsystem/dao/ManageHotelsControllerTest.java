package com.example.hotelreservationsystem.controllers;

import com.example.hotelreservationsystem.dao.HotelDAO;
import com.example.hotelreservationsystem.models.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ManageHotelsControllerTest {

    private ManageHotelsController controller;
    private HotelDAO hotelDAO;
    private ObservableList<Hotel> hotelList;

    @BeforeEach
    void setUp() {
        hotelDAO = Mockito.mock(HotelDAO.class);
        controller = new ManageHotelsController();
        controller.hotelDAO = hotelDAO;
        hotelList = FXCollections.observableArrayList();
        controller.hotelList = hotelList;
    }

    @Test
    void testInitialize() throws SQLException {
        Hotel hotel = new Hotel(1, "Test Hotel", "Test Address", 4.5f);
        when(hotelDAO.getAllHotels()).thenReturn(List.of(hotel));
        controller.initialize();
        assertEquals(1, controller.hotelList.size());
        assertEquals("Test Hotel", controller.hotelList.get(0).getName());
    }

    @Test
    void testHandleAddHotel() throws SQLException {
        controller.nameField = new TextField("Test Hotel");
        controller.addressField = new TextField("Test Address");
        controller.ratingField = new TextField("4.5");
        Hotel hotel = new Hotel(0, "Test Hotel", "Test Address", 4.5f);
        when(hotelDAO.createHotel(any(Hotel.class))).thenReturn(true);
        controller.handleAddHotel();
        assertEquals(1, controller.hotelList.size());
        assertEquals("Test Hotel", controller.hotelList.get(0).getName());
    }
}
