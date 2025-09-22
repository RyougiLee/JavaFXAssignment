package view;

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
    Canvas canvas = new Canvas(1000,1000);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    @Override
    public void start(Stage stage) throws Exception {

        StackPane root = new StackPane();

        draw();

        root.getChildren().addAll(canvas);
        Scene scene = new Scene(root, 1000, 1000);

        stage.setScene(scene);
        stage.show();

        canvas.setOnMouseMoved(e -> {
            double mouseX = e.getX();
            double mouseY = e.getY();

            if(mouseX >= imgX && mouseX<= imgX + imgWidth && mouseY >= imgY && mouseY <= imgY + imgHeight){

            }

        });

    }

    public void draw(){
        gc.clearRect(0, 0, 1000, 1000);
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


}

class petTest{
    public static void main(String[] args) {
        Application.launch(PetGUI.class);
    }
}
