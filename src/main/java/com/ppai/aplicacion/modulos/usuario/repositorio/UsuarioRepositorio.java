package com.ppai.aplicacion.modulos.usuario.repositorio;

import com.ppai.aplicacion.modulos.usuario.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {}