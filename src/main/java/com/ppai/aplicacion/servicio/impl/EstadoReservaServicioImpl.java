package com.ppai.aplicacion.servicio.impl;

import com.ppai.aplicacion.negocio.EstadoReserva;
import com.ppai.aplicacion.repo.EstadoReservaRepo;
import com.ppai.aplicacion.servicio.EstadoReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoReservaServicioImpl implements EstadoReservaServicio {
    private final EstadoReservaRepo estadoReservaRepo;

    @Autowired
    public EstadoReservaServicioImpl(EstadoReservaRepo estadoReservaRepo) {
        this.estadoReservaRepo = estadoReservaRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoReserva> listarEstadosReserva() {
        return estadoReservaRepo.findAll();
    }
}
