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
    public static ConfigurableApplicationContext springContext;
    public static Parent root;
    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        springContext = SpringApplication.run(SpringApp.class);
        FXMLLoader loader = new FXMLLoader(SpringApp.class.getResource("/fxml/ReservaVisita.fxml"));
        loader.setControllerFactory(springContext::getBean);   // Escanea toda la aplicación y detecta dependecias

        Scene scene = new Scene(loader.load(), 640, 360, false, SceneAntialiasing.BALANCED);
        // scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
