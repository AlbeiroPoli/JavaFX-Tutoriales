/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carmelo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Carmelo Marin Abrego
 */
public class SearchComboBoxApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        root.getChildren().add(createSearchComboBox());

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("JavaFX - SearchComboBox");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Node createSearchComboBox() {

        ObservableList<String> items = FXCollections
                .observableArrayList(
                        "auto", 
                        "casa",
                        "perro",
                        "animales",
                        "oro",
                        "minerales", 
                        "teclado",
                        "computadora",
                        "restaurante",
                        "comida",
                        "papas",
                        "salud",
                        "bellesa",
                        "cristales",
                        "escuela",
                        "saber",
                        "periodico");
        
        SearchComboBox<String> cbx = new SearchComboBox<>();
        cbx.setItems(items);
        cbx.setFilter((item, text) -> item.contains(text));
        cbx.getSelectionModel().selectedItemProperty().addListener((p, o, n) -> System.out.println("ComboBox Item: " + n));
        cbx.setPrefWidth(250.0);
        cbx.getSelectionModel().select(5);

        Button btn = new Button("Select index 10");
        btn.setOnAction(a -> cbx.getSelectionModel().clearAndSelect(10));
        
        VBox box = new VBox(10.0);
        box.getChildren().addAll(cbx, btn);
        box.setAlignment(Pos.CENTER);
        
        return box;
    }
}
