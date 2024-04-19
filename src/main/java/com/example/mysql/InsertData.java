package com.example.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void main(String[] args) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement (
                     "INSERT INTO users (name, password) VALUES (?,?)"
             ))  {
            String name = "Peter";
            String password = "password123";
            statement.setString(1, name);
            statement.setString(2, password);
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Data inserted successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertRegisterData(String username, String password) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement (
                     "INSERT INTO users (name, password) VALUES (?,?)"
             ))  {
            statement.setString(1, username);
            statement.setString(2, password);
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Data inserted successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
