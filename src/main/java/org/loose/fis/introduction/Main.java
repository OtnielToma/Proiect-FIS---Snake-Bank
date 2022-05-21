package org.loose.fis.introduction;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.loose.fis.introduction.services.UserService;

import java.util.Objects;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {


        UserService.loadUsersFromFile();
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));

        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("introduction/loginScene.fxml"))));

        primaryStage.setScene(scene);



        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);

        primaryStage.sizeToScene();

        //primaryStage.setScene(new Scene(root, 600, 400));


        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }

}
