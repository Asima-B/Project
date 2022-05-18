package main.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import main.models.Booking;
import main.models.Seat;

import java.io.BufferedReader;
import java.io.FileReader;

import static main.Main.bookings;
import static main.Main.films;

public class AdminBookingsController extends Navigate {

    @FXML
    private TableColumn<Booking, String> date;

    @FXML
    private TableColumn<Booking, Integer> id;

    @FXML
    private TableColumn<Booking, Double> price;




    @FXML
    private TableColumn<Booking, String> time;

    @FXML
    private TableColumn<Booking, String> userEmail;

    @FXML
    private TableColumn<Booking, String> filmTitle;

    @FXML
    private TableView<Booking> bookingsTable;

    @FXML
    private TableColumn<Booking, String> seat;



    public static ObservableList<Booking> bookingObservableList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        reset();
        bookingObservableList.addAll(bookings);
        id.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("bookingId"));
        userEmail.setCellValueFactory(new PropertyValueFactory<Booking, String>("userEmail"));
        filmTitle.setCellValueFactory(new PropertyValueFactory<Booking, String>("filmTitle"));
        time.setCellValueFactory(new PropertyValueFactory<Booking, String>("time"));
        date.setCellValueFactory(new PropertyValueFactory<Booking, String>("date"));
        price.setCellValueFactory(new PropertyValueFactory<Booking, Double>("totalPrice"));
        seat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSeat().toString()));

        bookingsTable.setItems(bookingObservableList);
    }

    private void reset() {
        bookingObservableList.removeAll(films);
        bookingsTable.getItems().clear();
    }


}
