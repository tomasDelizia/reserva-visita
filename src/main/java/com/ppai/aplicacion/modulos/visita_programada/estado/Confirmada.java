package com.ppai.aplicacion.modulos.visita_programada.estado;

import com.ppai.aplicacion.modulos.visita_programada.modelo.CambioEstadoReservaVisita;
import com.ppai.aplicacion.modulos.visita_programada.modelo.EstadoReservaVisita;
import com.ppai.aplicacion.modulos.visita_programada.modelo.ReservaVisita;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Confirmada")
@DiscriminatorValue("Confirmada")
public class Confirmada extends EstadoReservaVisita {
    public Confirmada() {
        idEstadoReserva = 5;
        nombre = "Confirmada";
    }

    @Override
    public void cumplimentar(ReservaVisita reservaVisita) {
        CambioEstadoReservaVisita cambioEstado = new CambioEstadoReservaVisita(new Cumplimentada());
        reservaVisita.addCambioEstado(cambioEstado);
    }

    @Override
    public boolean esConfirmada() {
        return true;
    }
}
