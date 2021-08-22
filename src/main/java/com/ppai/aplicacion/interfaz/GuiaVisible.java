package com.ppai.aplicacion.interfaz;


/**
 * Clase que representa los datos generales de un guía para ser mostrado.
 */
public class GuiaVisible {
    private final String nombre, apellido;


    public GuiaVisible(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}
