package sample;

import com.javafx.experiments.importers.Importer3D;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        boolean supported = Platform.isSupported(ConditionalFeature.SCENE3D);

//        if (supported) createScene3D(primaryStage);
        if (supported) loadScene3D(primaryStage);
    }

    private void loadScene3D(Stage stage) throws IOException {

        PointLight light1 = new PointLight();
        light1.setTranslateZ(-500);

        Node model = Importer3D.load(getClass().getResource("obj-model/cyborg.obj").toExternalForm());
        model.setScaleX(150.0);
        model.setScaleY(150.0);
        model.setScaleZ(150.0);

        Group root = new Group(model, light1);

        Scene scene = new Scene(root, 1280, 768, true, SceneAntialiasing.BALANCED);

        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setTranslateX(scene.getWidth() / -2.0);
        camera.setTranslateY(scene.getHeight() / -2.0);

        RotateTransition rt = new RotateTransition(Duration.seconds(10), model);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        rt.setAxis(new Point3D(0, 1, 0));
        rt.play();

        scene.setFill(Color.CORNFLOWERBLUE);
        scene.setCamera(camera);
        stage.setTitle("JavaFX Graficos 3D - Cargar Modelo");
        stage.setScene(scene);
        stage.show();
    }

    private void createScene3D(Stage stage) {

        Color color_luz = Color.WHITESMOKE;

        Image image_diffuse = new Image(getClass().getResource("muro_diffuse.jpg").toExternalForm());
        Image image_normal = new Image(getClass().getResource("muro_normal.jpg").toExternalForm());

        // materiales para el box shape
        PhongMaterial mat = new PhongMaterial();
        mat.setSpecularColor(color_luz);
        mat.setSpecularPower(64);
        mat.setDiffuseMap(image_diffuse);
        mat.setBumpMap(image_normal);

        // crear un cubo 3D, anchura, altura y profundidad
        Box box = new Box(300, 300, 300);
        box.setRotate(45);
        box.setRotationAxis(new Point3D(1, 1, 0));
        box.setMaterial(mat);

        // crear una luz puntual
        PointLight light = new PointLight(color_luz);
        light.setTranslateX(-350);
        light.setTranslateY(+180);
        light.setTranslateZ(-500);

        Group root = new Group(box, light);

        // crear la escena, true para activar el buffer de profindidad
        Scene scene = new Scene(root, 1280, 768, true, SceneAntialiasing.BALANCED);

        // crear una camara en perspectiva
        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setTranslateX(scene.getWidth() / -2.0);
        camera.setTranslateY(scene.getHeight() / -2.0);

        // crear una animacion de rotacion sobre los ejes (X,Y,Z)
        RotateTransition rt = new RotateTransition(Duration.seconds(10), box);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        rt.setAxis(new Point3D(1, 1, 1));
        rt.play();

        scene.setCamera(camera);
        stage.setTitle("JavaFX Graficos 3D");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
