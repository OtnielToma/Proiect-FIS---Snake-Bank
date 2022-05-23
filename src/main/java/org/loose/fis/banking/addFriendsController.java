package org.loose.fis.banking;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class addFriendsController {

    @FXML
    private TextField friendUsernameBox;
    @FXML
    private Label infoLabel;

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
