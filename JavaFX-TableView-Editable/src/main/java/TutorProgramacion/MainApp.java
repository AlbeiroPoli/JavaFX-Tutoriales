package TutorProgramacion;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class MainApp extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        TableView<Persona> tv = new TableView();
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tv.setEditable(true);

        TableColumn<Persona, String> tc_nombre = new TableColumn<>("Nombre");
        tc_nombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("Nombre"));
        tc_nombre.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_nombre.setOnEditCommit(data -> {
            System.out.println("Nuevo Nombre: " +  data.getNewValue());
            System.out.println("Antiguo Nombre: " + data.getOldValue());

            Persona p = data.getRowValue();
            p.setNombre(data.getNewValue());

            System.out.println(p);
        });


        TableColumn<Persona, LocalDate> tc_nacimiento = new TableColumn<>("Fecha de Nacimiento");
        tc_nacimiento.setCellValueFactory(new PropertyValueFactory<Persona, LocalDate>("nacimiento"));
        LocalDateStringConverter converter = new LocalDateStringConverter();
        tc_nacimiento.setCellFactory(TextFieldTableCell.<Persona, LocalDate>forTableColumn(converter));
        tc_nacimiento.setOnEditCommit(data -> { data.getRowValue().setNacimiento(data.getNewValue()); });


        TableColumn<Persona, Persona.Genero> tc_genero = new TableColumn<>("Genero");
        tc_genero.setCellValueFactory(new PropertyValueFactory<Persona, Persona.Genero>("Genero"));
        tc_genero.setCellFactory(ChoiceBoxTableCell
                .forTableColumn(Persona.Genero.MASCULINO, Persona.Genero.FEMENINO));
        tc_genero.setOnEditCommit(data -> {
            System.out.println(data.getRowValue());
        });


        TableColumn<Persona, Boolean> tc_activo = new TableColumn<>("Activo");
        tc_activo.setCellValueFactory(cell -> cell.getValue().activoProperty());
        tc_activo.setCellFactory(CheckBoxTableCell.forTableColumn(tc_activo));


        tv.getColumns().addAll(tc_nombre, tc_nacimiento, tc_genero, tc_activo);
        tv.getItems().addAll(Persona.getPersonList());

        StackPane root = new StackPane(tv);
        root.setPadding(new Insets(10.0));
        Scene scene = new Scene(root, 1280, 720);

        stage.setTitle("Edicion en TableView");
        stage.setScene(scene);
        stage.show();
    }
}
