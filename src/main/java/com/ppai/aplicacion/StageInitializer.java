package com.ppai.aplicacion;

// Clase que inicializa la escena JavaFX


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.ppai.aplicacion.NuevaReservaVisitaFXApplication.StageReadyEvent;
import java.io.IOException;

@Component  // Permite a Spring utilizar inyección de dependencia
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Value("classpath:/fxml/ReservaVisita.fxml")
    private Resource reservaResource;
    private final String applicationTitle;
    private ApplicationContext applicationContext;


    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle,
                            ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(reservaResource.getURL());
            // Se toman los controladores javaFX del contexto Spring de la aplicación
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent root = fxmlLoader.load();
            Stage stage = event.getStage(); // Escena lista para inicializar la IU
            stage.setTitle(applicationTitle);
            stage.setScene(new Scene(root, 810.0, 696.0));
            //stage.setMaximized(true);    // Tamaño de ventana completo
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
