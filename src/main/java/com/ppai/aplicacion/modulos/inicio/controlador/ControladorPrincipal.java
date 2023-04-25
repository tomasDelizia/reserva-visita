package com.ppai.aplicacion.modulos.inicio.controlador;

import com.ppai.aplicacion.modulos.usuario.servicio.SesionServicio;
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
