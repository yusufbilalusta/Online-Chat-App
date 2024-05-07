package com.example.demo;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Handler implements Runnable {

    public static ArrayList<Handler> handlerArrayList = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
//    private String username;

    private Client client;

    public Handler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            this.username = bufferedReader.readLine();
            handlerArrayList.add(this);
//            broadcastMessage("SERVER: "+ username+ " has entered the chat");


        }catch (IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);

        }

    }


    @Override
    public void run() {
        String msg;
        while (socket.isConnected()){
            try {
                msg = bufferedReader.readLine();
                broadcastMessage(msg);
            }catch (IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
                break;
            }
        }
    }
    public void broadcastMessage(String gonderilcekMsg){
        for (Handler handler: handlerArrayList) {
            try {

                handler.bufferedWriter.write(gonderilcekMsg);
                handler.bufferedWriter.newLine();
                handler.bufferedWriter.flush();

            }catch (IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
            }
        }
    }

    public void removeClientHandler(){
        handlerArrayList.remove(this);
 //       broadcastMessage("SERVER: "+ username+" has left the chat!");
    }
    public void closeEverything(Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter){
        removeClientHandler();
        try{
            if (bufferedReader !=null){
                bufferedReader.close();
            }
            if (bufferedWriter !=null){
                bufferedWriter.close();
            }
            if (socket !=null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
