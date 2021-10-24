package com.ppai.aplicacion.persistencia.repositorio;

import com.ppai.aplicacion.negocio.visita_programada.modelo.ReservaVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaVisitaRepositorio extends JpaRepository<ReservaVisita, Integer> {}
