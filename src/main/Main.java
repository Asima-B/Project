package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.models.Booking;
import main.models.Customer;
import main.models.Film;
import main.models.Seat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Booking> bookings = new ArrayList<>();
    public static ArrayList<Film> films = new ArrayList<>();
    public static Customer currentCustomer;
    public static Film selectedFilm;

    public static void getUsers() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("users.txt"));
            String s = "";
            while ((s = br.readLine()) != null) {
                String[] data = s.split(",");

                Customer customer = new Customer(data[0], data[1], data[2]);
                customers.add(customer);

            }
        } catch (Exception e) {
        }
    }

    public static void getFilms() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("films.txt"));
            String s = "";
            while ((s = br.readLine()) != null) {
                String[] data;
                data = s.split(",");
                Film film = new Film(data[0], data[1], data[2], data[3], new String[]{data[4], data[5], data[6]}, Double.parseDouble(data[7]));
                films.add(film);

            }
        } catch (Exception e) {
        }
    }

    public static void getBookings() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("bookings.txt"));
            String s = "";
            while ((s = br.readLine()) != null) {
                String[] data;
                data = s.split(",");
                Booking booking = new Booking(data[1],data[2], data[3],data[4],new Seat(Integer.parseInt(data[5]),Integer.parseInt(data[6]),true), Double.parseDouble(data[7]));
                bookings.add(booking);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getUsers();
        getFilms();
        getBookings();
        launch(args);

    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/intro_page.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Booking");
        stage.setWidth(1000);
        stage.setHeight(650);
        stage.show();
    }
}
