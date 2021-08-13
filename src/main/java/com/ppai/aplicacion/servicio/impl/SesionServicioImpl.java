package com.ppai.aplicacion.servicio.impl;

import com.ppai.aplicacion.negocio.Sesion;
import com.ppai.aplicacion.repo.SesionRepo;
import com.ppai.aplicacion.servicio.SesionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SesionServicioImpl implements SesionServicio {
    private final SesionRepo sesionRepo;

    @Autowired
    public SesionServicioImpl(SesionRepo sesionRepo) {
        this.sesionRepo = sesionRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sesion> listarSesiones() {
        return sesionRepo.findAll();
    }

    @Override
    @Transactional
    public void guardarSesion(Sesion sesion) {
        sesionRepo.save(sesion);
    }

}
