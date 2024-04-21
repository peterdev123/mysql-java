package com.example.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {

    public static void main(String[] args) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE users SET name = ? WHERE id = ?")) {

            String name = "Raphael Escosia";
            int id = 2;

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUsername(int userIdToUpdate, String newUsername){
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE users SET name = ? WHERE id = ?")) {

            preparedStatement.setString(1, newUsername);
            preparedStatement.setInt(2, userIdToUpdate);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data updated successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePassword(int userIdToUpdate, String newPassword){
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE users SET password = ? WHERE id = ?")) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, userIdToUpdate);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Password updated successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateProfile(int userid, String fullname, String email, Date birthdate, String gender, String address) {
        try (Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE profile SET fullname = ?, email = ?, birthdate = ?, gender = ?, address = ? WHERE id = ?"
                ))  {

            preparedStatement.setString(1, fullname);
            preparedStatement.setString(2, email);
            preparedStatement.setDate(3, birthdate);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, address);
            preparedStatement.setInt(6, userid);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Profile updated successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
