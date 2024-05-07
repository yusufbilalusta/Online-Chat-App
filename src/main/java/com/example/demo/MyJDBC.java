package com.example.demo;
import java.sql.*;
import java.util.ArrayList;

public class MyJDBC {

    public ArrayList<String> getUsers(){
        ArrayList<String> userList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/login_schema",
                    "root",
                    "123456"
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM login_schema.users");

            while (resultSet.next()){
                String username = resultSet.getString("username");
                userList.add(username);

            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();

        }
        return userList;
    }
    public ArrayList<String> getPassword(){
        ArrayList<String> passwordList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/login_schema",
                    "root",
                    "123456"
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM login_schema.users");

            while (resultSet.next()){
                String password = resultSet.getString("password");
                passwordList.add(password);
            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();

        }
        return passwordList;
    }
    public static void addUser(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/login_schema",
                    "root",
                    "123456"
            );

            // SQL sorgusunu hazırla
            String sql = "INSERT INTO login_schema.users (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Parametreleri ayarla
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Sorguyu çalıştır
            preparedStatement.executeUpdate();

            // Kullanıcı başarıyla eklendi mesajı yazdır
            System.out.println("Kullanıcı başarıyla eklendi.");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}
