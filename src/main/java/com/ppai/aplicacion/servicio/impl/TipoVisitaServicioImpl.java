package com.ppai.aplicacion.servicio.impl;

import com.ppai.aplicacion.negocio.TipoVisita;
import com.ppai.aplicacion.repo.TipoVisitaRepo;
import com.ppai.aplicacion.servicio.TipoVisitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoVisitaServicioImpl implements TipoVisitaServicio {
    private final TipoVisitaRepo tipoVisitaRepo;

    @Autowired
    public TipoVisitaServicioImpl(TipoVisitaRepo tipoVisitaRepo) {
        this.tipoVisitaRepo = tipoVisitaRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoVisita> listarTiposVisita() {
        return tipoVisitaRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public TipoVisita encontrarPorNombre(String nombreTipoVisita) {
        return tipoVisitaRepo.findByNombre(nombreTipoVisita);
    }
}
