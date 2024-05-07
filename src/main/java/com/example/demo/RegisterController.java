package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {



    @FXML
    private Label mesaj_alani;

    @FXML
    private PasswordField password_textField;

    @FXML
    private TextField userName_textField;

    @FXML
    private Button back_button;

    @FXML
    private Button register_button;
    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Kayit Başarılı");
        alert.setHeaderText(null);
        alert.setContentText("Kayit başarıyla gerçekleştirildi!");
        alert.showAndWait();
    }
    @FXML
    public void Register(ActionEvent event) throws IOException {
        MyJDBC.addUser(userName_textField.getText(),password_textField.getText());
        // Yeni pencereyi yükle

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene root = new Scene(loader.load(), 750, 510);
        Stage stage = new Stage();
        stage.setScene(root);
        stage.show();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        showSuccessAlert();


    }

    @FXML
    public void backButtonAction(ActionEvent event) throws IOException {

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene root = new Scene(loader.load(), 769, 517);
        Stage stage = new Stage();
        stage.setScene(root);
        stage.show();
    }
}
