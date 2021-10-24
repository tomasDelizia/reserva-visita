package com.ppai.aplicacion.persistencia.repositorio;

import com.ppai.aplicacion.negocio.empleado.modelo.AsignacionGuia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignacionGuiaRepositorio extends JpaRepository<AsignacionGuia, Integer> {}
