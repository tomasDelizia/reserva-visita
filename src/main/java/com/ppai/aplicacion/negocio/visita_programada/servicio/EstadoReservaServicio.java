package com.ppai.aplicacion.negocio.visita_programada.servicio;

import com.ppai.aplicacion.negocio.visita_programada.modelo.EstadoReserva;
import java.util.List;

public interface EstadoReservaServicio {
    List<EstadoReserva> listarEstadosReserva();

    /**
     * Método que busca el estado "Pendiente de Confirmación" de entre todos los estados existentes.
     * @return el estado "Pendiente de Confirmación".
     */
    EstadoReserva buscarEstadoPendienteDeConfirmacion();
}
