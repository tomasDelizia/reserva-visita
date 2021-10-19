package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.negocio.TipoVisita;
import java.util.List;

public interface TipoVisitaServicio {
    List<TipoVisita> listarTiposVisita();

    TipoVisita encontrarPorNombre(String nombreTipoVisita);

    String[] buscarTiposVisita();
}
