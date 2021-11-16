package com.ppai.aplicacion.negocio.visita_programada.estrategia;

import com.ppai.aplicacion.negocio.exposicion.modelo.Exposicion;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class EstrategiaVisitaPorExposicion implements EstrategiaCalculoDuracionReserva {
    @Override
    public LocalTime calcularDuracionEstimadaVisita(List<Exposicion> listaExposiciones) {
        // Se inicializan los contadores de horas, minutos y segundos totales.
        int horasTotales = 0;
        int minutosTotales = 0;
        int segundosTotales = 0;
        // Mientras haya exposiciones, obtenemos sus duraciones.
        for (Exposicion exposicion:
                listaExposiciones) {
            // Para el tipo de visita 'Por Exposición', se suman las duraciones resumidas.
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
