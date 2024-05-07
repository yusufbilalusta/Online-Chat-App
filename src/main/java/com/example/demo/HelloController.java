package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloController {

    @FXML
    private PasswordField password_textField;
    @FXML
    private Label mesaj_alani;

    @FXML
    private TextField userName_textField;
    private MyJDBC myJDBC = new MyJDBC();
    ArrayList<String> userarraylist;
    ArrayList<String> passwordarraylist;
    public static String username;

    @FXML
    void login_button(ActionEvent event) {
        // Veritabanından kullanıcı adları ve şifreleri al
        userarraylist = myJDBC.getUsers();
        passwordarraylist = myJDBC.getPassword();

        String username = userName_textField.getText();
        String password = password_textField.getText();

        if (userarraylist.contains(username) && passwordarraylist.get(userarraylist.indexOf(username)).equals(password)) {
            mesaj_alani.setText("Başarılı giriş!");
            try {
                this.username = username;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("application.fxml"));
                Parent root = loader.load();

                Scene scene = mesaj_alani.getScene();

                scene.setRoot(root);
 /*               ChatApplication chatApplication = new ChatApplication();
                chatApplication.start(new Stage());*/
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            // Kullanıcı adı veya şifre yanlışsa hata mesajını göster
            mesaj_alani.setText("Hatalı kullanıcı adı veya şifre!");
        }
    }

    @FXML
    void register_button(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
            Scene root = new Scene(loader.load(), 580, 400);
            Stage stage = new Stage();
            stage.setScene(root);
            stage.show();

        } catch (IOException e) {
            System.err.println("Yeni pencere yüklenirken hata oluştu: "+e.getMessage());
        }
    }




}