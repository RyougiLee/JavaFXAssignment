package view;
import controller.DictionaryController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Spinner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.application.Application;

public class DictionaryView extends Application {

    private DictionaryController controller;
    TextField searchBar = new TextField();
    Label result = new Label();

    public void start(Stage stage){
        FlowPane pane = new FlowPane();
        Button searchButton = new Button("Search");

        stage.setTitle("Dictionary");

        Insets insets = new Insets(10,10,10,10);
        pane.setMargin(result,insets);
        pane.setMargin(searchButton,insets);
        pane.setMargin(searchBar,insets);

        pane.getChildren().add(searchBar);
        pane.getChildren().add(searchButton);
        pane.getChildren().add(result);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

        searchButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                controller.search();
            }
        });
    }

    public void init(){
        controller = new DictionaryController(this);
    }

    public String getSearchContent(){
        return searchBar.getText();
    }

    public void setResult(String value){
        result.setText(value);
    }
}
