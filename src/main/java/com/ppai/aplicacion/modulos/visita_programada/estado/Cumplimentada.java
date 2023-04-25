package com.ppai.aplicacion.modulos.visita_programada.estado;

import com.ppai.aplicacion.modulos.visita_programada.modelo.EstadoReservaVisita;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Cumplimentada")
@DiscriminatorValue("Cumplimentada")
public class Cumplimentada extends EstadoReservaVisita {
    public Cumplimentada() {
        idEstadoReserva = 6;
        nombre = "Cumplimentada";
    }

    @Override
    public boolean esCumplimentada() {
        return true;
    }
}
