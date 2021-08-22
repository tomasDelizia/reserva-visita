package com.ppai.aplicacion.interfaz;

import com.ppai.aplicacion.controlador.ControladorLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PantallaLogin extends PantallaBase {
    private ControladorLogin controladorLogin;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtConstrasenia;

    @FXML
    private Label lblErrorLogin;


    @Autowired
    public void setControladorLogin(ControladorLogin controladorLogin) {
        this.controladorLogin = controladorLogin;
    }

    public void tomarLogin() {
        // Método para tomar el el usuario y contraseña ingresados para luego ser verificados.
        String usuario = txtUsuario.getText();
        String contrasenia = txtConstrasenia.getText();
        controladorLogin.login(usuario, contrasenia);
    }

    public void informarLoginFallido() {
        // Método que informa de un intento de ingreso al sistema fallido.
        lblErrorLogin.setVisible(true);
    }
}//end PantallaLogin