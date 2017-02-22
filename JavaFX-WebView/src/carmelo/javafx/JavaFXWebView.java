/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carmelo.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author Carmelo Marín Abrego
 */
public class JavaFXWebView extends Application {

    public Node createTopBar(WebEngine webEngine) {
        HBox root = new HBox();
        root.setSpacing(5.0);
        root.setAlignment(Pos.CENTER);

        Label urlLabel = new Label("URL");

        TextField urlText = new TextField("http://www.google.com");
        HBox.setHgrow(urlText, Priority.ALWAYS);

        Button btnIr = new Button("Ir");
        Button btnNext = new Button("Siguiente");
        Button btnPrev = new Button("Anterior");

        btnPrev.setDisable(true);
        btnNext.setDisable(true);

        WebHistory history = webEngine.getHistory();

        history.currentIndexProperty().addListener((p, oldValue, newValue) -> {
            int currentIndex = newValue.intValue();

            if (currentIndex <= 0) {
                btnPrev.setDisable(true);
            } else {
                btnPrev.setDisable(false);
            }

            if (currentIndex >= history.getEntries().size() - 1) {
                btnNext.setDisable(true);
            } else {
                btnNext.setDisable(false);
            }
        });

        btnIr.setOnAction(a -> webEngine.load(urlText.getText()));

        btnNext.setOnAction(a -> history.go(+1));
        btnPrev.setOnAction(a -> history.go(-1));

        root.getChildren().addAll(
                urlLabel, urlText,
                btnIr, btnPrev, btnNext
        );

        return root;
    }

    @Override
    public void start(Stage primaryStage) {

        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setMaxWidth(Double.MAX_VALUE);

        WebView webView = new WebView();

        WebEngine webEngine = webView.getEngine();

        Node topBar = createTopBar(webEngine);

        // cambiar el titulo al cargar una nueva pagina web
        webEngine.titleProperty()
                .addListener((p, o, t) -> primaryStage.setTitle(t));

        // mostrar el progreso de cargar de la web
        webEngine.getLoadWorker().progressProperty()
                .addListener((p, o, v) -> progressBar.setProgress(v.doubleValue()));

        VBox root = new VBox();
        root.getChildren().addAll(topBar, webView, progressBar);
        root.setPadding(new Insets(5.0));
        root.setSpacing(10.0);

        Scene scene = new Scene(root, 800, 420);

        primaryStage.setTitle("JavaFX Navegador Web");
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
