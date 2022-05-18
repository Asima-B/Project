package main.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class LoginAsAdmin {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField adminEmail;

    @FXML
    private TextField adminPassword;

    @FXML
    void goToCreateAccount(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/login_as_user.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void loginAsAdmin(ActionEvent event) throws IOException {
        if (adminEmail.getText().equals(Admin.getEmail()) && adminPassword.getText().equals(Admin.getPassword())){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/admin_home_page.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }else{
            JOptionPane.showMessageDialog(null,"Email or password is incorrect!");
        }
    }

}