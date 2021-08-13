package com.ppai.aplicacion.servicio.impl;

import com.ppai.aplicacion.negocio.AsignacionGuia;
import com.ppai.aplicacion.repo.AsignacionGuiaRepo;
import com.ppai.aplicacion.servicio.AsignacionGuiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AsignacionGuiaServicioImpl implements AsignacionGuiaServicio {
    private final AsignacionGuiaRepo asignacionGuiaRepo;

    @Autowired
    public AsignacionGuiaServicioImpl(AsignacionGuiaRepo asignacionGuiaRepo) {
        this.asignacionGuiaRepo = asignacionGuiaRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AsignacionGuia> listarAsignacionesGuia() {
        return asignacionGuiaRepo.findAll();
    }
}
