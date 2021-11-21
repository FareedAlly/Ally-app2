package baseline;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryManagementApplication extends javafx.application.Application
{

    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InventoryManager.fxml"));

        primaryStage.setTitle("To-do list");
        primaryStage.setScene(new Scene(root, 600, 500));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



}
