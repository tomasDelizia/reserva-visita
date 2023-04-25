package com.ppai.aplicacion.modulos.visita_programada.repositorio;

import com.ppai.aplicacion.modulos.visita_programada.modelo.CambioEstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambioEstadoReservaRepositorio extends JpaRepository<CambioEstadoReserva, Integer> {}