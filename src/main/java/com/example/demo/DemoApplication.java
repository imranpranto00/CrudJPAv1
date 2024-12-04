package com.example.demo;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication extends Application {
    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
        context = builder.run();
    }

    @Override
    public void start(Stage primaryStage) {
        context.getBean(JavaFxApp.class).start(primaryStage);
    }

    @Override
    public void stop() {
        context.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
