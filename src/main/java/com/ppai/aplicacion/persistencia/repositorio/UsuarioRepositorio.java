package com.ppai.aplicacion.persistencia.repositorio;

import com.ppai.aplicacion.negocio.usuario.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {}