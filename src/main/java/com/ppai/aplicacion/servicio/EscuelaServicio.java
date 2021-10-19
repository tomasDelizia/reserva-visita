package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.negocio.Escuela;
import java.util.List;

public interface EscuelaServicio {
    List<Escuela> listarEscuelas();

    Escuela encontrarPorNombre(String nombreEscuela);

    String[] buscarEscuelas();
}
