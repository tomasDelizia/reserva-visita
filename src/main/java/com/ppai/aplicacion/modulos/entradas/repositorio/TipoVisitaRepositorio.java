package com.ppai.aplicacion.modulos.entradas.repositorio;

import com.ppai.aplicacion.modulos.entradas.modelo.TipoVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVisitaRepositorio extends JpaRepository<TipoVisita, Integer> {
    TipoVisita findByNombre(String nombreTipoVisita);
}