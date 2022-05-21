package org.loose.fis.game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


public class Controller {






    public void initialize() {
    }




    public void backToMenuScene(ActionEvent event) throws IOException {

        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("introduction/menuScene.fxml")));
        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root1);
            stage1.setScene(scene1);
            stage1.show();


    }





}