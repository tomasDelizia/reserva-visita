package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.negocio.ReservaVisita;
import java.util.List;

public interface ReservaVisitaServicio {
    List<ReservaVisita> listarReservasVisita();

    void guardarReservaVisita(ReservaVisita reservaVisita);

    /**
     * Método que recorre todas las reservas existentes y obtiene el número de la última de ellas.
     * @return el número de la última reserva registrada.
     */
    int getUltimoNroReserva();
}
