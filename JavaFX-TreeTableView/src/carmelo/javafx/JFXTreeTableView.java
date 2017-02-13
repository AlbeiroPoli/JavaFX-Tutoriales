/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carmelo.javafx;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Carmelo Marín Abrego
 */
public class JFXTreeTableView extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        TreeTableView ttv = new TreeTableView();
        
        TreeTableColumn<Empleado, String> ttcNombre = new TreeTableColumn<>("Nombre");
        TreeTableColumn<Empleado, String> ttcApellido = new TreeTableColumn<>("Apellido");
        TreeTableColumn<Empleado, LocalDate> ttcNacimiento = new TreeTableColumn<>("Fecha de Nacimiento");
        TreeTableColumn<Empleado, Double> ttcSalario = new TreeTableColumn<>("Salario Bruto");
        
        ttcNombre.setCellValueFactory(new TreeItemPropertyValueFactory<>("nombre"));
        ttcApellido.setCellValueFactory(new TreeItemPropertyValueFactory<>("apellido"));
        ttcNacimiento.setCellValueFactory(new TreeItemPropertyValueFactory<>("nacimiento"));
        ttcSalario.setCellValueFactory(new TreeItemPropertyValueFactory<>("salario"));
        
        ttv.getColumns().addAll(ttcNombre, ttcApellido, ttcNacimiento, ttcSalario);
        
        addData(ttv);
        
        StackPane root = new StackPane();
        root.getChildren().add(ttv);
        root.setPadding(new Insets(10.0));
        
        Scene scene = new Scene(root, 480, 320);
        
        primaryStage.setTitle("JavaFX :: TreeTableView");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void addData(TreeTableView ttv)
    {
        Empleado emp1 = new Empleado("Juan", "Perez", LocalDate.parse("2000-05-01"), 500.0);
        Empleado emp11 = new Empleado("Maria", "Lopez", LocalDate.parse("2010-05-01"), 400.0);
        Empleado emp12 = new Empleado("Ivania", "Gonzalez", LocalDate.parse("2010-08-07"), 300.0);
        
        TreeItem<Empleado> itm1 = new TreeItem<>(emp1);
        TreeItem<Empleado> itm11 = new TreeItem<>(emp11);
        TreeItem<Empleado> itm12 = new TreeItem<>(emp12);
        
        itm1.getChildren().addAll(itm11, itm12);
        
        ttv.setRoot(itm1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
