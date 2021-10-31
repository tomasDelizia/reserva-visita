package com.ppai.aplicacion.presentacion.presentacion_usuarios;

import com.ppai.aplicacion.negocio.usuario.controlador.ControladorLogin;
import com.ppai.aplicacion.presentacion.Pantalla;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PantallaLogin extends Pantalla {
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

    /**
     * Método para tomar el el usuario y contraseña ingresados para luego ser verificados.
     */
    public void tomarLogin() {
        controladorLogin.login(txtUsuario.getText(), txtConstrasenia.getText());
    }

    /**
     * Método que informa de un intento de ingreso al sistema fallido.
     */
    public void informarLoginFallido() {
        lblErrorLogin.setVisible(true);
    }
}//end PantallaLogin