package javafx.enlaces;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXEnlaces extends Application {

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(createAreaBind());

        primaryStage.setTitle("JavaFX Boolean Binding");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent createConvertBind() {

        Label lbl = new Label("Conversor de longitud, en Pies:");
        lbl.setStyle("-fx-font-size: 18;");

        Slider sliderPies = new Slider(0, 100, 0);
        sliderPies.setShowTickMarks(true);
        sliderPies.setShowTickLabels(true);

        NumberBinding pulgadas = sliderPies.valueProperty().multiply(12);
        NumberBinding metros = sliderPies.valueProperty().multiply(.3048);

        TextField txtPulgadas = new TextField();
        txtPulgadas.textProperty().bind(pulgadas.asString("En pulgadas: %.2f"));

        TextField txtMetros = new TextField();
        txtMetros.textProperty().bind(metros.asString("En metros: %.2f"));

        VBox root = new VBox(lbl, sliderPies, txtMetros, txtPulgadas);
        root.setPadding(new Insets(10.0));
        root.setSpacing(10.0);

        return root;
    }

    private Parent createBooleanBind() {

        Label lblNombre = new Label("Nombre");
        Label lblContrasena = new Label("Contrasena");

        TextField tfNombre = new TextField();
        PasswordField tfContrasena = new PasswordField();

        Button btnEntrar = new Button("Entrar");
        btnEntrar.setMaxWidth(Double.MAX_VALUE);

        btnEntrar.disableProperty().bind(
                tfNombre.textProperty().isEmpty().or(
                        tfContrasena.textProperty().isEmpty()));

        GridPane.setColumnSpan(btnEntrar, 2);

        GridPane root = new GridPane();
        root.setPadding(new Insets(10.0));
        root.add(lblNombre, 0, 0);
        root.add(lblContrasena, 0, 1);
        root.add(tfNombre, 1, 0);
        root.add(tfContrasena, 1, 1);
        root.add(btnEntrar, 0, 2);
        root.setHgap(10.0);
        root.setVgap(5.0);

        ColumnConstraints column1 = new ColumnConstraints();

        ColumnConstraints column2 = new ColumnConstraints(350, 100, Double.MAX_VALUE);
        column2.setHgrow(Priority.ALWAYS);

        root.getColumnConstraints().addAll(column1, column2);

        return root;
    }

    private Parent createAreaBind() {

        Spinner<Double> sbBase = new Spinner(new DoubleSpinnerValueFactory(0, 100, 50.5));
        Spinner<Double> sbAltura = new Spinner(new DoubleSpinnerValueFactory(0, 100, 20.5));

        DoubleBinding base = Bindings.selectDouble(sbBase, "valueFactory", "value");
        DoubleBinding altura = Bindings.selectDouble(sbAltura, "valueFactory", "value");

        NumberBinding area = Bindings.multiply(base, altura);

        TextField txt = new TextField();
        txt.textProperty().bind(area.asString("Area del rectangulo: %.2f"));

        TextField txt0 = new TextField();
        txt0.textProperty().bind(area.divide(2.0).asString("Area del triangulo: %.2f"));

        VBox root = new VBox(sbBase, sbAltura, txt, txt0);
        root.setPadding(new Insets(10.0));
        root.setSpacing(10.0);

        return root;
    }

    private Parent createCounterBind() {

        ListView<Integer> lv = new ListView<>();
        lv.getItems().addAll(1, 2, 3, 4, 5);

        String txt = "Elemento seleccionado: %d";

        Label lbl = new Label();
        lbl.textProperty().bind(lv.getSelectionModel()
                .selectedItemProperty().asString(txt));

        VBox root = new VBox(lbl, lv);
        root.setPadding(new Insets(10.0));
        root.setSpacing(10.0);

        return root;
    }

    private Parent createSimpleBidirectionalBind() {

        TextField tf_1 = new TextField();
        TextField tf_2 = new TextField();

        tf_1.textProperty().bindBidirectional(tf_2.textProperty());

        VBox root = new VBox(tf_1, tf_2);
        root.setPadding(new Insets(10.0));
        root.setSpacing(10.0);

        return root;
    }

    private VBox createSimpleBind() {
        Slider slider = new Slider(0, 1, 0);

        ProgressBar bar = new ProgressBar(0);
        bar.setMaxWidth(Double.MAX_VALUE);
        bar.progressProperty().bind(slider.valueProperty());

        bar.progressProperty().unbind();
        bar.progressProperty().set(0.5);

        VBox root = new VBox(slider, bar);
        root.setPadding(new Insets(10.0));
        root.setSpacing(10.0);

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
