package com.ppai.aplicacion.repo;

import com.ppai.aplicacion.negocio.CambioEstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambioEstadoReservaRepositorio extends JpaRepository<CambioEstadoReserva, Integer> {}