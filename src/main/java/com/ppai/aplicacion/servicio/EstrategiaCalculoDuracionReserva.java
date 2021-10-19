package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.negocio.Exposicion;
import java.time.LocalTime;
import java.util.List;

public interface EstrategiaCalculoDuracionReserva {
    LocalTime calcularDuracionEstimadaVisita(List<Exposicion> listaExposiciones);
}
