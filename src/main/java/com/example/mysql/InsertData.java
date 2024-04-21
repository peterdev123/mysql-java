package com.example.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

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

    public static void insertProfileData(int userId, String fullname, String email, Date birthdate, String gender, String address) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLConnection.getConnection();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO profile (id, fullname, email, birthdate, gender, address) VALUES (?, ?, ?, ?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, fullname);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(4, birthdate);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, address);
            preparedStatement.executeUpdate();

            connection.commit();
            System.out.println("Profile created/updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating profile: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println("Failed to rollback: " + ex.getMessage());
                }
            }
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
