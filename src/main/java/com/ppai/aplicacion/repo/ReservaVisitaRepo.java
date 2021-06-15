package com.ppai.aplicacion.repo;

import com.ppai.aplicacion.negocio.ReservaVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaVisitaRepo extends JpaRepository<ReservaVisita, Integer> {}
