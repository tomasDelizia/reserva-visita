package com.ppai.aplicacion.persistencia.repositorio;

import com.ppai.aplicacion.negocio.venta_entradas.modelo.TipoVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVisitaRepositorio extends JpaRepository<TipoVisita, Integer> {
    TipoVisita findByNombre(String nombreTipoVisita);
}