package org.loose.fis.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {


       // UserService.loadUsersFromFile();


        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("game/game.fxml"))));
        primaryStage.setScene(scene);


        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);

        primaryStage.sizeToScene();


        primaryStage.show();
        Node root = scene.getRoot();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
