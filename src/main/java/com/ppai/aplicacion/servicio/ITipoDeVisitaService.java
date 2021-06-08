package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.persistencia.TipoDeVisita;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITipoDeVisitaService {
    List<TipoDeVisita> findAll();
}
