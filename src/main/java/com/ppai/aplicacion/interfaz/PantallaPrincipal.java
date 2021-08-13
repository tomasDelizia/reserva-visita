package com.ppai.aplicacion.interfaz;

import com.ppai.aplicacion.config.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class PantallaPrincipal implements Initializable {
    private StageManager stageManager;
    private PantallaReservaVisita pantallaReservaVisita;


    @Lazy
    @Autowired
    public void setStageManager(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @Autowired
    public void setPantallaReservaVisita(PantallaReservaVisita pantallaReservaVisita) {
        this.pantallaReservaVisita = pantallaReservaVisita;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void opcionNuevaReservaVisita(ActionEvent actionEvent) {
        pantallaReservaVisita.opcionNuevaReservaVisita();
    }

    public void salir(ActionEvent actionEvent) {
        stageManager.switchScene(FxmlView.LOGIN);
    }
}//end PantallaPrincipal