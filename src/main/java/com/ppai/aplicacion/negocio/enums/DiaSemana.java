package com.ppai.aplicacion.negocio.enums;

/**
 * Enumeración de los siete días de las semana.
 */
public enum DiaSemana {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO;

    public int getValue() {
        return ordinal() + 1;
    }
}
