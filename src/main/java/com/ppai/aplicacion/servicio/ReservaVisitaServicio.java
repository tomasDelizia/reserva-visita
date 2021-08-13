package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.negocio.ReservaVisita;
import java.util.List;

public interface ReservaVisitaServicio {
    List<ReservaVisita> listarReservasVisita();

    void guardarReservaVisita(ReservaVisita reservaVisita);
}
