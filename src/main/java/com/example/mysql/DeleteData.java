package com.example.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {
    public static void main(String[] args) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = c.prepareStatement(
                     "DELETE FROM users WHERE id = ?")) {

            int userIdToDelete = 5;

            preparedStatement.setInt(1, userIdToDelete);

            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println("Rows Deleted: " + rowsDeleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAccount(int id) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = c.prepareStatement(
                     "DELETE FROM profile WHERE id = ?")) {

            int userIdToDelete = id;

            preparedStatement.setInt(1, userIdToDelete);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Data deleted succesfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = c.prepareStatement(
                     "DELETE FROM users WHERE id = ?")) {

            int userIdToDelete = id;

            preparedStatement.setInt(1, userIdToDelete);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Data deleted succesfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProfile(int id) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = c.prepareStatement(
                     "DELETE FROM profile WHERE id = ?")) {

            int userIdToDelete = id;

            preparedStatement.setInt(1, userIdToDelete);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Data deleted succesfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
