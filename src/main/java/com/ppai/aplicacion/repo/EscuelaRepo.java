package com.ppai.aplicacion.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ppai.aplicacion.negocio.Escuela;
import org.springframework.stereotype.Repository;

@Repository
public interface EscuelaRepo extends JpaRepository<Escuela, Integer> {}