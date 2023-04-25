package com.ppai.aplicacion.modulos.sede.repositorio;

import com.ppai.aplicacion.modulos.sede.modelo.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepositorio extends JpaRepository<Sede, Integer> {
    Sede findByNombre(String nombreSede);
}