package com.ppai.reservavisita;

// Clase que inicializa la aplicación JavaFX

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.ppai.reservavisita.ReservaVisitaFX.StageReadyEvent;
import java.io.IOException;

@Component  // Permite a Spring utilizar inyección de dependencia
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Value("classpath:/reservaVisita.fxml")
    private Resource reservaResource;
    private String applicationTitle;
    //private ApplicationContext applicationContext;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle, ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        //this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(reservaResource.getURL());
            //fxmlLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass)); // De dónde tomamos los controladores javaFX
            Parent parent = fxmlLoader.load();

            Stage stage = event.getStage(); // Escena lista para inicializar la IU
            stage.setTitle(applicationTitle);
            stage.setScene(new Scene(parent, 640, 360));
            //stage.setMaximized(true);    // Tamaño de ventana completo
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException();   // Para no complicar el código
        }
    }
}
