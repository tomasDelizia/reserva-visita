package com.ppai.aplicacion.modulos.empleado.repositorio;

import com.ppai.aplicacion.modulos.empleado.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer> {
}
