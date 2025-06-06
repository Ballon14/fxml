import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.EOFException;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernamField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    private final String username = "admin";
    private final String password = "admin123";

    @FXML
    private void intialize() {
        usernamField.setOnMouseClicked(e -> {
            if (usernamField.getText().equals("Masukan Username")){
                usernamField.clear();
            }
        });

        passwordField.setOnMouseClicked(e -> {
            if (passwordField.getText().equals("Masukan Password")){
                passwordField.clear();
            }
        });
    }

    @FXML
    private void handleLogin(ActionEvent event){
        String username = usernamField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || username.equals("Masukan Username")){
            showAlert("Error", "Username belum diisi", Alert.AlertType.ERROR);
            return;
        }

        if (password.isEmpty() || password.equals("Masukan Password")){
            showAlert("Error", "Password belum diisi", Alert.AlertType.ERROR);
            return;
        }

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)){
            showAlert("Success", "Login Berhasil Selamat Datang Admin", Alert.AlertType.INFORMATION);
            loadAdminDashboard();
        } else {
            showAlert("Succes", "Login Berhasik Selamat Datang" + username, Alert.AlertType.INFORMATION);
            loadUserDashboard();
        }
    }

    @FXML
    private void showRegisterForm(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/user/register.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Registrasi -  Sistem Informasi XYZ");
        } catch (IOException e){
            e.printStackTrace();
            showAlert("Error", "Gagal Memuat Form Registrasi", Alert.AlertType.ERROR);
        }
    }

    private void loadAdminDashboard(){
        try{
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("/admin/adminDashboard.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard Admin - Sistem Informasi XYZ");
        } catch (IOException e){
            e.printStackTrace();
            showAlert("Error", "Gagal Memuat Dashboard Admin", Alert.AlertType.ERROR);
        }
    }

    private void loadUserDashboard(){
        loadAdminDashboard();
    }

    private void showAlert(String title, String message, Alert.AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}