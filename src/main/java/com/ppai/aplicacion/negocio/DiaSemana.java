package com.ppai.aplicacion.negocio;

public enum DiaSemana {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO;

    public int getValue() {
        return ordinal() + 1;
    }
}
