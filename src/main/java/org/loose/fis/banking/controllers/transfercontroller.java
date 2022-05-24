package org.loose.fis.banking.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.loose.fis.authentication.Main;
import org.loose.fis.banking.exceptions.CouldNotFindUserException;
import org.loose.fis.banking.exceptions.InvalidTransferAmountException;
import org.loose.fis.banking.model.Wallet;
import org.loose.fis.banking.services.FriendsListservices;
import org.loose.fis.banking.services.Walletservices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class transfercontroller {
    @FXML
    private VBox vBox;

    @FXML
    private TextField transferAmount;
    @FXML
    public Button closeButton;

    public void closeApp(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    Stage stage1;
    Scene scene1;
    Parent root1;

    public void backToBankingScene(ActionEvent event) throws IOException {
        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("banking/banking.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.setMaximized(true);
        stage1.setResizable(false);
        stage1.show();
    }

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
