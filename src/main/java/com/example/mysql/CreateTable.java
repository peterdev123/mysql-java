package com.example.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {
        try (Connection c = MySQLConnection.getConnection();
            Statement statement = c.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "password VARCHAR(100) NOT NULL)";
            statement.execute(query);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        try (Connection c = MySQLConnection.getConnection();
             Statement statement = c.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "password VARCHAR(100) NOT NULL)";
            statement.execute(query);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createProfileTable() {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("CREATE TABLE IF NOT EXISTS profile (" +
                     "profileid INT AUTO_INCREMENT PRIMARY KEY," +
                     "id INT NOT NULL," +
                     "FOREIGN KEY (id) REFERENCES users(id)," +
                     "fullname VARCHAR(50) NOT NULL," +
                     "email VARCHAR(100) NOT NULL," +
                     "birthdate DATE NOT NULL," +
                     "gender VARCHAR(50) NOT NULL," +
                     "address VARCHAR(100) NOT NULL" +
                     ")")) {
            statement.execute();
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



