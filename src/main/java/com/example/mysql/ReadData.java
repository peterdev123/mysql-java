package com.example.mysql;

import java.sql.*;

public class ReadData {
    public static void main(String[] args) {
        try (Connection c = MySQLConnection.getConnection();
             Statement statement = c.createStatement()){
            String query = "SELECT * FROM users";
            ResultSet res = statement.executeQuery(query);
            while(res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                String password = res.getString("password");
                System.out.println("ID " + id +
                        "\nName: " + name +
                        "\nEmail: " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean doesProfileExist(int userid) {
        boolean result = false;
        ResultSet res = null;
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "SELECT * FROM profile WHERE id = ?")) {
            statement.setInt(1, userid);
            res = statement.executeQuery();
            if (res.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isDeleted(int userid) {
        boolean result = true;
        ResultSet res = null;
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "SELECT * FROM users WHERE id = ?")) {
            statement.setInt(1, userid);
            res = statement.executeQuery();
            if (res.next()) {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
