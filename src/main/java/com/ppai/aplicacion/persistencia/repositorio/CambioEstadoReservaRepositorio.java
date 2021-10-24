package com.ppai.aplicacion.persistencia.repositorio;

import com.ppai.aplicacion.negocio.visita_programada.modelo.CambioEstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambioEstadoReservaRepositorio extends JpaRepository<CambioEstadoReserva, Integer> {}