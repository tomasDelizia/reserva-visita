package com.ppai.aplicacion.negocio.empleado.servicio.implementacion;

import com.ppai.aplicacion.negocio.empleado.modelo.AsignacionGuia;
import com.ppai.aplicacion.persistencia.repositorio.AsignacionGuiaRepositorio;
import com.ppai.aplicacion.negocio.empleado.servicio.AsignacionGuiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AsignacionGuiaServicioImpl implements AsignacionGuiaServicio {
    private final AsignacionGuiaRepositorio asignacionGuiaRepositorio;

    @Autowired
    public AsignacionGuiaServicioImpl(AsignacionGuiaRepositorio asignacionGuiaRepositorio) {
        this.asignacionGuiaRepositorio = asignacionGuiaRepositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AsignacionGuia> listarAsignacionesGuia() {
        return asignacionGuiaRepositorio.findAll();
    }
}
