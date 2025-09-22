package view;

import controller.ConverterController;
import controller.DictionaryController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;

public class ConverterGUI extends Application {

    TextField currencyValueFrom = new TextField();
    TextField currencyValueTo = new TextField();
    private ConverterController controller;

    Label warning = new Label();

    String[] currencies = {"USD","GDP","EUR","CNY","JPY"};

    ChoiceBox currencyFrom = new ChoiceBox();
    ChoiceBox currencyTo = new ChoiceBox();

    @Override
    public void start(Stage stage) throws Exception {
        currencyFrom.getItems().addAll(currencies);
        currencyTo.getItems().addAll(currencies);
        Label labelFrom = new Label("From");
        Label labelTo = new Label("To");
        Label title = new Label("Currency Converter");

        currencyFrom.setValue("USD");  // default selected item
        currencyTo.setValue("EUR");    // default selected item


        BorderPane labelFromPane = new BorderPane();
        labelFromPane.setBottom(labelFrom);
        BorderPane.setAlignment(labelFrom, Pos.BOTTOM_RIGHT);

        BorderPane labelToPane = new BorderPane();
        labelToPane.setBottom(labelTo);
        BorderPane.setAlignment(labelTo, Pos.BOTTOM_RIGHT);

        Button button = new Button("Convert");

        Insets insets = new Insets(10,10,10,10);

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

        HBox HBox = new HBox();
        HBox.setSpacing(10);
        HBox.getChildren().add(currencyValueFromVBox);
        HBox.getChildren().add(currencyFromVBox);
        HBox.getChildren().add(buttonVBox);
        HBox.getChildren().add(currencyValueToVBox);
        HBox.getChildren().add(currencyToVBox);

        VBox container = new VBox();
        container.getChildren().add(title);
        container.getChildren().add(HBox);
        container.getChildren().add(warning);
        HBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(container);
        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();

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
        controller = new ConverterController(this);
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
}
class test{
    public static void main(String[] args) {
        ConverterGUI.launch(ConverterGUI.class);
    }
}
