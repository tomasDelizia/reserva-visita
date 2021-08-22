package com.ppai.aplicacion.servicio.impl;

import com.ppai.aplicacion.negocio.Escuela;
import com.ppai.aplicacion.repo.EscuelaRepo;
import com.ppai.aplicacion.servicio.EscuelaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EscuelaServicioImpl implements EscuelaServicio {
    public final EscuelaRepo escuelaRepo;

    @Autowired
    public EscuelaServicioImpl(EscuelaRepo escuelaRepo) {
        this.escuelaRepo = escuelaRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Escuela> listarEscuelas() {
        return escuelaRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Escuela encontrarPorNombre(String nombreEscuela) {
        return escuelaRepo.findByNombre(nombreEscuela);
    }
}
