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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.models.Booking;
import main.models.Film;
import main.models.Seat;
import main.models.Ticket;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static main.Main.*;
import static main.controllers.AdminFilmsController.filmsObservableList;

public class SelectedFilmsController {

    private static Ticket ticket;
    private Stage stage;
    private Scene scene;

    @FXML
    private DatePicker dateBooking;


    @FXML
    private Text description;

    @FXML
    private Text endDate;

    @FXML
    private Label filmTitleforBooking;

    @FXML
    private Label priceBooking;

    @FXML
    private ComboBox<Integer> seatColumn;

    @FXML
    private ComboBox<Integer> seatRow;

    @FXML
    private Text startDate;

    @FXML
    private Text time;

    @FXML
    private ComboBox<String> timeBooking;

    @FXML
    private Text title;

    @FXML
    private Label uemail;

    @FXML
    private Label uname;

    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/intro_page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void initialize(){
        List<String> times = new ArrayList<String>();
        Collections.addAll(times, selectedFilm.getTimesInArray());
        ObservableList<String> timesList = FXCollections.observableArrayList(times);

        ObservableList<Integer> seats = FXCollections.observableArrayList();
        for(int i =1; i<=30;i++){
            seats.add(i);
        }

        seatColumn.setItems(seats);
        seatRow.setItems(seats);
        uemail.setText(currentCustomer.getEmail());
        uname.setText(currentCustomer.getName());

        title.setText("MOVIE TITLE: "+ selectedFilm.getTitle());
        description.setText("Description: "+selectedFilm.getDescription());
        startDate.setText(selectedFilm.getStartDate());
        endDate.setText(selectedFilm.getEndDate());
        time.setText(selectedFilm.getTimes());

        filmTitleforBooking.setText("Film: "+ selectedFilm.getTitle());
        priceBooking.setText("Price: "+selectedFilm.getPrice().toString());


        LocalDate minDate = LocalDate.parse(selectedFilm.getStartDate());
        LocalDate maxDate =  LocalDate.parse(selectedFilm.getEndDate());
        dateBooking.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
                    }});

        timeBooking.setItems(timesList);



    }
    @FXML
    void allFilms(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/all_films.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    void bookButton(ActionEvent event) throws IOException {
        Booking bookingFilm = new Booking(currentCustomer.getEmail(), selectedFilm.getTitle(),dateBooking.getValue().toString(),timeBooking.getValue(),new Seat(seatRow.getValue(),seatColumn.getValue(),true), selectedFilm.getPrice());
        bookings.add(bookingFilm);
        BufferedWriter writer = new BufferedWriter(new FileWriter("bookings.txt", true));
        writer.write(bookingFilm.getBookingId() + "," + currentCustomer.getEmail() +","
                +selectedFilm.getTitle() + "," + dateBooking.getValue() + "," + timeBooking.getValue()+ "," + bookingFilm.getSeat().getRow() + "," +bookingFilm.getSeat().getColumn() + "," + bookingFilm.getTotalPrice());
        writer.newLine();
        writer.close();
        LocalDate bookedTime = LocalDate.now();
        JOptionPane.showMessageDialog(null, "You have successfully booked! ");
        ticket = new Ticket(bookingFilm.getBookingId(),currentCustomer.getName(),selectedFilm.getTitle(),dateBooking.getValue().toString()+" "+timeBooking.getValue(),bookedTime.toString(),selectedFilm.getPrice(),bookingFilm.getSeat().toString());
        JOptionPane.showMessageDialog(null, "You can get your ticket. ");
        getTicket();
    }

    private void getTicket() {
        try {
            FileWriter myWriter = new FileWriter(currentCustomer.getName()+"-ticket.txt");
            myWriter.write("********* Ticket ID: "+ ticket.getId()+"  **********" +
                    "\n\nBooked Time: "+ticket.getBookedTime()+
                    "\nCustomer: " + ticket.getUserName()+
                    "\nFilm: " + ticket.getFilmTitle() +
                    "\nTime: " + ticket.getFilmTime() +
                    "\nSeat: "+ ticket.getSeat()+
                    "\n\nPrice: "+ticket.getPrice()+
                    "\n\n************************************************");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
