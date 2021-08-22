package com.ppai.aplicacion.interfaz;

/**
 * Clase que representa los datos generales de una exposición para ser mostrada.
 */
public class ExposicionVisible {
    private final String nombre, publicoDestino, horaApertura, horaCierre;

    public ExposicionVisible(String nombre, String publicoDestino, String horaApertura, String horaCierre) {
        this.nombre = nombre;
        this.publicoDestino = publicoDestino;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPublicoDestino() {
        return publicoDestino;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }
}
