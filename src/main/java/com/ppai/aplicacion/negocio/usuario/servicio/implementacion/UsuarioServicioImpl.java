package com.ppai.aplicacion.negocio.usuario.servicio.implementacion;

import com.ppai.aplicacion.negocio.usuario.modelo.Usuario;
import com.ppai.aplicacion.persistencia.repositorio.UsuarioRepositorio;
import com.ppai.aplicacion.negocio.usuario.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }
}
