package com.ppai.aplicacion.persistencia.repositorio;

import com.ppai.aplicacion.negocio.empleado.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer> {
}
