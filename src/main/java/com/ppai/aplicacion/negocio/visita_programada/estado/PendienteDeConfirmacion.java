package com.ppai.aplicacion.negocio.visita_programada.estado;

import com.ppai.aplicacion.negocio.visita_programada.modelo.CambioEstadoReservaVisita;
import com.ppai.aplicacion.negocio.visita_programada.modelo.Escuela;
import com.ppai.aplicacion.negocio.visita_programada.modelo.EstadoReservaVisita;
import com.ppai.aplicacion.negocio.visita_programada.modelo.ReservaVisita;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity(name = "PendienteDeConfirmacion")
@DiscriminatorValue("Pendiente de Confirmación")
public class PendienteDeConfirmacion extends EstadoReservaVisita {
    public PendienteDeConfirmacion() {
        idEstadoReserva = 1;
        nombre = "Pendiente de Confirmación";
    }

    @Override
    public void notificarEscuelas(List<Escuela> escuelasANotificar, ReservaVisita reservaVisita) {
        for (Escuela escuela:
             escuelasANotificar) {
            //escuela.notificar();
        }
        CambioEstadoReservaVisita cambioEstado = new CambioEstadoReservaVisita(new EscuelaNotificada());
        reservaVisita.addCambioEstado(cambioEstado);
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
    public boolean esPendienteDeConfirmacion() {
        return true;
    }
}
