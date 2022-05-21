package org.loose.fis.banking;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.loose.fis.introduction.Main;

public class bankingcontroller {
    @FXML
    private Label fundslabel;

    private Wallet userWallet;

    public void initialize(){
        userWallet = Walletservices.getWallet(Main.getUsername());

        fundslabel.setText("Your current funds: " + userWallet.getfunds() + "$");
    }

}
