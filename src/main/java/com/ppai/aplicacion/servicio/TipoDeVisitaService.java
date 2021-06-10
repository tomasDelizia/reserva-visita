package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.negocio.TipoVisita;
import com.ppai.aplicacion.repo.TipoVisitaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDeVisitaService implements ITipoDeVisitaService {

    @Autowired
    private TipoVisitaRepo tipoVisitaRepo;

    @Override
    public List<TipoVisita> findAll() {
        return (List<TipoVisita>) tipoVisitaRepo.findAll();
    }
}
