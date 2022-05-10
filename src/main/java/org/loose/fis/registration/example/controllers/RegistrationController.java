package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.registration.example.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.registration.example.services.UserService;
import org.loose.fis.registration.example.exceptions.UsernameOrPasswordIncorrectException;
import javax.swing.*;
import java.awt.*;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Text welcome;
    @FXML
    public void initialize() {

    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText());
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    public void handleLoginAction() {
        try {
            UserService.checkPassword(usernameField.getText(),passwordField.getText());
               //daca nu exista username ul afiseaza urmatorul rand
                registrationMessage.setText("Logged in!");
        } catch (UsernameOrPasswordIncorrectException e) {
            registrationMessage.setText(e.getMessage());
            //registrationMessage.setText("parola sau username incorect");
        }
    }

}
