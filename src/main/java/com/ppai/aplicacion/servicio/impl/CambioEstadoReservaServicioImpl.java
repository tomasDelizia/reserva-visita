package com.ppai.aplicacion.servicio.impl;

import com.ppai.aplicacion.negocio.CambioEstadoReserva;
import com.ppai.aplicacion.repo.CambioEstadoReservaRepo;
import com.ppai.aplicacion.servicio.CambioEstadoReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CambioEstadoReservaServicioImpl implements CambioEstadoReservaServicio {
    private final CambioEstadoReservaRepo cambioEstadoReservaRepo;

    @Autowired
    public CambioEstadoReservaServicioImpl(CambioEstadoReservaRepo cambioEstadoReservaRepo) {
        this.cambioEstadoReservaRepo = cambioEstadoReservaRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CambioEstadoReserva> listarCambiosEstadoReserva() {
        return cambioEstadoReservaRepo.findAll();
    }
}
