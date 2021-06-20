package com.ppai.aplicacion.interfaz;

import com.ppai.aplicacion.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class PantallaPrincipal {

    @FXML
    private Button btnRegistrarReservaVisita;

    @Autowired
    private StageManager stageManager;

    private Stage mainStage;

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCancel;

    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    @FXML
    public void loginEvent(ActionEvent event) throws IOException {

        //loginService.loginService();

        Parent root = stageManager.getLoader("reservaVisita.fxml").load();

        Scene scene = new Scene(root);

        mainStage = stageManager.getMainStage();

        mainStage.setTitle("Reserva de Visita");

        mainStage.setScene(scene);

        mainStage.show();

    }

}
