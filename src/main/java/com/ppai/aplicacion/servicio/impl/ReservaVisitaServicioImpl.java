package com.ppai.aplicacion.servicio.impl;

import com.ppai.aplicacion.negocio.ReservaVisita;
import com.ppai.aplicacion.repo.ReservaVisitaRepo;
import com.ppai.aplicacion.servicio.ReservaVisitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaVisitaServicioImpl implements ReservaVisitaServicio {
    private final ReservaVisitaRepo reservaVisitaRepo;

    @Autowired
    public ReservaVisitaServicioImpl(ReservaVisitaRepo reservaVisitaRepo) {
        this.reservaVisitaRepo = reservaVisitaRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaVisita> listarReservasVisita() {
        return reservaVisitaRepo.findAll();
    }

    @Override
    @Transactional
    public void guardarReservaVisita(ReservaVisita reservaVisita) {
        reservaVisitaRepo.save(reservaVisita);
    }
}
