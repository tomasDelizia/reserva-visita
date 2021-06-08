package com.ppai.aplicacion.repo;

import com.ppai.aplicacion.persistencia.TipoDeVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDeVisitaRepo extends JpaRepository<TipoDeVisita, Integer> {}