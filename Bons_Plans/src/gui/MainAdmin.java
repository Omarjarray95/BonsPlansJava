package gui;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAdmin extends Application 
{
     @Override
    public void start(Stage primaryStage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("BackendAdminAcceuil.fxml"));
        Scene scene=new Scene(root);
        primaryStage.setTitle("Bons Plans - Admin");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
    public static void main(String[] args) 
    {
        launch(args);
    }

   
}