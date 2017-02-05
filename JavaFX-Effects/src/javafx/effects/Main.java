package javafx.effects;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        GaussianBlur gaussianBlur = new GaussianBlur(3.0);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLUE);
        dropShadow.setInput(gaussianBlur);

        Button btn = new Button("Hello Effects");
        btn.setEffect(dropShadow);

        Text txt = new Text("Text GLOW");
        txt.setEffect(new Reflection());
        txt.setFont(new Font("Arial", 14.0));

        ComboBox<String> cbx = new ComboBox<>();
        cbx.setItems(FXCollections.observableArrayList("Panama", "Chile", "Mexico"));
        cbx.setEffect(new GaussianBlur(4.0));
        cbx.getSelectionModel().selectFirst();

        HBox root = new HBox(btn, txt, cbx);
        root.setPadding(new Insets(10.0));
        root.setSpacing(10.0);

        primaryStage.setTitle("JavaFX Effects");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
