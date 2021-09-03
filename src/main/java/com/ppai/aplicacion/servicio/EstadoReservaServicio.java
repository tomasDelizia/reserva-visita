package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.negocio.EstadoReserva;
import java.util.List;

public interface EstadoReservaServicio {
    List<EstadoReserva> listarEstadosReserva();

    /**
     * Método que busca el estado "Pendiente de Confirmación" de entre todos los estados existentes.
     * return el estado "Pendiente de Confirmación".
     */
    EstadoReserva buscarEstadoPendienteDeConfirmacion();
}
