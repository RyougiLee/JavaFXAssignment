package view;

import application.ConverterApp;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ConverterGUIDB extends Application {

    TextField currencyValueFrom = new TextField();
    TextField currencyValueTo = new TextField();
    private ConverterApp controller;

    Label warning = new Label();

    ArrayList <String> currencies = new ArrayList<>();

    ChoiceBox currencyFrom = new ChoiceBox();
    ChoiceBox currencyTo = new ChoiceBox();

    @Override
    public void start(Stage stage) throws Exception {
        Label labelFrom = new Label("From");
        Label labelTo = new Label("To");
        Label title = new Label("Currency Converter");

        currencyFrom.setValue("USD");  // default selected item
        currencyTo.setValue("EUR");    // default selected item

        BorderPane titlePane = new BorderPane();
        titlePane.setBottom(title);
        BorderPane.setAlignment(title,Pos.CENTER);

        BorderPane labelFromPane = new BorderPane();
        labelFromPane.setBottom(labelFrom);
        BorderPane.setAlignment(labelFrom, Pos.BOTTOM_RIGHT);

        BorderPane labelToPane = new BorderPane();
        labelToPane.setBottom(labelTo);
        BorderPane.setAlignment(labelTo, Pos.BOTTOM_RIGHT);

        Button button = new Button("Convert");

        VBox currencyValueFromVBox = new VBox();
        currencyValueFromVBox.getChildren().add(new Label(""));
        currencyValueFromVBox.getChildren().add(currencyValueFrom);

        VBox currencyFromVBox = new VBox();
        currencyFromVBox.getChildren().add(labelFromPane);
        currencyFromVBox.getChildren().add(currencyFrom);

        VBox buttonVBox = new VBox();
        buttonVBox.getChildren().add(new Label(""));
        buttonVBox.getChildren().add(button);

        VBox currencyValueToVBox = new VBox();
        currencyValueToVBox.getChildren().add(new Label(""));
        currencyValueToVBox.getChildren().add(currencyValueTo);

        VBox currencyToVBox = new VBox();
        currencyToVBox.getChildren().add(labelToPane);
        currencyToVBox.getChildren().add(currencyTo);

        HBox Hbox = new HBox();
        Hbox.setSpacing(10);
        Hbox.getChildren().add(currencyValueFromVBox);
        Hbox.getChildren().add(currencyFromVBox);
        Hbox.getChildren().add(buttonVBox);
        Hbox.getChildren().add(currencyValueToVBox);
        Hbox.getChildren().add(currencyToVBox);

        Button addButton = new Button("Add currency");
        BorderPane addButtonPane = new BorderPane();
        addButtonPane.setCenter(addButton);

        VBox container = new VBox();
        container.getChildren().add(titlePane);
        container.getChildren().add(Hbox);
        container.getChildren().add(warning);
        container.getChildren().add(addButtonPane);
        Hbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(container);
        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage newStage = new Stage();

                HBox nameBox = new HBox();
                nameBox.getChildren().add(new Label("Currency Name: "));
                TextField nameValue = new TextField();
                nameBox.getChildren().add(nameValue);

                HBox abbrBox = new HBox();
                abbrBox.getChildren().add(new Label("Abbreviation Name: "));
                TextField abbrValue = new TextField();
                abbrBox.getChildren().add(abbrValue);

                HBox rateBox = new HBox();
                rateBox.getChildren().add(new Label("Rate to USD: "));
                TextField rateValue = new TextField();
                rateBox.getChildren().add(rateValue);

                BorderPane currencyButtonPane = new BorderPane();
                Button currencyButton = new Button("Add currency");
                currencyButtonPane.setCenter(currencyButton);

                Label warning = new Label();

                VBox container = new VBox();
                container.getChildren().addAll(nameBox,abbrBox,rateBox,currencyButtonPane,warning);

                Scene scene = new Scene(container);
                newStage.setScene(scene);

                currencyButton.setOnAction(e -> {
                    try{
                        String name = nameValue.getText();
                        String abbr_name = abbrValue.getText();
                        double rate_to_usd = Double.parseDouble(rateValue.getText());
                        controller.addNewCurrency(name,abbr_name,rate_to_usd);
                        warning.setText("");
                        newStage.hide();
                    }
                    catch (Exception ex){
                        warning.setText("Invalid input value");
                    }
                });

                newStage.showAndWait();
                controller.update();
            }
        });

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /*
                if(!currencyValueFrom.getText().equals("")){
                controller.Calculate();
                warning.setText("");
                }
                else{
                warning.setText("Value cannot be empty");}
                 */
                try{
                    double value = Double.parseDouble(currencyValueFrom.getText());
                    controller.Calculate();
                    warning.setText("");
                }
                catch (Exception e){
                    warning.setText("Invalid input value");
                }
            }
        });
    }

    @Override
    public void init() throws Exception {
        controller = new ConverterApp(this);
    }

    public String getCurrencyFrom(){
        return currencyFrom.getValue().toString();
    }

    public String getCurrencyTo(){
        return currencyTo.getValue().toString();
    }

    public double getCurrencyValueFrom(){
        return Double.parseDouble(currencyValueFrom.getText());
    }

    public void setCurrencyValueTo(Double value){
        currencyValueTo.setText(String.valueOf(value));
    }

    public void setWarning(String text){
        warning.setText(text);
    }

    public void addChoiceBox(String name){
        currencies.add(name);
        currencyFrom.getItems().add(name);
        currencyTo.getItems().add(name);
    }

    public void resetChoiceBox(){
        currencies.clear();
        currencyFrom.getItems().clear();
        currencyTo.getItems().clear();
    }
}
class ConverterTest{
    public static void main(String[] args) {
        ConverterGUIDB.launch(ConverterGUIDB.class);
    }
}
