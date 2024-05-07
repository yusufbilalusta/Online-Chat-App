package com.example.demo;

import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private TextArea textArea;


    public Client(Socket socket, String username, TextArea textArea) {
        try {
            this.socket = socket;
            this.username = username;
            this.textArea = textArea;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            closeEverything();
        }
    }

    public void sendMessage(String message) {
        try {
            bufferedWriter.write(username + " : " + message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            closeEverything();
        }
    }

    public void listenForMessage() {
        new Thread(() -> {
            String receivedMessage;
            try {
                while ((receivedMessage = bufferedReader.readLine()) != null) {
                    textArea.appendText(receivedMessage + "\n");
                }
            } catch (IOException e) {
                closeEverything();
            }
        }).start();
    }

    public void closeEverything() {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}