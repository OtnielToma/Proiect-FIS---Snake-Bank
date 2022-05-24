package org.loose.fis.banking;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class transfercontorller {
    @FXML
    private VBox vBox;

    @FXML
    private TextField transferAmount;

    public void initialize() throws CouldNotFindUserException {
        ArrayList<String> friends = FriendsListservices.getFriends();

        for (String friendName : friends) {
            HBox friendContainer = new HBox();

            Label friendUsernameLabel = new Label();
            friendUsernameLabel.setText(friendName);

            Button transferButton = new Button();
            transferButton.setText("Transfer");
        }

    }
}
