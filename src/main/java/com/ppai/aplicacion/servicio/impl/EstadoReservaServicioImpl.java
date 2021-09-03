package com.ppai.aplicacion.servicio.impl;

import com.ppai.aplicacion.negocio.EstadoReserva;
import com.ppai.aplicacion.repo.EstadoReservaRepositorio;
import com.ppai.aplicacion.servicio.EstadoReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EstadoReservaServicioImpl implements EstadoReservaServicio {
    private final EstadoReservaRepositorio estadoReservaRepositorio;

    @Autowired
    public EstadoReservaServicioImpl(EstadoReservaRepositorio estadoReservaRepositorio) {
        this.estadoReservaRepositorio = estadoReservaRepositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoReserva> listarEstadosReserva() {
        return estadoReservaRepositorio.findAll();
    }

    /**
     * @inheritDoc
     * Implementación del método buscarEstadoPendienteDeConfirmacion().
     */
    @Override
    public EstadoReserva buscarEstadoPendienteDeConfirmacion() {
        List<EstadoReserva> estadosDeReserva = listarEstadosReserva();
        for (EstadoReserva estadoReserva:
                estadosDeReserva) {
            if (estadoReserva.esPendienteDeConfirmacion()) {
                return estadoReserva;
            }
        }
        return null;
    }
}
