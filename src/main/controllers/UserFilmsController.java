package main.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.models.Film;

import java.io.IOException;
import java.util.Objects;

import static main.Main.*;
import static main.controllers.AdminFilmsController.filmsObservableList;

public class UserFilmsController {
    private Stage stage;
    private Scene scene;

    @FXML
    private TableColumn<Film, String > description;

    @FXML
    private TableColumn<Film, String> endDate;

    @FXML
    private TableView<Film> filmTable;

    @FXML
    private TableColumn<Film, String> filmTitle;

    @FXML
    private TableColumn<Film, String> startDate;

    @FXML
    private TableColumn<Film, String> times;

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

    private void addButtonToTable() {
        TableColumn<Film, Void> colBtn = new TableColumn("Book");

        Callback<TableColumn<Film, Void>, TableCell<Film, Void>> cellFactory = new Callback<TableColumn<Film, Void>, TableCell<Film, Void>>() {
            @Override
            public TableCell<Film, Void> call(TableColumn<Film, Void> productVoidTableColumn) {
                final TableCell<Film, Void> cell = new TableCell<Film, Void>() {

                    private final Button btn = new Button("Book");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Film bookingFilm = getTableView().getItems().get(getIndex());
                            selectedFilm = bookingFilm;
                            Parent root = null;
                            try {
                                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/selected_film.fxml")));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        filmTable.getColumns().add(colBtn);
    }
    private void reset() {
        filmsObservableList.removeAll(films);
        filmTable.getItems().clear();
    }
    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/u_home_page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void initialize(){
        uemail.setText(currentCustomer.getEmail());
        uname.setText(currentCustomer.getName());

        reset();
        filmsObservableList.addAll(films);
        addButtonToTable();
        filmTitle.setCellValueFactory(new PropertyValueFactory<Film, String>("title"));
        times.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTimes()));

        description.setCellValueFactory(new PropertyValueFactory<Film, String>("description"));
        startDate.setCellValueFactory(new PropertyValueFactory<Film, String>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<Film, String>("endDate"));

        filmTable.setItems(filmsObservableList);
    }



}
