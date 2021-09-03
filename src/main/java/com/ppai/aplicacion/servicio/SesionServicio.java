package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.negocio.Sesion;
import java.util.List;

public interface SesionServicio {
    List<Sesion> listarSesiones();

    void guardarSesion(Sesion sesion);

    /**
     * Método que verifica que el usuario y contraseña pasados por parámetro sean válidos
     * y que además el usuario ingresado sea un Responsable de Visitas.
     * @param nombreUsuario nombre de usuario ingresado.
     * @param contrasenia contraseña ingresada.
     * @return una sesión con el usuario logueado.
     */
    Sesion iniciarSesion(String nombreUsuario, String contrasenia);
}
