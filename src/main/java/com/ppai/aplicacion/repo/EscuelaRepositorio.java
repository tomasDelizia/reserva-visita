package com.ppai.aplicacion.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ppai.aplicacion.negocio.Escuela;
import org.springframework.stereotype.Repository;

@Repository
public interface EscuelaRepositorio extends JpaRepository<Escuela, Integer> {
    Escuela findByNombre(String nombreEscuela);
}