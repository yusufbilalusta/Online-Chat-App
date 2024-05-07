package com.example.demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // ServerSocket belirli bir port üzerinden gelen bağlantıları dinlemek ve kabul etmek için kullanılır.(TCP)
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void Start(){
        try {
            while (!serverSocket.isClosed()){
                //Socket sinifi baglantiyi temsil eder
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");
                Handler handler = new Handler(socket);

                Thread thread = new Thread(handler);
                thread.start();
            }
        }catch (IOException e){

        }
    }
    public void Close(){
        try {
            if (serverSocket != null){
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket1 = new ServerSocket(1234);
        Server server = new Server(serverSocket1);
        server.Start();

    }
}
