# OpenCV & JavaFX
Interacción de una aplicación *JavaFX* con la librería *OpenCV*, explica como convertir un objeto *org.opencv.core.Mat* a *javafx.scene.image.Image* para poder mostrarlo en un control ImageView.

Además creamos un ScheduledService<Image> para obtener priodicamente en segundo plano una captura de la cámara, esto nos permite mostrar una secuencia fluida de video.  

Este proyecto no incluye la librería OpenCV, por lo que debemos descárgala y copiar la carpeta **/opencv/build/java** dentro de la carpeta de proyecto **/JavaFX-OpenCV/lib**.

Puedes encontrar más información y otros tutoriales en: [Tutoriales JavaFX](http://acodigo.blogspot.com/p/tutorial-opencv.html)   