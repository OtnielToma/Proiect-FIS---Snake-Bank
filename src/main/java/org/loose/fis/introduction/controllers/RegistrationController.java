package org.loose.fis.introduction.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.loose.fis.banking.Walletservices;
import org.loose.fis.introduction.Main;
import org.loose.fis.introduction.exceptions.UsernameOrPasswordIncorrectException;
import org.loose.fis.introduction.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.introduction.services.UserService;

import java.io.IOException;
import java.util.Objects;

import static javafx.stage.StageStyle.DECORATED;

public class RegistrationController {

    @FXML
    private AnchorPane loginPane;
    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField lastName;
    @FXML
    private TextField firstName;
    @FXML
    private ChoiceBox gender= new ChoiceBox();
    @FXML
    private DatePicker date;
    @FXML
    public Button closeButton;

    Stage stage1;
    Scene scene1;
    Parent root1;


    public RegistrationController() {

    }


    @FXML
    public void initialize() {
        gender.setItems(FXCollections.observableArrayList("Male","Female"));



    }



    @FXML
    public void handleRegisterAction() {

        try {
            if(usernameField.getText().length()>=4 && passwordField.getText().length()>=8)
            {
                UserService.addUser(usernameField.getText(), passwordField.getText(),lastName.getText(),firstName.getText(), (String) gender.getSelectionModel().getSelectedItem(),""+date.getValue().getDayOfMonth()+"/"+date.getValue().getMonthValue()+"/"+date.getValue().getYear());
                Walletservices.addWallets(usernameField.getText());

            registrationMessage.setText("Account created successfully!");}
            else
                registrationMessage.setText("Username or password invalid!");


        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
    }
    public void closeApp(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void switchToRegisterScene(ActionEvent event) throws IOException {

        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("introduction/registerScene.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.setMaximized(true);
        stage1.setResizable(false);
        stage1.show();
    }

    public void switchToLoginScene(ActionEvent event) throws IOException {
        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("introduction/loginScene.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.show();
    }

    public void switchToMenuScene(ActionEvent event) throws IOException {

        if(handleLoginAction())
        {
        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("introduction/menuScene.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
       // stage1.setFullScreen(!stage1.isFullScreen());
        stage1.setResizable(false);
        stage1.show();}


    }

    public void switchToMiningScene(ActionEvent event) throws IOException {
            root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("game/game.fxml")));
            stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene1 = new Scene(root1);
            stage1.setScene(scene1);
            stage1.setMaximized(true);
           // System.out.println(stage1.getHeight());
          //  stage1.setFullScreen(!stage1.isFullScreen());
            stage1.setResizable(false);
            stage1.show();
    }

    public void switchToBankingScene(ActionEvent event) throws IOException {
        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("banking/banking.fxml")));
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
       // stage1.setFullScreen(!stage1.isFullScreen());
        stage1.setResizable(false);
        stage1.show();
    }


    public boolean handleLoginAction() {
        try {
            UserService.checkPassword(usernameField.getText(),passwordField.getText());
               //daca nu exista username ul afiseaza urmatorul rand
             registrationMessage.setText("Logged in!");
            Main.setUsername(usernameField.getText());
            return true;
        } catch (UsernameOrPasswordIncorrectException e) {

            registrationMessage.setText(e.getMessage());return false;
            //registrationMessage.setText("parola sau username incorect");
        }

    }
    @FXML
    private void switchToMenuSceneByEnter(KeyEvent event) throws IOException {
        if (event.getCode() == event.getCode().ENTER) {
            if(handleLoginAction())
            {
                root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("introduction/menuScene.fxml")));
                stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene1 = new Scene(root1);
                stage1.setScene(scene1);
               // stage1.setFullScreen(!stage1.isFullScreen());
                stage1.setResizable(false);
                stage1.show();}
        }
    }

}
