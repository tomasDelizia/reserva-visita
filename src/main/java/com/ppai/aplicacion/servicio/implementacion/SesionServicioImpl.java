package com.ppai.aplicacion.servicio.implementacion;

import com.ppai.aplicacion.negocio.Empleado;
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

    /**
     * @inheritDoc
     * Implementación del método getEmpleadoEnSesion().
     * Método que devuelve el empleado que tiene asociado el usuario de la sesión actual.
     * @return el empleado logueado.
     */
    @Override
    public Empleado getEmpleadoEnSesion() {
        return sesionActual.getEmpleadoEnSesion();
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

    /**
     * @inheritDoc
     * Implementación del método guardarSesion().
     */
    public void cerrarSesion() {
        sesionActual.setFechaYHoraFin(LocalDateTime.now());
        guardarSesion(sesionActual);
    }
}
