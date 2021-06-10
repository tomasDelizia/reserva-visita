package com.ppai.aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringApp extends Application {
    public static ConfigurableApplicationContext applicationContext;
    public static Parent root;
    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        applicationContext = SpringApplication.run(SpringApp.class);
        FXMLLoader loader = new FXMLLoader(SpringApp.class.getResource("/reservaVisita.fxml"));
        loader.setControllerFactory(applicationContext::getBean);   // Escanea toda la aplicación y detecta dependecias
        Scene scene = new Scene(loader.load(), 640, 360, false, SceneAntialiasing.BALANCED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
