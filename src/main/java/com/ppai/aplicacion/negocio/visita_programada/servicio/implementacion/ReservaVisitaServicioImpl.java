package com.ppai.aplicacion.negocio.visita_programada.servicio.implementacion;

import com.ppai.aplicacion.negocio.visita_programada.modelo.ReservaVisita;
import com.ppai.aplicacion.persistencia.repositorio.ReservaVisitaRepositorio;
import com.ppai.aplicacion.negocio.visita_programada.servicio.ReservaVisitaServicio;
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
