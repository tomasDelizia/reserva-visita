package com.ppai.aplicacion.persistencia.repositorio;

import com.ppai.aplicacion.negocio.sede.modelo.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepositorio extends JpaRepository<Sede, Integer> {
    Sede findByNombre(String nombreSede);
}