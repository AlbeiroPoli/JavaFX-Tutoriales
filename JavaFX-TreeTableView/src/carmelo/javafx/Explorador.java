/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carmelo.javafx;

import de.jensd.fx.glyphs.octicons.OctIcon;
import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class Explorador extends Application {

    // This method creates a TreeItem to represent the given File. It does this
    // by overriding the TreeItem.getChildren() and TreeItem.isLeaf() methods
    // anonymously, but this could be better abstracted by creating a
    // 'FileTreeItem' subclass of TreeItem. However, this is left as an exercise
    // for the reader.
    private TreeItem<File> createNode(final File f) {
        return new TreeItem<File>(f) {
            // We cache whether the File is a leaf or not. A File is a leaf if
            // it is not a directory and does not have any files contained within
            // it. We cache this as isLeaf() is called often, and doing the
            // actual check on File is expensive.
            private boolean isLeaf;

            // We do the children and leaf testing only once, and then set these
            // booleans to false so that we do not check again during this
            // run. A more complete implementation may need to handle more
            // dynamic file system situations (such as where a folder has files
            // added after the TreeView is shown). Again, this is left as an
            // exercise for the reader.
            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;

            @Override
            public ObservableList<TreeItem<File>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;

                    // First getChildren() call, so we actually go off and
                    // determine the children of the File contained in this TreeItem.
                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }

            @Override
            public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    File f = (File) getValue();
                    isLeaf = f.isFile();
                }

                return isLeaf;
            }

            private ObservableList<TreeItem<File>> buildChildren(TreeItem<File> TreeItem) {
                File f = TreeItem.getValue();

                if (f != null && f.isDirectory()) {
                    File[] files = f.listFiles();
                    if (files != null) {
                        ObservableList<TreeItem<File>> children = FXCollections.observableArrayList();

                        for (File childFile : files) {
                            // no mostrar archivos ocultos
                            if (!childFile.isHidden()) {
                                children.add(createNode(childFile));
                            }
                        }

                        return children;
                    }
                }

                return FXCollections.emptyObservableList();
            }

        };
    }

    class FileOrDir {

        private String name;
        private Boolean isFile;

        public FileOrDir(String name, Boolean isFile) {
            this.name = name;
            this.isFile = isFile;
        }
    }

    private static final DecimalFormat format = new DecimalFormat("#.##");
    private static final long MiB = 1024 * 1024;
    private static final long KiB = 1024;

    public String getFileSize(File file) {

        // si es una carpeta 
        if (!file.isFile()) {
            return "";
        }
        
        final double length = file.length();

        if (length > MiB) {
            return format.format(length / MiB) + " MiB";
        }
        if (length > KiB) {
            return format.format(length / KiB) + " KiB";
        }

        return format.format(length) + " B";
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TreeItem<File> archivos = new TreeItem<>();

        TreeTableColumn<File, FileOrDir> ttcNombre = new TreeTableColumn<>("Nombre");
        TreeTableColumn<File, String> ttcTamano = new TreeTableColumn<>("Tamaño");
        TreeTableColumn<File, Date> ttcFecha = new TreeTableColumn<>("Fecha de Modificación");

        ttcNombre.setPrefWidth(350);
        ttcNombre.setCellValueFactory(value -> {
            File file = value.getValue().getValue();
            FileOrDir fod = new FileOrDir(file.toString(), file.isFile());
            return new ReadOnlyObjectWrapper(fod);
        });

        ttcNombre.setCellFactory(cell -> {
            return new TreeTableCell<File, FileOrDir>() {

                @Override
                protected void updateItem(FileOrDir item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        this.setGraphic(null);
                        this.setText(null);
                    } else {
                        this.setGraphic(new OctIconView(item.isFile ? OctIcon.FILE : OctIcon.FILE_DIRECTORY));
                        this.setText(item.name);
                    }
                }

            };
        });

        ttcTamano.setCellValueFactory(cell -> {
            File file = cell.getValue().getValue();
            return new SimpleStringProperty(getFileSize(file));
        });

        ttcFecha.setCellValueFactory(cell -> {
            File file = cell.getValue().getValue();
            return new SimpleObjectProperty(new Date(file.lastModified()));
        });

        TreeTableView<File> treeView = new TreeTableView<>();
        treeView.setShowRoot(false);
        treeView.setRoot(archivos);
        treeView.getColumns().addAll(ttcNombre, ttcTamano, ttcFecha);

        File[] roots = File.listRoots();
        for (File disk : roots) {
            archivos.getChildren().add(createNode(disk));
        }

        VBox.setVgrow(treeView, Priority.ALWAYS);

        VBox root = new VBox();
        root.setPadding(new Insets(10.0));
        root.setSpacing(10.0);
        root.getChildren().add(new Label("Sistema de archivos"));
        root.getChildren().add(treeView);

        primaryStage.setTitle("JavaFX :: Explorador de Archivos");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
