package org.loose.fis.banking;


import javafx.scene.control.Alert;
import org.loose.fis.authentication.Main;



public class shopcontroller {

    public  void button1 () {

        int price = 15;

        Wallet userWallet = Walletservices.getWallet(Main.getUsername());

        if(userWallet.getfunds() >= price){
            userWallet.setfunds(userWallet.getfunds() - price);
            Walletservices.updateWallet(userWallet);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Bought successfully! Your package will arrive in 2 days on your home address. ");
            alert.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Not enough funds");
            alert.show();
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
            alert.setContentText("Bought successfully! Your package will arrive in 2 days on your home address.");
            alert.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Not enough funds");
            alert.show();
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Not enough funds");
            alert.show();
            throw new InvalidTransferAmountException();
        }

    }




}
