import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/user/login.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            primaryStage.setTitle("Login - Sistem Informasi XYZ");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();

            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error loading appliaction" + e.getMessage());
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}