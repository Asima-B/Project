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
import main.models.Customer;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class CreateAccountController {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    void goToSignIn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/login_as_user.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void register(ActionEvent event) {
        try {
            File f = new File("users.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));

            if (isExisingAcount(email.getText())) {
                JOptionPane.showMessageDialog(null, "Account for this email already exists!");
            } else {
                Customer nuwCust = new Customer(name.getText(), email.getText(), password.getText());
                pw.append(name.getText() + ","+email.getText() + ","+ password.getText() + "\n");
                pw.close();

                JOptionPane.showMessageDialog(null, "You have successfully registered!");
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/login_as_user.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        } catch (Exception e) {
        }
    }

    private boolean isExisingAcount(String text) {
return false;
    }
}


