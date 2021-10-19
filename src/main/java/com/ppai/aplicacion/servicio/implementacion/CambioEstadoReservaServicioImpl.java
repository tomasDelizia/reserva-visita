package com.ppai.aplicacion.servicio.implementacion;

import com.ppai.aplicacion.negocio.CambioEstadoReserva;
import com.ppai.aplicacion.repo.CambioEstadoReservaRepositorio;
import com.ppai.aplicacion.servicio.CambioEstadoReservaServicio;
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
