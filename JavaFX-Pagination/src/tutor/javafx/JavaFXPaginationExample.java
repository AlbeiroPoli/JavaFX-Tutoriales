package tutor.javafx;

import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class JavaFXPaginationExample extends Application {

    private List<File> imageList;
    private Pagination pagination;

    @Override
    public void start(Stage primaryStage) {

        pagination = new Pagination();
        pagination.setPageFactory(index -> {
            if (imageList != null && index < imageList.size()) {

                String url = imageList.get(index).toURI().toString();
                
                ImageView imageView = new ImageView(url);
                imageView.setPreserveRatio(true);
                imageView.setFitHeight(400);
                imageView.setFitWidth(600);

                return imageView;

            } else {
                return new Label("No hay imagen seleccionada.");
            }
        });

        VBox.setVgrow(pagination, Priority.ALWAYS);

        VBox root = new VBox(createTopBar(primaryStage), pagination);
        root.setSpacing(10.0);
        root.setPadding(new Insets(10.0));

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("JavaFX Pagination");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Node createTopBar(Stage stage) {

        HBox box = new HBox();
        box.setSpacing(10.0);

        TextField tf = new TextField();
        tf.setPromptText("buscar carpeta de imagenes");
        tf.setEditable(false);
        tf.setFocusTraversable(false);

        HBox.setHgrow(tf, Priority.ALWAYS);

        FileChooser fileDialog = new FileChooser();
        fileDialog.getExtensionFilters()
                .add(new ExtensionFilter("Imagen", "*.jpg", "*.png", "*.bmp", "*.gif"));

        Button btn = new Button("...");
        btn.setOnAction(e -> {
            fileDialog.setTitle("Abrir imagenes");
            imageList = fileDialog.showOpenMultipleDialog(stage);
            if(imageList != null) {
                pagination.setPageCount(imageList.size());
                tf.setText(imageList.get(0).getParent());
            }
        });

        box.getChildren().add(tf);
        box.getChildren().add(btn);

        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
