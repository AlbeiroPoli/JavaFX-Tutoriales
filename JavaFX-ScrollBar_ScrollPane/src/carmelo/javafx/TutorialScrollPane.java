/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carmelo.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Carmelo Marín Abrego
 */
public class TutorialScrollPane extends Application {

    @Override
    public void start(Stage primaryStage) {

        Image image = new Image(getClass().getResourceAsStream("gears.jpg"));
        ImageView view = new ImageView(image);    

        ScrollPane root = new ScrollPane();
        root.setContent(view);
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        root.setPannable(true);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("JavaFX ScrollPane");
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
