package org.loose.fis.banking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class addFriendsController {

    @FXML
    private TextField friendUsernameBox;
    @FXML
    private Label infoLabel;
    @FXML
    private Button closeButton;

    @FXML
    public void closeApp(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    Stage stage1;
    Scene scene1;
    Parent root1;
    public void backToMenuScene(ActionEvent event) throws IOException {
        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("banking/banking.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.show();
    }

    public void addFriendClick(){

        try{
            FriendsListservices.addFriend(friendUsernameBox.getText());
            infoLabel.setText("Friend added.");
        }
        catch (CouldNotFindUserException ex){
            infoLabel.setText("Could not find username.");
        }
    }

}
