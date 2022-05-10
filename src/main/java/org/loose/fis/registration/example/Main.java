package org.loose.fis.registration.example;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.loose.fis.registration.example.services.UserService;

import javax.swing.event.ChangeListener;
import java.util.Objects;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {


        UserService.loadUsersFromFile();
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));

        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("register.fxml"))));
        primaryStage.setScene(scene);
        //primaryStage.setTitle("Registration Example");

        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);

        primaryStage.sizeToScene();

        //primaryStage.setScene(new Scene(root, 600, 400));


        primaryStage.show();
        Node root = scene.getRoot();
        Bounds rootBounds = root.getBoundsInLocal();

        double deltaW = primaryStage.getWidth() - rootBounds.getWidth();
        double deltaH = primaryStage.getHeight() - rootBounds.getHeight();

        Bounds prefBounds = getPrefBounds(root);

        primaryStage.setMinWidth(prefBounds.getWidth() + deltaW);
        primaryStage.setMinHeight(prefBounds.getHeight() + deltaH);

        primaryStage.widthProperty().addListener((o, oldValue, newValue)->{
            if(newValue.intValue() < 1280.0 ) {
                primaryStage.setResizable(false);
              //  primaryStage.setWidth(1280);
                primaryStage.setResizable(true);
            }
        });
        primaryStage.heightProperty().addListener((o, oldValue, newValue)->{
            if(newValue.intValue() < 720.0 ) {
                primaryStage.setResizable(false);
               // primaryStage.setHeight(720);
                primaryStage.setResizable(true);
            }
        });
    }



    private Bounds getPrefBounds(Node node) {
        double prefWidth ;
        double prefHeight ;

        Orientation bias = node.getContentBias();
        if (bias == Orientation.HORIZONTAL) {
            prefWidth = node.prefWidth(-1);
            prefHeight = node.prefHeight(prefWidth);
        } else if (bias == Orientation.VERTICAL) {
            prefHeight = node.prefHeight(-1);
            prefWidth = node.prefWidth(prefHeight);
        } else {
            prefWidth = node.prefWidth(-1);
            prefHeight = node.prefHeight(-1);
        }
        return new BoundingBox(0, 0, prefWidth, prefHeight);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
