package com.ppai.aplicacion.repo;

import com.ppai.aplicacion.negocio.TipoVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVisitaRepo extends JpaRepository<TipoVisita, Integer> {}