package com.example.mysql;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
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

    public Label txtCheck, txtUpdate, statusLabel;

    public Button btnLogin, btnSignUp, btnReturn, btnModifyAccount, btnAddProfile;

    public static int LogedUser;

    public TextField txtNewName, txtNewPassword, txtUsernameField, txtPasswordField, txtConfirmPasswordField;

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    protected void onLoginClick() throws IOException {
        String username = txtUser.getText();
        String password = txtPass.getText();

        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "SELECT * FROM users WHERE name = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                LogedUser = resultSet.getInt("id");

                Stage loginStage = (Stage) txtUser.getScene().getWindow();
                loginStage.close();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("homepage-view.fxml"));
                try {
                    Parent root = loader.load();
                    Stage newStage = new Stage();
                    newStage.setScene(new Scene(root));
                    newStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                txtCheck.setText("Make an account, or Incorrect username or password!.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void onSignUpClick() throws IOException {
        Stage loginStage = (Stage) txtUser.getScene().getWindow();
        loginStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup-view.fxml"));
        try {
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onRegisterClick() throws IOException {
        String username = txtUsernameField.getText();
        String password = txtPasswordField.getText();
        String confirmPassword = txtConfirmPasswordField.getText();

        if (!confirmPassword.equals(password)) {
            statusLabel.setText("Confirm Password does not equal to Password");
            return;
        }

        InsertData.insertRegisterData(username, password);
        Stage signUpStage = (Stage) txtUsernameField.getScene().getWindow();
        signUpStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        try {
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAddProfileClick() throws IOException {
        Stage homepageStage = (Stage) btnModifyAccount.getScene().getWindow();
        homepageStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile-view.fxml"));
        try {
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onModifyClick() throws IOException {
        Stage homepageStage = (Stage) btnModifyAccount.getScene().getWindow();
        homepageStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("modify-view.fxml"));
        try {
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDeleteAccount() {
        DeleteData.deleteAccount(LogedUser);
        txtUpdate.setText("Account Deleted!");
    }

    @FXML
    private void setNewUsername() {
        int currentUserId = LogedUser;
        String newUsername = txtNewName.getText();

        UpdateData updateData = new UpdateData();
        try {
            updateData.updateUsername(currentUserId, newUsername);
            System.out.println("New username set successfully!");
            txtUpdate.setText("Username set successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setNewPassword() {
        int currentUserId = LogedUser;
        String newPassword = txtNewPassword.getText();

        UpdateData updateData = new UpdateData();
        try {
            updateData.updatePassword(currentUserId, newPassword);
            System.out.println("New password set successfully!");
            txtUpdate.setText("Password set successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void returnLogin() throws IOException {
        Stage homepageStage = (Stage) btnReturn.getScene().getWindow();
        homepageStage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("homepage-view.fxml"));
        try {
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}