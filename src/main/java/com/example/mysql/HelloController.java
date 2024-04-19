package com.example.mysql;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    public VBox pnLogin;

    public Button pnLogout;

    public ColorPicker cpPicker;

    public Button btnLogin;

    public AnchorPane login, logout;

//    private static final String[] allUsernames = {"user1", "user2", "user3"};
//    private static final String[] allPassword = {"pass1", "pass2", "pass3"};

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    protected void onLoginClick() throws IOException {
        boolean isValid = false;
        String username = txtUser.getText();
        String password = txtPass.getText();

//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(getClass().getResource("user1.css").getPath(), true));
//            bw.write(".root { -fx-background-color: #ff4500 }");
//            bw.newLine();
//            bw.close();
//        } catch (IOException e) {
//        }

//        boolean isValid = false;
//        for (int i = 0; i < allUsernames.length; i++) {
//            if (username.equals(allUsernames[i]) && password.equals(allPassword[i])) {
//                isValid = true;
//                break;
//            }
//        }

        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "SELECT * FROM users WHERE name = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                int LogedUser = userId;
                isValid = true;
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
//                Parent root = loader.load();
//                Stage stage = new Stage();
//                stage.setScene(new Scene(root));
//                stage.show();
            }
//            else {
//                actionTarget.setText("Make an account, or Incorrect username or password!.");
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (isValid) {
            AnchorPane p = (AnchorPane) pnLogin.getParent();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

//    @FXML
//    protected void onLogoutClick() throws IOException {
//        Color color = cpPicker.getValue();
//        String realColor = "rgb(" +
//                (int)(color.getRed() * 255) + ", " +
//                (int)(color.getGreen() * 255) + ", " +
//                (int)(color.getBlue() * 255) + ")";
//
//        System.out.println(color);
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(getClass().getResource("user1.css").getPath()));
//            bw.write(".root { -fx-background-color: #27200d; }");
//            bw.newLine();
//            bw.write(".button { -fx-background-color: " + realColor + " }");
//            bw.newLine();
//            bw.close();
//        } catch (IOException e) {
//        }
//        AnchorPane p = (AnchorPane) pnLogout.getParent();
//        Parent scene = FXMLLoader.load(getClass().getResource("login-view.fxml"));
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(getClass().getResource("login.css").getPath()));
//            bw.write(".root { -fx-background-color:  #27200d; }");
//            bw.newLine();
//            bw.close();
//        } catch (IOException e) {
//        }
//        AnchorPane pe = (AnchorPane) pnLogout.getParent();
//        pe.getScene().getStylesheets().clear();
//        pe.getScene().getStylesheets().add(getClass().getResource("login.css").toExternalForm());
//        pe.getChildren().clear();
//        pe.getChildren().add(scene);
//    }
    @FXML
    private void onDeleteAccount() {
    //    try (Connection c = MySqlConnection.getConnection();
    //         PreparedStatement preparedStatement = c.prepareStatement(
    //                 "DELETE FROM users WHERE id = ?")) {
    //
    //        int userIdToDelete = HelloApplication.LogedUser;
    //
    //        preparedStatement.setInt(1, userIdToDelete);
    //
    //        int rowsDeleted = preparedStatement.executeUpdate();
    //        if (rowsDeleted > 0) {
    //            System.out.println("Data deleted succesfully!");
    //        }
    //    } catch (SQLException e) {
    //        e.printStackTrace();
    //    }
    }

    @FXML
    private void setNewUsername() {
//        int currentUserId = HelloApplication.LogedUser;
//        String newUsername = nameNewName.getText();
//
//        UpdateData updateData = new UpdateData();
//        try {
//            updateData.updateUsername(currentUserId, newUsername);
//            System.out.println("New username set successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    private void setNewPassword() {
//        int currentUserId = HelloApplication.LogedUser;
//        String newPassword = psNewPassword.getText();
//
//        UpdateData updateData = new UpdateData();
//        try {
//            updateData.updatePassword(currentUserId, newPassword);
//            System.out.println("New password set successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

}