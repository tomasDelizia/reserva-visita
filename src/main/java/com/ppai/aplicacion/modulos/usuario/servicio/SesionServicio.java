package com.ppai.aplicacion.modulos.usuario.servicio;

import com.ppai.aplicacion.modulos.usuario.modelo.Sesion;
import com.ppai.aplicacion.modulos.empleado.modelo.Empleado;

public interface SesionServicio {

    void guardarSesion(Sesion sesion);

    /**
     * Método que devuelve el empleado asociado al usuario con la sesión abierta.
     * @return el empleado asociado al usuario de la sesión.
     */
    Empleado getEmpleadoEnSesion();

    /**
     * Método que verifica que el usuario y contraseña pasados por parámetro sean válidos
     * y que además el usuario ingresado sea un Responsable de Visitas.
     * @param nombreUsuario nombre de usuario ingresado.
     * @param contrasenia contraseña ingresada.
     * @return una sesión con el usuario logueado.
     */
    Sesion iniciarSesion(String nombreUsuario, String contrasenia);

    /**
     * Método que cierra la sesión abierta, guardándo sus datos previamente en la base de datos.
     */
    void cerrarSesion();
}
