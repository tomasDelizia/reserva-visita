package com.ppai.aplicacion.servicio.impl;

import com.ppai.aplicacion.negocio.Sede;
import com.ppai.aplicacion.repo.SedeRepo;
import com.ppai.aplicacion.servicio.SedeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SedeServicioImpl implements SedeServicio {
    private final SedeRepo sedeRepo;

    @Autowired
    public SedeServicioImpl(SedeRepo sedeRepo) {
        this.sedeRepo = sedeRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sede> listarSedes() {
        return sedeRepo.findAll();
    }

}
