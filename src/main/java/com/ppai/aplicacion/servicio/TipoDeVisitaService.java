package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.persistencia.TipoDeVisita;
import com.ppai.aplicacion.repo.TipoDeVisitaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDeVisitaService implements ITipoDeVisitaService {

    @Autowired
    private TipoDeVisitaRepo tipoDeVisitaRepo;

    @Override
    public List<TipoDeVisita> findAll() {
        return (List<TipoDeVisita>) tipoDeVisitaRepo.findAll();
    }
}
