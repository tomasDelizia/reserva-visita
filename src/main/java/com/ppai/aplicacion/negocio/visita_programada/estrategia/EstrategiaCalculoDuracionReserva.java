package com.ppai.aplicacion.negocio.visita_programada.estrategia;

import com.ppai.aplicacion.negocio.exposicion.modelo.Exposicion;
import java.time.LocalTime;
import java.util.List;

public interface EstrategiaCalculoDuracionReserva {
    /**
     * Método que calcula la duración estimada de una visita por exposición para las exposiciones seleccionadas.
     * @param listaExposiciones las exposiciones que serán parte de la visita guiada.
     * @return la duración estimada de la visita en un objeto LocalTime.
     */
    LocalTime calcularDuracionEstimadaVisita(List<Exposicion> listaExposiciones);
}
