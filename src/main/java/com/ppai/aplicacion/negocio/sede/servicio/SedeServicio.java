package com.ppai.aplicacion.negocio.sede.servicio;

import com.ppai.aplicacion.negocio.sede.modelo.Sede;
import java.util.List;

public interface SedeServicio {
    List<Sede> listarSedes();

    Sede encontrarPorNombre(String nombreSede);

    String[] buscarSedes();
}
