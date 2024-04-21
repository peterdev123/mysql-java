package com.example.mysql;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AppController {
    @FXML
    public VBox pnLogin;

    public Label lblCheck, lblUpdate, lblStatus, lblAccoutnStatus, lblFullName, lblEmail, lblBirthdate, lblGender, lblAddress;

    public Button btnLogin, btnSignUp, btnReturn, btnModifyAccount, btnAddProfile, btnLogOut, btnViewProfile, btnEditProfile;

    public static int LogedUser;

    public TextField txtNewName, txtNewPassword, txtUsernameField, txtPasswordField, txtConfirmPasswordField, txtFullName, txtEmail, txtAddress
                    , birthdate1, txtGender1, txtAddress1, txtEmail1, txtFullName1;

    public DatePicker dpDOB;

    public ComboBox cmbGender;
    public DatePicker dpDOB1;

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;

    Profile profile = null;
    @FXML
    protected void onLoginClick() {
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
                lblCheck.setText("Make an account, or Incorrect username or password!.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void onSignUpClick() {
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
    protected void onRegisterClick() {
        String username = txtUsernameField.getText();
        String password = txtPasswordField.getText();
        String confirmPassword = txtConfirmPasswordField.getText();

        if (!confirmPassword.equals(password)) {
            lblStatus.setText("Confirm Password does not equal to Password");
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
    protected void onModifyClick() {
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
    protected void onDeleteAccount() {
        if(!ReadData.isDeleted(LogedUser)) {
            DeleteData.deleteAccount(LogedUser);
            lblUpdate.setText("Account Deleted!");
            return;
        }
        lblUpdate.setText("Account already deleted!");
    }

    @FXML
    protected void onProfileAdd() throws IOException {
        String fullname = txtFullName.getText();
        String email = txtEmail.getText();
        Date birthdate = java.sql.Date.valueOf(dpDOB.getValue());
        String gender = (String) cmbGender.getSelectionModel().getSelectedItem();
        String address = txtAddress.getText();
        InsertData.insertProfileData(LogedUser, fullname, email, birthdate, gender, address);
        onProfileReturn();
    }

    @FXML
    private void setNewUsername() {
        int currentUserId = LogedUser;
        String newUsername = txtNewName.getText();

        UpdateData updateData = new UpdateData();
        if(ReadData.isDeleted(currentUserId)) {
            lblUpdate.setText("Account already deleted!");
            return;
        }
        updateData.updateUsername(currentUserId, newUsername);
        System.out.println("New username set successfully!");
        lblUpdate.setText("Username set successfully!");
    }

    @FXML
    private void setNewPassword() {
        int currentUserId = LogedUser;
        String newPassword = txtNewPassword.getText();

        UpdateData updateData = new UpdateData();
        if(ReadData.isDeleted(currentUserId)) {
            lblUpdate.setText("Account already deleted!");
            return;
        }
        updateData.updatePassword(currentUserId, newPassword);
        System.out.println("New password set successfully!");
        lblUpdate.setText("Password set successfully!");
    }

    @FXML
    private void returnHomepage() {
        if(!ReadData.isDeleted(LogedUser)) {
            Stage modifyStage = (Stage) btnReturn.getScene().getWindow();
            modifyStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homepage-view.fxml"));
            try {
                Parent root = loader.load();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Stage modifyStage = (Stage) btnReturn.getScene().getWindow();
            modifyStage.close();
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
    }

    @FXML
    public void onProfileReturn() {
        Stage makeProfileStage = (Stage) txtEmail.getScene().getWindow();
        makeProfileStage.close();
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

    public void onRegisterReturn() {
        Stage registrationStage = (Stage) txtUsernameField.getScene().getWindow();
        registrationStage.close();
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

//    public void initializeHomepage() {
//        boolean hasProfile = checkUserProfile();
//        btnAddProfile.setVisible(!hasProfile);
//        btnEditProfile.setVisible(hasProfile);
//        btnViewProfile.setVisible(hasProfile);
//    }

    private boolean checkUserProfile() {
        return ReadData.doesProfileExist(LogedUser);
    }

    @FXML
    protected void onAddProfileClick() {
        if (!checkUserProfile()) {
            CreateTable.createProfileTable();
            Stage homepageStage = (Stage) btnModifyAccount.getScene().getWindow();
            homepageStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("addprofile-view.fxml"));
            try {
                Parent root = loader.load();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            lblAccoutnStatus.setText("Profile already exists!");
        }
    }

    @FXML
    protected void onViewProfileClick() {
        if (checkUserProfile()) {
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
        } else {
            lblAccoutnStatus.setText("Make a profile first!");
        }
    }

    @FXML
    public void onLogoutClick() {
        Stage makeProfileStage = (Stage) btnLogOut.getScene().getWindow();
        makeProfileStage.close();
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
    protected void onEditProfileClick() {
        if (checkUserProfile()) {
            Stage homepageStage = (Stage) btnModifyAccount.getScene().getWindow();
            homepageStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("editprofile-view.fxml"));
            try {
                Parent root = loader.load();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            lblAccoutnStatus.setText("Make a profile first!");
        }
    }

    @FXML
    protected void onReturnProfile() {
        Stage viewProfileStage = (Stage) lblAddress.getScene().getWindow();
        viewProfileStage.close();

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

    @FXML
    protected void onViewProfileMouse() {
        if (lblFullName != null) {
            while (profile == null) {
                profile = ReadData.readProfile(LogedUser);
                lblFullName.setText(profile.getFullname());
                lblEmail.setText(profile.getEmail());
                lblBirthdate.setText(profile.getBirthdate());
                lblGender.setText(profile.getGender());
                lblAddress.setText(profile.getAddress());
            }
        }

        if (txtFullName1 != null) {
            while (profile == null) {
                profile = ReadData.readProfile(LogedUser);
                txtFullName1.setText(profile.getFullname());
                txtEmail1.setText(profile.getEmail());
                txtGender1.setText(profile.getGender());
                txtAddress1.setText(profile.getAddress());
            }
        }
    }

    @FXML
    protected void onReturnProfile1() {
        Stage editProfileStage = (Stage) txtAddress1.getScene().getWindow();
        editProfileStage.close();

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

    @FXML
    protected void onSaveProfile() {
        String fullname = txtFullName1.getText();
        String email = txtEmail1.getText();
        Date birthdate = java.sql.Date.valueOf(dpDOB1.getValue());
        String gender = txtGender1.getText();
        String address = txtAddress1.getText();

        UpdateData.updateProfile(LogedUser, fullname, email, birthdate, gender, address);
    }
}