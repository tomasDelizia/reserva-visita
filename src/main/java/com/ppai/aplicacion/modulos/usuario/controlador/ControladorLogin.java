package com.ppai.aplicacion.modulos.usuario.controlador;

import com.ppai.aplicacion.modulos.usuario.presentacion.PantallaLogin;
import com.ppai.aplicacion.modulos.inicio.presentacion.PantallaPrincipal;
import com.ppai.aplicacion.modulos.usuario.modelo.Sesion;
import com.ppai.aplicacion.modulos.usuario.servicio.SesionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControladorLogin {
    private PantallaLogin pantallaLogin;
    private PantallaPrincipal pantallaPrincipal;
    private final SesionServicio sesionServicio;


    @Autowired
    public ControladorLogin(SesionServicio sesionServicio) {
        this.sesionServicio = sesionServicio;
    }

    @Autowired
    public void setPantallaLogin(PantallaLogin pantallaLogin) {
        this.pantallaLogin = pantallaLogin;
    }

    @Autowired
    public void setPantallaPrincipal(PantallaPrincipal pantallaPrincipal) {
        this.pantallaPrincipal = pantallaPrincipal;
    }

    /**
     * Método que verifica que el usuario y contraseña pasados por parámetro sean válidos
     * y que además el usuario ingresado sea un Responsable de Visitas.
     * @param nombreUsuario nombre de usuario ingresado
     * @param contrasenia contraseña ingresada
     */
    public void login(String nombreUsuario, String contrasenia) {
        Sesion sesionActual = sesionServicio.iniciarSesion(nombreUsuario, contrasenia);

        // Si se encontró el usuario correspondiente, se informa un mensaje de éxito en la pantalla.
        if (sesionActual != null)
            pantallaPrincipal.habilitarPantalla();

        // Si no, se informa un mensaje de ingreso fallido.
        else
            pantallaLogin.informarLoginFallido();
    }
}//end ControladorLogin