package org.loose.fis.introduction;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.loose.fis.banking.Walletservices;
import org.loose.fis.introduction.services.UserService;

import java.util.Objects;

public class Main extends Application {

    private static String username;

    @Override
    public void start(Stage primaryStage) throws Exception {


        UserService.loadUsersFromFile();
        Walletservices.loadWalletFromFile();
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));

        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("introduction/loginScene.fxml"))));

        primaryStage.setScene(scene);

        primaryStage.setTitle("Snake Bank");
        primaryStage.getIcons().add(new Image("/introduction/Logo.png"));


        primaryStage.setWidth(1920);
        primaryStage.setHeight(1080);

        primaryStage.setMaximized(true);primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.setFullScreen(true);

        primaryStage.show();


    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Main.username = username;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
