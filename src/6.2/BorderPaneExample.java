import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class BorderPaneExample extends Application {

    @Override
    public void start(Stage window){
        BorderPane layout = new BorderPane();

        Button topButton = new Button("NORTTH");
        Button rightButton = new Button("EAST");
        Button bottomButton = new Button("WEST");
        Button leftButton = new Button ("LEFT");
        Button centerButton = new Button("CENTER");

        layout.setTop(topButton);
        layout.setRight(rightButton);
        layout.setBottom(bottomButton);
        layout.setLeft(leftButton);
        layout.setCenter(centerButton);

        Scene view = new Scene(layout);

        window.setScene(view);
        window.show();
    }

}
class Test{
    public static void main(String[] args) {
        BorderPaneExample.launch(BorderPaneExample.class);
    }
}
