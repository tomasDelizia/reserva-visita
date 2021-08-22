package com.ppai.aplicacion.interfaz;

import com.ppai.aplicacion.config.StageManager;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class PantallaBase implements Initializable {
    protected StageManager stageManager;

    @Lazy
    @Autowired
    public void setStageManager(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}//end PantallaBase
