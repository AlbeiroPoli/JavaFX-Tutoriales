/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carmelo;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author Carmelo Marin Abrego
 */
public class JavaFXOpenCV extends Application {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private VideoCapture capture;
    private ImageView imageView;
    private ScheduledService<Image> service;

    @Override
    public void start(Stage primaryStage) {

        capture = new VideoCapture();

        // servicio que ejecutar la captura periodicamente
        service = new ScheduledService<Image>() {
            @Override
            protected Task<Image> createTask() {
                // crear un CameraTask usando el VideoCapture y 
                // el metodo para procesar la imagen idicado JavaFXOpenCV.this::procesarImagen
                return new CameraTask(capture, JavaFXOpenCV.this::procesarImagen);
            }
        };

        // ejecutar el servicio cada 33.3 ms
        service.setPeriod(Duration.millis(33.333333));

        // al finalizar cada ejecucion ejecutar el metodo this::ready
        service.setOnReady(this::ready);

        imageView = new ImageView();
        imageView.setFitHeight(600);
        imageView.setFitWidth(800);

        Button btn = new Button();
        btn.setText("Iniciar Camara");
        btn.setOnAction(this::start);

        VBox root = new VBox(imageView, btn);
        root.setPadding(new Insets(10.0));
        root.setSpacing(10.0);

        Scene scene = new Scene(root);

        primaryStage.setTitle("JavaFX & OpenCV");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setOnCloseRequest(this::stop);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    private Mat procesarImagen(Mat src) {
        Mat dst = new Mat();

        Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(dst, dst, new Size(7, 7), 1.5, 1.5);
        Imgproc.Canny(dst, dst, 0, 30, 3, false);

        return dst;
    }

    private void ready(WorkerStateEvent worker) {
        Image image = (Image) worker.getSource().getValue();
        if (image != null) {
            imageView.setImage(image);
        }
    }

    private void start(ActionEvent e) {
        if (!capture.isOpened() && !service.isRunning()) {
            capture.open(0);
            service.start();
        }
    }

    private void stop(WindowEvent e) {
        service.cancel();
        capture.release();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
