package com.ppai.aplicacion.servicio.impl;

import com.ppai.aplicacion.negocio.Usuario;
import com.ppai.aplicacion.repo.UsuarioRepo;
import com.ppai.aplicacion.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
    private final UsuarioRepo usuarioRepo;

    @Autowired
    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }
}
