package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.negocio.Sesion;
import java.util.List;

public interface SesionServicio {
    List<Sesion> listarSesiones();

    void guardarSesion(Sesion sesion);
}
