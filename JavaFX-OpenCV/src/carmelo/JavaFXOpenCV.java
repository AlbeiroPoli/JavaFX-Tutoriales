/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carmelo;

import java.io.ByteArrayInputStream;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

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
    private AnimationTimer timer;

    @Override
    public void start(Stage primaryStage) {

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (capture.isOpened()) {
                    Mat frame = new Mat();
                    capture.read(frame);
                    if (!frame.empty()) {
                        
                        Mat dst = new Mat();
                        
                        Imgproc.cvtColor(frame, dst, Imgproc.COLOR_BGR2GRAY);
                        Imgproc.GaussianBlur(dst, dst, new Size(7, 7), 1.5, 1.5);
                        Imgproc.Canny(dst, dst, 0, 30, 3, false);
                        
                        imageView.setImage(createImageFromMat(dst));
                    }
                }
            }
        };

        imageView = new ImageView();
        imageView.setFitHeight(320);
        imageView.setFitWidth(460);

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

        primaryStage.show();
    }

    private void start(ActionEvent e) {
        if (capture == null) {
            capture = new VideoCapture();
            capture.set(Videoio.CAP_PROP_FRAME_WIDTH, 460);
            capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, 320);
        }

        capture.open(0);
        timer.start();
    }

    private void stop(WindowEvent e) {
        timer.stop();
        capture.release();
    }

    private Image createImageFromMat(Mat src) {
        MatOfByte dst = new MatOfByte();
        Imgcodecs.imencode(".bmp", src, dst);
        return new Image(new ByteArrayInputStream(dst.toArray()));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
