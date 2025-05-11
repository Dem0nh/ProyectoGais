package ui;

import database.DatabaseSetup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        DatabaseSetup.crearTablas();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/loginController.fxml"));
        Pane root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LearnEG 📘");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
