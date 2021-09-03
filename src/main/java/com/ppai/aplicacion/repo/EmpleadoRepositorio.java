package com.ppai.aplicacion.repo;

import com.ppai.aplicacion.negocio.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer> {
    Empleado findByNombreAndApellido(String nombre, String apellido);
}
