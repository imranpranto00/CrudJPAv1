package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

@Component
public class MainController {

    private final PersonService personService;

    public MainController(PersonService personService) {
        this.personService = personService;
    }

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, Long> colId;

    @FXML
    private TableColumn<Person, String> colName;

    @FXML
    private TableColumn<Person, String> colEmail;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    private ObservableList<Person> personList;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        personList = FXCollections.observableArrayList(personService.findAll());
        personTable.setItems(personList);

        addButton.setOnAction(event -> addPerson());
        updateButton.setOnAction(event -> updatePerson());
        deleteButton.setOnAction(event -> deletePerson());
    }

    private void addPerson() {
        String name = nameField.getText();
        String email = emailField.getText();
        if (!name.isEmpty() && !email.isEmpty()) {
            Person person = new Person();
            person.setName(name);
            person.setEmail(email);
            Person savedPerson = personService.save(person);
            personList.add(savedPerson);
        }
    }

    private void updatePerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            selectedPerson.setName(nameField.getText());
            selectedPerson.setEmail(emailField.getText());
            personService.save(selectedPerson);
            personTable.refresh();
        }
    }

    private void deletePerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            personService.deleteById(selectedPerson.getId());
            personList.remove(selectedPerson);
        }
    }
}
