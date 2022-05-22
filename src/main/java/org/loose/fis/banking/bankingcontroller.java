package org.loose.fis.banking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.loose.fis.introduction.Main;

import java.io.IOException;
import java.util.Objects;

public class bankingcontroller {
    @FXML
    private Label fundslabel;

    private Wallet userWallet;

    public void initialize(){
        userWallet = Walletservices.getWallet(Main.getUsername());

        fundslabel.setText("Your current funds: " + userWallet.getfunds() + "$");
    }

    public void switchToMiningScene(ActionEvent event) throws IOException {
        Stage stage1;
        Scene scene1;
        Parent root1;
        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("game/game.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.show();
    }

}
