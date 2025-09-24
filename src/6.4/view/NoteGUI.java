package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class NoteGUI extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/note_view.fxml"));
        Parent root = fxmlLoader.load();

        stage.setScene(new Scene(root));
        stage.show();
    }
}

class NoteTest{
    public static void main(String[] args) {
        NoteGUI.launch(NoteGUI.class);
    }
}