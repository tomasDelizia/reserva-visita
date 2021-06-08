package com.ppai.aplicacion.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ppai.aplicacion.persistencia.Escuela;

public interface EscuelaRepo extends JpaRepository<Escuela, Integer> {}