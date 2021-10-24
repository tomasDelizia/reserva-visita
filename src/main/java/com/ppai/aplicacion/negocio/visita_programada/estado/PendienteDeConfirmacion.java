package com.ppai.aplicacion.negocio.visita_programada.estado;

import com.ppai.aplicacion.negocio.empleado.modelo.AsignacionGuia;
import com.ppai.aplicacion.negocio.empleado.modelo.Empleado;
import com.ppai.aplicacion.negocio.exposicion.modelo.Exposicion;
import com.ppai.aplicacion.negocio.sede.modelo.Sede;
import com.ppai.aplicacion.negocio.visita_programada.modelo.CambioEstadoReservaVisita;
import com.ppai.aplicacion.negocio.visita_programada.modelo.Escuela;
import com.ppai.aplicacion.negocio.visita_programada.modelo.EstadoReservaVisita;
import com.ppai.aplicacion.negocio.visita_programada.modelo.ReservaVisita;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity(name = "PendienteDeConfirmacion")
@DiscriminatorValue("Pendiente de Confirmación")
public class PendienteDeConfirmacion extends EstadoReservaVisita {
    public PendienteDeConfirmacion() {
        idEstadoReserva = 1;
        nombre = "Pendiente de Confirmación";
    }

    @Override
    public void crearReservaVisita(int numeroReserva,
                                   int cantidadAlumnos,
                                   LocalDateTime fechaYHoraCreacion,
                                   LocalDateTime fechaYHoraReserva,
                                   LocalTime duracionEstimada,
                                   Escuela escuela,
                                   Sede sede,
                                   Empleado empleadoCreo,
                                   List<Exposicion> exposicionesAVisitar,
                                   List<Empleado> guiasSeleccionados,
                                   ReservaVisita reservaVisita) {
        reservaVisita.setNumeroReserva(numeroReserva);
        reservaVisita.setCantidadAlumnos(cantidadAlumnos);
        reservaVisita.setFechaYHoraCreacion(fechaYHoraCreacion);
        reservaVisita.setFechaYHoraReserva(fechaYHoraReserva);
        reservaVisita.setDuracionEstimada(duracionEstimada);
        reservaVisita.setEscuela(escuela);
        reservaVisita.setSede(sede);
        reservaVisita.setEmpleadoCreo(empleadoCreo);
        reservaVisita.setExposicion(exposicionesAVisitar);

        LocalDateTime fechaYHoraFinReserva = reservaVisita.getFechaYHoraFinReserva();

        for (Empleado guia :
                guiasSeleccionados) {
            AsignacionGuia asignacionGuia = new AsignacionGuia(guia, fechaYHoraReserva, fechaYHoraFinReserva);
            reservaVisita.addAsignacionGuia(asignacionGuia);
        }

        CambioEstadoReservaVisita cambioEstado = new CambioEstadoReservaVisita(this, fechaYHoraCreacion);
        reservaVisita.addCambioEstado(cambioEstado);
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
