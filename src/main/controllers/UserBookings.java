package main.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.models.Booking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static main.Main.*;

public class UserBookings {
    private Stage stage;
    private Scene scene;
    @FXML
    private TableColumn<Booking, Integer> bookingId;

    @FXML
    private TableColumn<Booking, String> date;

    @FXML
    private TableColumn<Booking, String> film;

    @FXML
    private TableView<Booking> myBookingTable;

    @FXML
    private TableColumn<Booking, Double> price;

    @FXML
    private TableColumn<Booking, String> seat;


    @FXML
    private TableColumn<Booking, String> time;

    @FXML
    private Label uemail;

    @FXML
    private Label uname;

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/u_home_page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/intro_page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static ObservableList<Booking> myBookingsList = FXCollections.observableArrayList();
    private void reset() {
        myBookingsList.removeAll(currentCustomer.getMyBooking());
        myBookingTable.getItems().clear();
        for (Booking b:bookings){
            if(b.getUserEmail().equals(currentCustomer.getEmail())){
                myBookingsList.add(b);
            }
        }
        currentCustomer.setMyBooking(new ArrayList<>(myBookingsList));
    }
    @FXML
    void initialize(){
        uemail.setText(currentCustomer.getEmail());
        uname.setText(currentCustomer.getName());
        reset();
        bookingId.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("bookingId"));
        film.setCellValueFactory(new PropertyValueFactory<Booking, String>("filmTitle"));
        time.setCellValueFactory(new PropertyValueFactory<Booking, String>("time"));
        date.setCellValueFactory(new PropertyValueFactory<Booking, String>("date"));
        price.setCellValueFactory(new PropertyValueFactory<Booking, Double>("totalPrice"));
        seat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSeat().toString()));

        myBookingTable.setItems(myBookingsList);
    }

}
