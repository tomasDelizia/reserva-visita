package com.ppai.aplicacion.repo;

import com.ppai.aplicacion.negocio.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepo extends JpaRepository<Empleado, Integer> {}
