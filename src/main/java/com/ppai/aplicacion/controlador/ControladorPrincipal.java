package com.ppai.aplicacion.controlador;

import com.ppai.aplicacion.interfaz.PantallaLogin;
import com.ppai.aplicacion.interfaz.PantallaPrincipal;
import com.ppai.aplicacion.servicio.SesionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControladorPrincipal {
    private final SesionServicio sesionServicio;

    @Autowired
    public ControladorPrincipal(SesionServicio sesionServicio) {
        this.sesionServicio = sesionServicio;
    }

    public void cerrarSesion() {
        sesionServicio.cerrarSesion();
    }
}
