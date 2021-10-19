package com.ppai.aplicacion.servicio.implementacion;

import com.ppai.aplicacion.negocio.Exposicion;
import com.ppai.aplicacion.servicio.EstrategiaCalculoDuracionReserva;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class EstrategiaVisitaPorExposicion implements EstrategiaCalculoDuracionReserva {
    /**
     * Método que calcula la duración estimada de una visita por exposición para las exposiciones seleccionadas.
     * @param listaExposiciones las exposiciones que serán parte de la visita guiada.
     * @return la duración estimada de la visita en un objeto LocalTime.
     */
    @Override
    public LocalTime calcularDuracionEstimadaVisita(List<Exposicion> listaExposiciones) {
        // Se inicializan los contadores de horas, minutos y segundos totales.
        int horasTotales = 0;
        int minutosTotales = 0;
        int segundosTotales = 0;
        // Mientras haya exposiciones, obtenemos sus duraciones.
        for (Exposicion exposicion:
                listaExposiciones) {
            LocalTime duracionExposicion = exposicion.calcularDuracionExtendida();
            // Añadimos la duración (horas, minutos y segundos) de cada exposición a los contadores.
            horasTotales += duracionExposicion.getHour();
            minutosTotales += duracionExposicion.getMinute();
            segundosTotales += duracionExposicion.getSecond();
        }
        // Creamos un objeto duración para obtener la duración total sumando los 3 contadores.
        Duration duracionTotal = Duration.
                ofHours(horasTotales).
                plusMinutes(minutosTotales).
                plusSeconds(segundosTotales);
        // Retornamos el objeto duración convertido apropiadamente al tipo LocalTime.
        return LocalTime.of(
                duracionTotal.toHoursPart(), duracionTotal.toMinutesPart(), duracionTotal.toSecondsPart());
        }
}
