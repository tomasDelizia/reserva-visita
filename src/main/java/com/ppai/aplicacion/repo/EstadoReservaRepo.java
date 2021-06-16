package com.ppai.aplicacion.repo;

import com.ppai.aplicacion.negocio.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoReservaRepo extends JpaRepository<EstadoReserva, Integer> {}