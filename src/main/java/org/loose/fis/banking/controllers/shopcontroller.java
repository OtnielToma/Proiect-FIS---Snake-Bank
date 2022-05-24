package org.loose.fis.banking.controllers;


import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.ImagePattern;
import javafx.stage.StageStyle;
import org.loose.fis.authentication.Main;
import org.loose.fis.banking.exceptions.InvalidTransferAmountException;
import org.loose.fis.banking.model.Wallet;
import org.loose.fis.banking.services.Walletservices;


public class shopcontroller {

    public  void button1 () {

        int price = 15;

        Wallet userWallet = Walletservices.getWallet(Main.getUsername());

        if(userWallet.getfunds() >= price){
            userWallet.setfunds(userWallet.getfunds() - price);
            Walletservices.updateWallet(userWallet);

            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setContentText("Bought successfully! Your package will arrive in 2 days on your home address. ");
            alert.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.NONE);

            alert.setContentText("Not enough funds");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.show();


            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            alert.hide();
                            alert.close();

                        }
                    },
                    500
            );

            throw new InvalidTransferAmountException();
        }

    }

    public  void button2 () {

        int price = 7;

        Wallet userWallet = Walletservices.getWallet(Main.getUsername());

        if(userWallet.getfunds() >= price){
            userWallet.setfunds(userWallet.getfunds() - price);
            Walletservices.updateWallet(userWallet);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setContentText("Bought successfully! Your package will arrive in 2 days on your home address.");
            alert.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.NONE);

            alert.setContentText("Not enough funds");
            alert.initStyle(StageStyle.UTILITY);
            alert.show();


            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            alert.hide(); alert.close();
                        }
                    },
                    500
            );

            throw new InvalidTransferAmountException();
        }

    }
    public  void button3 () {

        int price = 3;

        Wallet userWallet = Walletservices.getWallet(Main.getUsername());

        if(userWallet.getfunds() >= price){
            userWallet.setfunds(userWallet.getfunds() - price);
            Walletservices.updateWallet(userWallet);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Bought successfully! Your package will arrive in 2 days on your home address.");
            alert.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.NONE);

            alert.setContentText("Not enough funds");
            alert.initStyle(StageStyle.UTILITY);
            alert.show();
            alert.hide(); alert.close();
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            alert.hide(); alert.close();
                        }
                    },
                    500
            );

            throw new InvalidTransferAmountException();
        }

    }




}
