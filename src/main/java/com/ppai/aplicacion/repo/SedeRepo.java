package com.ppai.aplicacion.repo;

import com.ppai.aplicacion.negocio.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepo extends JpaRepository<Sede, Integer> {
    Sede findByNombre(String nombreSede);
}