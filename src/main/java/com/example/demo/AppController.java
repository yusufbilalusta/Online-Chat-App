package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private Button button_send;

    @FXML
    private TextArea text_area;

    @FXML
    private TextField textfield_msg;

    @FXML
    private Label username_label;
    private Client client;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Socket socket = new Socket("localhost", 1234); // Bağlanılacak sunucu adresi ve port numarası
            client = new Client(socket, HelloController.username, text_area);
            client.listenForMessage();
            username_label.setText(HelloController.username);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textfield_msg.setOnKeyPressed(event -> {
            if (event.getCode().getName().equals("Enter")) {
                sendMessage(new ActionEvent());
            }
        });
    }

    @FXML
    public void sendMessage(ActionEvent event) {
        String message = textfield_msg.getText().trim();
        if (!message.isEmpty()) {
            client.sendMessage(message);
            textfield_msg.clear();
        }
    }

    public Client getClient(){
        return client;
    }
}