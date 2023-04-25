package com.ppai.aplicacion.modulos.visita_programada.servicio;

import com.ppai.aplicacion.modulos.visita_programada.modelo.Escuela;
import java.util.List;

public interface EscuelaServicio {
    List<Escuela> listarEscuelas();

    Escuela encontrarPorNombre(String nombreEscuela);

    String[] buscarEscuelas();
}
