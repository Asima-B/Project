package main.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import main.models.Film;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static main.Main.films;

public class AdminFilmsController extends Navigate {

    @FXML
    private TableColumn<Film, String> startDate;

    @FXML
    private TableColumn<Film, String> endDate;

    @FXML
    private TableColumn<Film, String> description;

    @FXML
    private TableView<Film> filmTable;

    @FXML
    private ComboBox<String> filmTime1;

    @FXML
    private ComboBox<String> filmTime2;

    @FXML
    private ComboBox<String> filmTime3;

    @FXML
    private TableColumn<Film, String> filmTitle;

    @FXML
    private DatePicker fromDate;

    @FXML
    private TextArea newFIlmDescription;

    @FXML
    private TextField newFilmTitle;

    @FXML
    private TextField setPrice;

    @FXML
    private TableColumn<Film, String> times;


    @FXML
    private DatePicker toDate;

    @FXML
    void addNewFilm(ActionEvent event) throws IOException {
        addFilm(newFilmTitle.getText(), newFIlmDescription.getText(), fromDate.getValue().toString(), toDate.getValue().toString(), filmTime1.getValue(), filmTime2.getValue(), filmTime3.getValue(), setPrice.getText());

        Film f =new Film(newFilmTitle.getText(), newFIlmDescription.getText(), fromDate.getValue().toString(), toDate.getValue().toString(), new String[]{filmTime1.getValue(), filmTime2.getValue(), filmTime3.getValue()}, Double.parseDouble(setPrice.getText()));
        films.add(f);
        filmsObservableList.add(f);
        filmTable.refresh();
    }

    public static ObservableList<Film> filmsObservableList = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        ObservableList<String> obsList = FXCollections.observableArrayList("13:00", "14:00", "15:00", "16:00", "17:00",
                "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00", "01:00", "02:00", "03:00");
        filmTime1.setItems(obsList);
        filmTime2.setItems(obsList);
        filmTime3.setItems(obsList);
        filmTime1.setValue("13:00");

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

    private void reset() {
        filmsObservableList.removeAll(films);
        filmTable.getItems().clear();
    }

    private void addButtonToTable() {
        TableColumn<Film, Void> colBtn = new TableColumn("Delete");

        Callback<TableColumn<Film, Void>, TableCell<Film, Void>> cellFactory = new Callback<TableColumn<Film, Void>, TableCell<Film, Void>>() {
            @Override
            public TableCell<Film, Void> call(TableColumn<Film, Void> productVoidTableColumn) {
                final TableCell<Film, Void> cell = new TableCell<Film, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Film removedFilm = getTableView().getItems().get(getIndex());
                            getTableView().getItems().remove(removedFilm);
                            System.out.println("removedData: " + removedFilm.toString());
                            films.remove(removedFilm);

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


    public void addFilm(String title, String description, String firstDate, String lastDate, String time1, String time2, String time3, String price) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("films.txt", true));
        writer.write(title + "," + description + "," + firstDate + "," + lastDate + "," + time1 + "," + time2 + "," + time3 + "," + price);
        writer.newLine();
        writer.close();
    }
}
