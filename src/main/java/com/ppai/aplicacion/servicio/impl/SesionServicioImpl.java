package com.ppai.aplicacion.servicio.impl;

import com.ppai.aplicacion.negocio.Sesion;
import com.ppai.aplicacion.negocio.Usuario;
import com.ppai.aplicacion.repo.SesionRepositorio;
import com.ppai.aplicacion.servicio.SesionServicio;
import com.ppai.aplicacion.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SesionServicioImpl implements SesionServicio {
    private final SesionRepositorio sesionRepositorio;

    private final UsuarioServicio usuarioServicio;

    @Autowired
    public SesionServicioImpl(SesionRepositorio sesionRepositorio, UsuarioServicio usuarioServicio) {
        this.sesionRepositorio = sesionRepositorio;
        this.usuarioServicio = usuarioServicio;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sesion> listarSesiones() {
        return sesionRepositorio.findAll();
    }

    @Override
    @Transactional
    public void guardarSesion(Sesion sesion) {
        sesionRepositorio.save(sesion);
    }

    /**
     * @inheritDoc
     * Implementación del método iniciarSesion().
     * @param nombreUsuario nombre de usuario ingresado.
     * @param contrasenia contraseña ingresada.
     * @return una sesión con el usuario logueado o nada si no se pudo autenticar el usuario.
     */
    public Sesion iniciarSesion(String nombreUsuario, String contrasenia) {
        // Primero, se obtienen todos los usuarios.
        List<Usuario> listaUsuarios = usuarioServicio.listarUsuarios();
        Sesion sesionActual = null;
        /* Mientras haya usuarios, le pregunto a cada uno si coinciden con el usuario y contraseña, y
           si además su empleado correspondiente es Responsable de visitas. */
        for (Usuario usuario:
                listaUsuarios) {
            // Se crea una sesión con la hora de inicio y el usuario logueado.
            if (usuario.autenticar(nombreUsuario, contrasenia))
                sesionActual = new Sesion(LocalDateTime.now(), usuario);
        }
        return sesionActual;
    }
}
