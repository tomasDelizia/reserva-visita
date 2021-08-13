package com.ppai.aplicacion.interfaz;

import com.ppai.aplicacion.config.StageManager;
import com.ppai.aplicacion.controlador.ControladorLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class PantallaLogin implements Initializable {
    private ControladorLogin controlador;
    private StageManager stageManager;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtConstrasenia;

    @FXML
    private Label lblErrorLogin;


    @Lazy
    @Autowired
    public void setStageManager(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @Autowired
    public void setControlador(ControladorLogin controlador) {
        this.controlador = controlador;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void tomarLogin(ActionEvent actionEvent) {
        // Método para tomar el el usuario y contraseña ingresados para luego ser verificados.
        String usuario = txtUsuario.getText();
        String contrasenia = txtConstrasenia.getText();
        controlador.login(usuario, contrasenia);
    }

    public void habilitarPantalla() {
        stageManager.switchScene(FxmlView.MAIN);
    }

    public void informarLoginFallido() {
        // Método que informa de un intento de ingreso al sistema fallido.
        lblErrorLogin.setVisible(true);
    }
}//end PantallaLogin