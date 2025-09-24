package controller;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import view.NoteGUI;
import javafx.fxml.FXML;
import model.Note;
import model.Notebook;

import java.util.Locale;

public class NoteController {

    Notebook notebook = new Notebook();

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
        String title = inputTitle.getText();
        String content = inputContent.getText();

        notebook.add(new Note(title,content));

        notebookContainer.getItems().setAll(notebook.get());
    }
}
