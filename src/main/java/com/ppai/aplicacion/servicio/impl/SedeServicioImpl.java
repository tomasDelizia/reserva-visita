package com.ppai.aplicacion.servicio.impl;

import com.ppai.aplicacion.negocio.Sede;
import com.ppai.aplicacion.repo.SedeRepositorio;
import com.ppai.aplicacion.servicio.SedeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SedeServicioImpl implements SedeServicio {
    private final SedeRepositorio sedeRepositorio;

    @Autowired
    public SedeServicioImpl(SedeRepositorio sedeRepositorio) {
        this.sedeRepositorio = sedeRepositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sede> listarSedes() {
        return sedeRepositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Sede encontrarPorNombre(String nombreSede) {
        return sedeRepositorio.findByNombre(nombreSede);
    }
}
