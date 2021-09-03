package com.ppai.aplicacion.servicio.impl;

import com.ppai.aplicacion.negocio.ReservaVisita;
import com.ppai.aplicacion.repo.ReservaVisitaRepositorio;
import com.ppai.aplicacion.servicio.ReservaVisitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ReservaVisitaServicioImpl implements ReservaVisitaServicio {
    private final ReservaVisitaRepositorio reservaVisitaRepositorio;

    @Autowired
    public ReservaVisitaServicioImpl(ReservaVisitaRepositorio reservaVisitaRepositorio) {
        this.reservaVisitaRepositorio = reservaVisitaRepositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaVisita> listarReservasVisita() {
        return reservaVisitaRepositorio.findAll();
    }

    @Override
    @Transactional
    public void guardarReservaVisita(ReservaVisita reservaVisita) {
        reservaVisitaRepositorio.save(reservaVisita);
    }

    /**
     * @inheritDoc
     * Implementación del método obtenerUltimoNroReserva().
     * @return el número de la última reserva registrada.
     */
    @Override
    public int getUltimoNroReserva() {
        int ultimoNumeroReserva = 0;
        List<ReservaVisita> reservasDeVisita = listarReservasVisita();
        for (ReservaVisita reservaVisita:
                reservasDeVisita) {
            if (reservaVisita.getNroReserva() > ultimoNumeroReserva)
                ultimoNumeroReserva = reservaVisita.getNroReserva();
        }
        return ultimoNumeroReserva;
    }
}
