package com.ppai.aplicacion;

import com.ppai.aplicacion.config.StageManager;
import com.ppai.aplicacion.presentacion.FxmlView;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application {
    protected ConfigurableApplicationContext springContext;
    protected StageManager stageManager;


    public static void main(final String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {
        springContext = springBootApplicationContext();
    }

    @Override
    public void stop() {
        springContext.close();
    }

    @Override
    public void start(Stage stage) {
        stageManager = springContext.getBean(StageManager.class, stage);
        displayInitialScene();
    }

    private void displayInitialScene() {
        // Empieza con el login
        stageManager.switchScene((FxmlView.LOGIN));
    }

    private ConfigurableApplicationContext springBootApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        String[] args = getParameters().getRaw().toArray(String[]::new);
        return builder.run(args);
    }
}
