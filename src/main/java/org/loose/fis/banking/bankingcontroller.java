package org.loose.fis.banking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.loose.fis.authentication.Main;

import java.io.IOException;
import java.util.Objects;

public class bankingcontroller {
    @FXML
    private Label fundslabel;
    @FXML
    public Button closeButton;
    private Wallet userWallet;

    public void initialize(){
        userWallet = Walletservices.getWallet(Main.getUsername());

        fundslabel.setText("$" + (float) userWallet.getfunds());
    }
    Stage stage1;
    Scene scene1;
    Parent root1;

    public void closeApp(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void switchToMiningScene(ActionEvent event) throws IOException {

        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("game/game.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.show();
    }

    public void backToMenuScene(ActionEvent event) throws IOException {
        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("authentication/menuScene.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.show();
    }

    public  void  popupAddFriend(ActionEvent event) throws IOException {

        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("banking/friends.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.show();

    }


    public  void  popupTransfer(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("banking/transfer.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Transfer");
            stage.setScene(new Scene(root, 650, 500));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }



}
