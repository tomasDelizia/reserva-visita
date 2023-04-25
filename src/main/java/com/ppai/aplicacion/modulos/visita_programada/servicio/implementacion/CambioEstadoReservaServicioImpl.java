package com.ppai.aplicacion.modulos.visita_programada.servicio.implementacion;

import com.ppai.aplicacion.modulos.visita_programada.modelo.CambioEstadoReserva;
import com.ppai.aplicacion.modulos.visita_programada.repositorio.CambioEstadoReservaRepositorio;
import com.ppai.aplicacion.modulos.visita_programada.servicio.CambioEstadoReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CambioEstadoReservaServicioImpl implements CambioEstadoReservaServicio {
    private final CambioEstadoReservaRepositorio cambioEstadoReservaRepositorio;

    @Autowired
    public CambioEstadoReservaServicioImpl(CambioEstadoReservaRepositorio cambioEstadoReservaRepositorio) {
        this.cambioEstadoReservaRepositorio = cambioEstadoReservaRepositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CambioEstadoReserva> listarCambiosEstadoReserva() {
        return cambioEstadoReservaRepositorio.findAll();
    }
}
