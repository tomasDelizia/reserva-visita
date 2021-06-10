package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.negocio.TipoVisita;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITipoDeVisitaService {
    List<TipoVisita> findAll();
}
