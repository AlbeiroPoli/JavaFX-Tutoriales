/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carmelo;

import java.io.ByteArrayInputStream;
import java.util.Objects;
import java.util.function.Function;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author Carmelo Marin Abrego
 */
public class CameraTask extends Task<Image> {

    private final VideoCapture capture;
    private final Function<Mat, Mat> imgproc;

    public CameraTask(VideoCapture capture, Function<Mat, Mat> imgproc) {
        this.capture = Objects.requireNonNull(capture);
        this.imgproc = Objects.requireNonNull(imgproc);
    }

    @Override
    protected Image call() throws Exception {

        // omitir si la camara no esta abierta
        if (!capture.isOpened()) {
            return null;
        }

        // obtiene la captura de la camara, lo almacena el frame
        Mat frame = new Mat();
        capture.read(frame);

        // verificar si es una captura valida
        if (!frame.empty()) {
            // procesar y convertir la imagen
            Mat dst = imgproc.apply(frame);
            return createImageFromMat(dst);
        }

        return null;
    }

    // convertir cv::Mat a javafx.scene.image.Image
    private Image createImageFromMat(Mat src) {
        MatOfByte dst = new MatOfByte();
        Imgcodecs.imencode(".bmp", src, dst);
        return new Image(new ByteArrayInputStream(dst.toArray()));
    }

}
