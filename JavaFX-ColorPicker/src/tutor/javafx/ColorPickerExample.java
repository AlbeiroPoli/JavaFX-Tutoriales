package tutor.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ColorPickerExample extends Application {

    @Override
    public void start(Stage primaryStage) {

        ColorPicker color = new ColorPicker(Color.RED);
        color.getStyleClass().add(ColorPicker.STYLE_CLASS_SPLIT_BUTTON);

        StackPane root = new StackPane();
        root.getChildren().add(color);
        root.setAlignment(Pos.TOP_LEFT);
        root.setPadding(new Insets(10.0));

        color.setOnAction(e -> {
            BackgroundFill fill = new BackgroundFill(color.getValue(), null, null);
            root.setBackground(new Background(fill));
        });

        Scene scene = new Scene(root, 600, 250);

        primaryStage.setTitle("JavaFX ColorPicker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
