package com.ppai.aplicacion.negocio.visita_programada.servicio.implementacion;

import com.ppai.aplicacion.negocio.visita_programada.modelo.EstadoReserva;
import com.ppai.aplicacion.persistencia.repositorio.EstadoReservaRepositorio;
import com.ppai.aplicacion.negocio.visita_programada.servicio.EstadoReservaServicio;
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
