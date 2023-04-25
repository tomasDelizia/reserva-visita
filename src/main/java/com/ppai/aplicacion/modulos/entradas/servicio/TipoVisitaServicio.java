package com.ppai.aplicacion.modulos.entradas.servicio;

import com.ppai.aplicacion.modulos.entradas.modelo.TipoVisita;

import java.util.List;

public interface TipoVisitaServicio {
    List<TipoVisita> listarTiposVisita();

    TipoVisita encontrarPorNombre(String nombreTipoVisita);

    String[] buscarTiposVisita();
}
