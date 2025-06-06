import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField nimField;

    @FXML
    private Button registerButton;

    @FXML
    private Button backButton;

    private static List<User> registeredUsers = new ArrayList<>();

    public static class User {
        private String username;
        private String password;
        private String fullName;
        private String nim;

        public User(String username, String password, String fullName, String nim){
            this.username = username;
            this.password = password;
            this.fullName = fullName;
            this.nim = nim;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getFullName() {
            return fullName;
        }

        public String getNim() {
            return nim;
        }
    }

    @FXML
    private void handleRegister(ActionEvent event){
        String username = usernameField.getText();
        String password = passwordField.getText();
        String fullName = fullNameField.getText();
        String nim = nimField.getText();

        if (username.isEmpty()){
            showAlert("Error", "Username Tidak Boleh Kosong",Alert.AlertType.ERROR);
            return;
        }
        if (password.isEmpty()){
            showAlert("Error", "Password Tidak Boleh Kosong",Alert.AlertType.ERROR);
            return;
        }
        if (fullName.isEmpty()){
            showAlert("Error", "Nama Lengkap Tidak Boleh Kosong",Alert.AlertType.ERROR);
            return;
        }
        if (nim.isEmpty()){
            showAlert("Error", "NIM Tidak Boleh Kosong",Alert.AlertType.ERROR);
            return;
        }

        if (isUsernameExists(username)){
            showAlert("Error", "Username Sudah Terdaftar",Alert.AlertType.ERROR);
            return;
        }
        if(isNimExists(nim)){
            showAlert("Error", "NIM Sudah Terdaftar",Alert.AlertType.ERROR);
            return;
        }

        User newUser = new User(username, password, fullName, nim);
        registeredUsers.add(newUser);

        showAlert("Success", "Registrasi Berhasil! Selamat datang " + fullName + "!", Alert.AlertType.INFORMATION);

        clearForm();

        handleBackToLogin(event);
    }

    @FXML
    private void handleBackToLogin(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/user/login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login - Sistem Informasi XYZ");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Gagal kembali ke halaman login", Alert.AlertType.ERROR);
        }
    }

    private boolean isUsernameExists(String username){
        return registeredUsers.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }

    private boolean isNimExists(String nim){
        return registeredUsers.stream().anyMatch(user -> user.getNim().equals(nim));
    }

    private  void clearForm(){
        usernameField.clear();
        passwordField.clear();
        fullNameField.clear();
        nimField.clear();
    }

    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static List<User> getRegisteredUsers() {
        return registeredUsers;
    }
}
