package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import main.models.Customer;

import static main.Main.customers;
import static main.Main.getUsers;

public class AdminHomePageController extends Navigate {

    @FXML
    private TableColumn<Customer, String> email;


    @FXML
    private TableColumn<Customer, String> name;

    @FXML
    private TableColumn<Customer, String> password;

    @FXML
    private TableView<Customer> usersTable;

    public static ObservableList<Customer> customersObservableList = FXCollections.observableArrayList();



    @FXML
    private void initialize() {

        reset();
        customersObservableList.addAll(customers);
        addButtonToTable();
        name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));

        password.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));

        usersTable.setItems(customersObservableList);


    }
    private void reset() {
        customersObservableList.removeAll(customers);
        usersTable.getItems().clear();
    }

    private void addButtonToTable() {
        TableColumn<Customer, Void> colBtn = new TableColumn("Delete");

        Callback<TableColumn<Customer, Void>, TableCell<Customer, Void>> cellFactory = new Callback<TableColumn<Customer, Void>, TableCell<Customer, Void>>() {
            @Override
            public TableCell<Customer, Void> call(TableColumn<Customer, Void> productVoidTableColumn) {
                final TableCell<Customer, Void> cell = new TableCell<Customer, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Customer removedClient = getTableView().getItems().get(getIndex());
                            getTableView().getItems().remove(removedClient);
                            System.out.println("removedData: " + removedClient.toString());
                            customers.remove(removedClient);

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
        usersTable.getColumns().add(colBtn);
    }
}
