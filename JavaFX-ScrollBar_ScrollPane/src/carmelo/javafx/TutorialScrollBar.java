package carmelo.javafx;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TutorialScrollBar extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        Image image = new Image(getClass().getResourceAsStream("gears.jpg"));

        Canvas canvas = new Canvas(800, 600);
        
        double width = canvas.getWidth();
        double height = canvas.getHeight();
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, 0, 0, width, height, 0, 0, width, height);

        ScrollBar vertical = new ScrollBar();
        vertical.setOrientation(Orientation.VERTICAL);
        vertical.setMin(0);
        vertical.setMax(image.getHeight());
        vertical.setVisibleAmount(height);
        vertical.setMaxHeight(height);

        ScrollBar horizontal = new ScrollBar();
        horizontal.setOrientation(Orientation.HORIZONTAL);
        horizontal.setMin(0);
        horizontal.setMax(image.getWidth());
        horizontal.setVisibleAmount(width);
        horizontal.setMaxWidth(width);

        vertical.valueProperty().addListener((property, old, value) -> {
            double dy = vertical.getValue();
            double dx = horizontal.getValue();

            gc.drawImage(image, dx, dy, width, height, 0, 0, width, height);
        });

        horizontal.valueProperty().addListener((property, old, value) -> {
            double dy = vertical.getValue();
            double dx = horizontal.getValue();

            gc.drawImage(image, dx, dy, width, height, 0, 0, width, height);
        });
        
        HBox root = new HBox(new VBox(canvas, horizontal), vertical);

        Scene scene = new Scene(root);

        primaryStage.setTitle("JavaFX ScrollBar");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
