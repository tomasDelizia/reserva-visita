package com.ppai.aplicacion.negocio;

import com.ppai.aplicacion.negocio.usuario.servicio.SesionServicio;
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
