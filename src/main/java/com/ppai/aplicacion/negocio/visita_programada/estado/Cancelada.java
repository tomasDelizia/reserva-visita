package com.ppai.aplicacion.negocio.visita_programada.estado;

import com.ppai.aplicacion.negocio.visita_programada.modelo.EstadoReservaVisita;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Cancelada")
@DiscriminatorValue("Cancelada")
public class Cancelada extends EstadoReservaVisita {
    public Cancelada() {
        idEstadoReserva = 3;
        nombre = "Cancelada";
    }

    @Override
    public boolean esCancelada() {
        return true;
    }
}
