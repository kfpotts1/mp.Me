package Rename;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by kennypotts on 3/23/17.
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("rename.fxml"));
        primaryStage.setTitle("mpMe");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }
}
