package com.example.demo;

import com.example.demo.controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JavaFxApp {

    private final MainController mainController;

    public JavaFxApp(MainController mainController) {
        this.mainController = mainController;
    }

    public void start(Stage primaryStage) {
        try {
            // Load FXML file and set the controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
            loader.setControllerFactory(param -> mainController);
            Parent root = loader.load();

            // Set up the scene and show the stage
            primaryStage.setTitle("JavaFX with Spring Boot Example");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
