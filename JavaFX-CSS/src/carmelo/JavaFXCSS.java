package carmelo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Carmelo Marin Abrego
 */
public class JavaFXCSS extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label lbl1 = new Label("Este es un label CSS = .label");
        Label lbl2 = new Label("Este es un label CSS = .label");
        Label lbl3 = new Label("Este es un label ID = texto, CSS = #texto");
        lbl3.setId("texto");

        VBox root = new VBox();
        root.getStyleClass().add("vertical-box");
//        root.setStyle("-fx-alignment: CENTER; -fx-background-color: gray;");
        root.getChildren().addAll(lbl1, lbl2, lbl3, new Button("Click"));

        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());

        primaryStage.setTitle("JavaFX - CSS");
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
