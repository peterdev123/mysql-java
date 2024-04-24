package com.example.mysql;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;

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

    public static Profile readProfile(int userid) {
        Profile profile = null;
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("SELECT * FROM profile WHERE id = ?")) {
            statement.setInt(1, userid);
            try (ResultSet res = statement.executeQuery()) {
                if (res.next()) {
                    String fullName = res.getString("fullname");
                    String email = res.getString("email");
                    String birthdate = res.getString("birthdate");
                    String gender = res.getString("gender");
                    String address = res.getString("address");

                    profile = new Profile(fullName, email, birthdate, gender, address);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }

    public static LocalDate getDateOfBirth(int userId) {
        String sql = "SELECT birthdate FROM profile WHERE id = ?";
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Date date = rs.getDate("birthdate");
                return date.toLocalDate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
