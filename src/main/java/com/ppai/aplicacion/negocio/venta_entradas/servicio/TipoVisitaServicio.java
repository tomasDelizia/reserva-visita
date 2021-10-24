package com.ppai.aplicacion.negocio.venta_entradas.servicio;

import com.ppai.aplicacion.negocio.venta_entradas.modelo.TipoVisita;
import java.util.List;

public interface TipoVisitaServicio {
    List<TipoVisita> listarTiposVisita();

    TipoVisita encontrarPorNombre(String nombreTipoVisita);

    String[] buscarTiposVisita();
}
