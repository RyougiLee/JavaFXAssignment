package view;

import controller.PetController;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class PetGUI extends Application{

    double imgX = 50;
    double imgY = 50;
    double imgWidth = 150;
    double imgHeight = 150;
    Image image = new Image("img/headcrab.png");
    Canvas canvas = new Canvas(500,500);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    double mouseX;
    double mouseY;

    PetController controller;

    @Override
    public void start(Stage stage) throws Exception {

        StackPane root = new StackPane();

        draw();

        root.getChildren().addAll(canvas);
        Scene scene = new Scene(root, 500, 500);

        stage.setScene(scene);
        stage.show();

        canvas.setOnMouseEntered(e -> {

            controller.start();
        });

        canvas.setOnMouseMoved(e -> {
            mouseX = e.getX();
            mouseY = e.getY();
        });

        canvas.setOnMouseExited(e -> {
            controller.stop();
        });


    }

    public void init(){
        controller = new PetController(this);
        controller.move();
    }

    public void draw(){
        gc.clearRect(0, 0, 500, 500);
        gc.drawImage(image, imgX, imgY, imgWidth, imgHeight);
    }

    public synchronized void setImgX(double x){
        imgX = x;
        draw();
    }

    public synchronized void setImgY(double y){
        imgY = y;
        draw();
    }

    public synchronized double getImgX(){
        return imgX;
    }

    public synchronized double getImgY(){
        return imgY;
    }

    public synchronized double getMouseX() {return mouseX;}

    public synchronized double getMouseY() {return mouseY;}


}

class petTest{
    public static void main(String[] args) {
        Application.launch(PetGUI.class);
    }
}
