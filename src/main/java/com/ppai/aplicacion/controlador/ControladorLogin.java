package com.ppai.aplicacion.controlador;

import com.ppai.aplicacion.interfaz.PantallaLogin;
import com.ppai.aplicacion.interfaz.PantallaPrincipal;
import com.ppai.aplicacion.negocio.Sesion;
import com.ppai.aplicacion.servicio.SesionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControladorLogin {
    private PantallaLogin pantallaLogin;
    private PantallaPrincipal pantallaPrincipal;
    private ControladorReservaVisita controladorNuevaReservaVisita;
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

    @Autowired
    public void setControladorNuevaReservaVisita(ControladorReservaVisita controladorNuevaReservaVisita) {
        this.controladorNuevaReservaVisita = controladorNuevaReservaVisita;
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
        if (sesionActual != null) {
            controladorNuevaReservaVisita.setSesionActual(sesionActual);
            pantallaPrincipal.habilitarPantalla();
        }

        // Si no, se informa un mensaje de ingreso fallido.
        else
            pantallaLogin.informarLoginFallido();
    }
}//end ControladorLogin