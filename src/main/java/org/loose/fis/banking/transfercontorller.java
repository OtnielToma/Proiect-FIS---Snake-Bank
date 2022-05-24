package org.loose.fis.banking;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.loose.fis.authentication.Main;

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
            transferButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Transfer to " + friendName);

                    Wallet userWallet = Walletservices.getWallet(Main.getUsername());
                    Wallet friendWallet = Walletservices.getWallet(friendName);

                    if(userWallet.getfunds() >= Integer.parseInt(transferAmount.getText())){
                        userWallet.setfunds(userWallet.getfunds() - Integer.parseInt(transferAmount.getText()));
                        friendWallet.setfunds(friendWallet.getfunds() + Integer.parseInt(transferAmount.getText()));
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Transfer successful");
                        alert.show();
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invalid transfer amount");
                        alert.show();
                        throw new InvalidTransferAmountException();
                    }

                    Walletservices.updateWallet(userWallet);
                    Walletservices.updateWallet(friendWallet);
                }
            });

            friendContainer.getChildren().add(friendUsernameLabel);
            friendContainer.getChildren().add(transferButton);

            vBox.getChildren().add(friendContainer);
        }
    }
}
