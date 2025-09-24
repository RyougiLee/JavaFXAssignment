package controller;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import view.BMI;
import javafx.fxml.FXML;

import java.util.Locale;

public class NoteController {

    @FXML
    private BorderPane inputContainer;

    @FXML
    private ListView notebookContainer;

    @FXML
    private Button addButton;

    @FXML
    private TextField inputTitle;

    @FXML
    private TextArea inputContent;

    @FXML
    private void addNote(){
        Label
        container.getChildren().addAll(inputContainer.getChildren());
        notebookContainer.getChildren().addAll(container);
    }
}
