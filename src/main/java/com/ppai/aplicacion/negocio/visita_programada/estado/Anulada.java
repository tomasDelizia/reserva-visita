package com.ppai.aplicacion.negocio.visita_programada.estado;

import com.ppai.aplicacion.negocio.visita_programada.modelo.EstadoReservaVisita;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Anulada")
@DiscriminatorValue("Anulada")
public class Anulada extends EstadoReservaVisita {
    public Anulada() {
        idEstadoReserva = 4;
        nombre = "Anulada";
    }

    @Override
    public boolean esAnulada() {
        return true;
    }
}