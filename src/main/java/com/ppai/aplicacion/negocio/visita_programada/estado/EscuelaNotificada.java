package com.ppai.aplicacion.negocio.visita_programada.estado;

import com.ppai.aplicacion.negocio.visita_programada.modelo.CambioEstadoReservaVisita;
import com.ppai.aplicacion.negocio.visita_programada.modelo.EstadoReservaVisita;
import com.ppai.aplicacion.negocio.visita_programada.modelo.ReservaVisita;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "EscuelaNotificada")
@DiscriminatorValue("Escuela Notificada")
public class EscuelaNotificada extends EstadoReservaVisita {
    public EscuelaNotificada() {
        idEstadoReserva = 2;
        nombre = "Escuela Notificada";
    }

    @Override
    public void confirmar(ReservaVisita reservaVisita) {
        CambioEstadoReservaVisita cambioEstado = new CambioEstadoReservaVisita(new Confirmada());
        reservaVisita.addCambioEstado(cambioEstado);
    }

    @Override
    public void cancelar(ReservaVisita reservaVisita) {
        CambioEstadoReservaVisita cambioEstado = new CambioEstadoReservaVisita(new Cancelada());
        reservaVisita.addCambioEstado(cambioEstado);
    }

    @Override
    public void anular(ReservaVisita reservaVisita) {
        CambioEstadoReservaVisita cambioEstado = new CambioEstadoReservaVisita(new Anulada());
        reservaVisita.addCambioEstado(cambioEstado);
    }

    @Override
    public boolean esEscuelaNotificada() {
        return true;
    }
}
