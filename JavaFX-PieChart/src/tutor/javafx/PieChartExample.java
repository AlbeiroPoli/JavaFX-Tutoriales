package tutor.javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PieChartExample extends Application {

    @Override
    public void start(Stage primaryStage) {

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data.add(new PieChart.Data("OpenCV", 20));
        data.add(new PieChart.Data("JavaFX", 40));
        data.add(new PieChart.Data("Python", 10));
        data.add(new PieChart.Data("Spring", 15));
        data.add(new PieChart.Data("Qt", 10));
        data.add(new PieChart.Data("SQL", 17));

        PieChart pie = new PieChart(data);
        pie.setTitle("PieChart Tutorial 2017");
        pie.setLegendSide(Side.LEFT);
        pie.setTitleSide(Side.BOTTOM);
        pie.setLabelLineLength(60);
        pie.setLabelsVisible(true);

        pie.getData().forEach(this::installTooltip);

        StackPane root = new StackPane(pie);
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setTitle("JavaFX PieChart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void installTooltip(PieChart.Data d) {

        String msg = String.format("%s : %s", d.getName(), d.getPieValue());

        Tooltip tt = new Tooltip(msg);
        tt.setStyle("-fx-background-color: gray; -fx-text-fill: whitesmoke;");
        
        Tooltip.install(d.getNode(), tt);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
