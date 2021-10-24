package com.ppai.aplicacion.negocio.usuario.servicio.implementacion;

import com.ppai.aplicacion.negocio.empleado.modelo.Empleado;
import com.ppai.aplicacion.negocio.usuario.modelo.Sesion;
import com.ppai.aplicacion.negocio.usuario.modelo.Usuario;
import com.ppai.aplicacion.persistencia.repositorio.SesionRepositorio;
import com.ppai.aplicacion.negocio.usuario.servicio.SesionServicio;
import com.ppai.aplicacion.negocio.usuario.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SesionServicioImpl implements SesionServicio {
    private final SesionRepositorio sesionRepositorio;
    private final UsuarioServicio usuarioServicio;
    private static Sesion sesionActual;

    @Autowired
    public SesionServicioImpl(SesionRepositorio sesionRepositorio, UsuarioServicio usuarioServicio) {
        this.sesionRepositorio = sesionRepositorio;
        this.usuarioServicio = usuarioServicio;
    }

    @Override
    @Transactional
    public void guardarSesion(Sesion sesion) {
        sesionRepositorio.save(sesion);
    }

    @Override
    public Empleado getEmpleadoEnSesion() {
        return sesionActual.getEmpleadoEnSesion();
    }

    public Sesion iniciarSesion(String nombreUsuario, String contrasenia) {
        // Primero, se obtienen todos los usuarios.
        List<Usuario> listaUsuarios = usuarioServicio.listarUsuarios();
        sesionActual = null;
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

    public void cerrarSesion() {
        sesionActual.setFechaYHoraFin(LocalDateTime.now());
        guardarSesion(sesionActual);
    }
}
