package org.loose.fis.registration.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.loose.fis.registration.example.services.UserService;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        UserService.loadUsersFromFile();

        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));

        Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml")));
        primaryStage.setScene(scene);
        //primaryStage.setTitle("Registration Example");
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
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
            if(newValue.intValue() < 600.0 ) {
                primaryStage.setResizable(false);
                primaryStage.setWidth(600);
                primaryStage.setResizable(true);
            }
        });
        primaryStage.heightProperty().addListener((o, oldValue, newValue)->{
            if(newValue.intValue() < 400.0 ) {
                primaryStage.setResizable(false);
                primaryStage.setHeight(400);
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
