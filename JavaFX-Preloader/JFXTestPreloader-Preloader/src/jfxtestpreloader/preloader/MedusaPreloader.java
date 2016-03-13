package jfxtestpreloader.preloader;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import javafx.application.Preloader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Carmelo Marin Abrego
 */
public class MedusaPreloader extends Preloader {

    private Stage stage;
    private Gauge gauge;

    private Scene createMedusaPreloaderScene() {
        gauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.LEVEL)
                .title("Cargando...")
                .titleColor(Color.WHITE)
                .animated(true)
                .gradientBarEnabled(true)
                .gradientBarStops(new Stop(0.0, Color.RED),
                        new Stop(0.25, Color.ORANGE),
                        new Stop(0.5, Color.YELLOW),
                        new Stop(0.75, Color.YELLOWGREEN),
                        new Stop(1.0, Color.LIME))
                .build();

        StackPane pane = new StackPane(gauge);
        pane.setPadding(new Insets(75));
        
        Label footer = new Label("Blog: Tutor de Programación");
        footer.setStyle("-fx-font-size: 0.95em; -fx-text-fill: whitesmoke; -fx-font-style: oblique;");
        footer.setPadding(new Insets(10.0));
        
        BorderPane root = new BorderPane(pane); 
        root.setBackground(new Background(new BackgroundFill(Color.rgb(90, 90, 90), CornerRadii.EMPTY, Insets.EMPTY)));
        root.setBottom(footer);

        return new Scene(root, 480, 320, Color.TRANSPARENT);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(createMedusaPreloaderScene());
        stage.show();
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification pn) {
        if (pn instanceof ProgressNotification) {
            double value = ((ProgressNotification) pn).getProgress();
            gauge.setValue(value);
        } else if (pn instanceof StateChangeNotification) {
            stage.hide();
        }
    }
}
