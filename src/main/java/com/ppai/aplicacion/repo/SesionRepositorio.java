package com.ppai.aplicacion.repo;

import com.ppai.aplicacion.negocio.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface SesionRepositorio extends JpaRepository<Sesion, LocalDateTime> {}