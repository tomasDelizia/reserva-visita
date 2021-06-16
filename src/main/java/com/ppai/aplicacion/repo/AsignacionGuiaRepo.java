package com.ppai.aplicacion.repo;

import com.ppai.aplicacion.negocio.AsignacionGuia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignacionGuiaRepo extends JpaRepository<AsignacionGuia, Integer> {}
