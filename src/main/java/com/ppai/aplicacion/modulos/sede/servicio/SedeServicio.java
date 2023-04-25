package com.ppai.aplicacion.modulos.sede.servicio;

import com.ppai.aplicacion.modulos.sede.modelo.Sede;

import java.util.List;

public interface SedeServicio {
    List<Sede> listarSedes();

    Sede encontrarPorNombre(String nombreSede);

    String[] buscarSedes();
}
