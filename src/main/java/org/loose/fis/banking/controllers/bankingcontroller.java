package org.loose.fis.banking.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.loose.fis.authentication.Main;
import org.loose.fis.banking.exceptions.InvalidTransferAmountException;
import org.loose.fis.banking.model.Wallet;
import org.loose.fis.banking.services.Walletservices;

import java.io.IOException;
import java.util.Objects;

public class bankingcontroller {
    @FXML
    private Label fundslabel;
    @FXML
    public Button closeButton;
    @FXML
    private GridPane ShopGrid;
    @FXML
    private GridPane hideGrid;
    @FXML
    private Label alertMoney;
    @FXML
    private Label alertMoney1;

    private Wallet userWallet;

    public void initialize(){
        userWallet = Walletservices.getWallet(Main.getUsername());

        fundslabel.setText("$" + (float) userWallet.getfunds());
        if(userWallet.getfunds()>99999999)
            fundslabel.setFont(new Font(80));
        else
            fundslabel.setFont(new Font(100));
    }
    Stage stage1;
    Scene scene1;
    Parent root1;

    public void closeApp(ActionEvent event) {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void switchToMiningScene(ActionEvent event) throws IOException {

        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("game/game.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.setMaximized(true);
        stage1.setResizable(false);
        stage1.show();
    }

    public void backToMenuScene(ActionEvent event) throws IOException {
        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("authentication/menuScene.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.setMaximized(true);
        stage1.setResizable(false);
        stage1.show();
    }

    public void backToBankingScene(ActionEvent event) throws IOException {
        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("banking/banking.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.setMaximized(true);
        stage1.setResizable(false);
        stage1.show();
    }

    public  void  popupAddFriend(ActionEvent event) throws IOException {

        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("banking/friends.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.setMaximized(true);
        stage1.setResizable(false);
        stage1.show();

    }


    public  void  popupTransfer(ActionEvent event) throws IOException {
        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("banking/transfer.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.setResizable(false);stage1.setMaximized(true);
        stage1.show();


    }

    public  void  popupShop(){
        ShopGrid.setVisible(true);
       // ShopGrid.toFront();
        hideGrid.setVisible(true);
       // hideGrid.toFront();
    }
    public  void button1 () {
    int price = 15;

    Wallet userWallet = Walletservices.getWallet(Main.getUsername());

        if(userWallet.getfunds() >= price){
        userWallet.setfunds(userWallet.getfunds() - price);
        Walletservices.updateWallet(userWallet);

            alertMoney1.setVisible(true);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            alertMoney1.setVisible(false);
                        }
                    },
                    3000
            );
    }
        else{
            alertMoney.setVisible(true);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            alertMoney.setVisible(false);
                        }
                    },
                    2000
            );
        //throw new InvalidTransferAmountException();
    }

}

    public  void button2 () {

        int price = 7;

        Wallet userWallet = Walletservices.getWallet(Main.getUsername());

        if(userWallet.getfunds() >= price){
            userWallet.setfunds(userWallet.getfunds() - price);
            Walletservices.updateWallet(userWallet);

            alertMoney1.setVisible(true);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            alertMoney1.setVisible(false);
                        }
                    },
                    3000
            );
        }
        else{
            alertMoney.setVisible(true);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            alertMoney.setVisible(false);
                        }
                    },
                    2000
            );
           // throw new InvalidTransferAmountException();
        }

    }
    public  void button3 (ActionEvent event) throws InterruptedException, IOException {

        int price = 5;

        Wallet userWallet = Walletservices.getWallet(Main.getUsername());

        if(userWallet.getfunds() >= price){
            userWallet.setfunds(userWallet.getfunds() - price);
            Walletservices.updateWallet(userWallet);

            alertMoney1.setVisible(true);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            alertMoney1.setVisible(false);
                        }
                    },
                    3000
            );
        }
        else{

            alertMoney.setVisible(true);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            alertMoney.setVisible(false);
                        }
                    },
                    2000
            );
            //throw new InvalidTransferAmountException();
        }

    }



}
