package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.negocio.Sede;
import java.util.List;

public interface SedeServicio {
    List<Sede> listarSedes();

    Sede encontrarPorNombre(String nombreSede);
}
