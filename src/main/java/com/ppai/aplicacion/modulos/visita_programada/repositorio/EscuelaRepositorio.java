package com.ppai.aplicacion.modulos.visita_programada.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ppai.aplicacion.modulos.visita_programada.modelo.Escuela;
import org.springframework.stereotype.Repository;

@Repository
public interface EscuelaRepositorio extends JpaRepository<Escuela, Integer> {
    Escuela findByNombre(String nombreEscuela);
}