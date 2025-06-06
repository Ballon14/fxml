import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class AdminDashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private void initialize(){
        System.out.println("Admin Dashboard loaded");
    }

    public void handleLogout(){
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Konfirmasi Logout");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Apakah Anda yakin ingin keluar dari sistem?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/login.fxml"));
                Parent root =  loader.load();

                Stage stage = (Stage) welcomeLabel.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Logout - Sistem Informasi XYZ");

                new Thread(() ->{
                    try{
                        Thread.sleep(3000);
                        javafx.application.Platform.runLater(() -> redirectToLogin());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Gagal memuat logout", Alert.AlertType.ERROR);
            }
        }
    }

    public void showRegisteredUsers(){
        StringBuilder userList = new StringBuilder();
        userList.append("Daftar Pengguna Terdaftar:\n\n");

        if (RegisterController.getRegisteredUsers().isEmpty()){
            userList.append("Belum ada pengguna yang terdaftar.");
        } else {
            int count = 1;
            for (RegisterController.User user : RegisterController.getRegisteredUsers()){
                userList.append(count).append(". ").append("Username: ").append(user.getUsername()).append("\n  Nama: ").append(user.getFullName()).append("\n  NIM: ").append(user.getNim()).append("\n\n");
                count++;
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Data Pengguna");
        alert.setHeaderText("Sistem Informasi XYZ");
        alert.setContentText(userList.toString());
        alert.showAndWait();
    }

    public void showSystemInfo(){
        String systemInfo = "Sistem Informasi XYZ\n\n" + "Version :  1.0" + "Developer: Iqbal" + "Tahun : 2025\n\n" + "Fitur:\n" +  "- Login/Registrasi User\n" + "- Dashboard Admin\n" + "- Manajemen Data Pengguna\n" + "- Sistem Secure";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informasi Sistem");
        alert.setHeaderText("About App");
        alert.setContentText(systemInfo);
        alert.showAndWait();
    }

    private void redirectToLogin(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("user/login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login - Sistem Informasi XYZ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}