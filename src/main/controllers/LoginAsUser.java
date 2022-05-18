package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.Main;
import main.models.Customer;

import javax.swing.*;
import java.io.IOException;
import java.util.EventObject;
import java.util.Objects;

import static main.Main.currentCustomer;
import static main.Main.customers;

public class LoginAsUser {
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    void initialize() {
        Main.getUsers();
    }

    @FXML
    void goToCreateAccount(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/register.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {

        if (email.getText().isEmpty() || password.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fill out all fields!");
        } else if (notExist(email.getText())) {
            JOptionPane.showMessageDialog(null, "This account has not registered!");
        } else if (notCorrectPassword(email.getText(), password.getText())) {
            JOptionPane.showMessageDialog(null, "Not correct Password!");

        } else {

                setCurrentCustomer(email.getText(), password.getText());
                {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/u_home_page.fxml")));

                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }





    private void setCurrentCustomer(String email, String password) {
        for (Customer c : customers) {
            if (c.getEmail().equals(email) && c.getPassword().equals(password)) {
                currentCustomer = c;
            }
        }
    }

    private boolean notCorrectPassword(String email, String password) {
        for (Customer c : customers) {
            if (c.getEmail().equals(email) && c.getPassword().equals(password)) {
                return false;
            }
        }
        return true;
    }


    private boolean notExist(String email) {
        for (Customer c : customers) {
            if (c.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
}


