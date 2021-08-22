package com.ppai.aplicacion.controlador;

import com.ppai.aplicacion.interfaz.PantallaLogin;
import com.ppai.aplicacion.interfaz.PantallaPrincipal;
import com.ppai.aplicacion.negocio.Sesion;
import com.ppai.aplicacion.negocio.Usuario;
import com.ppai.aplicacion.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ControladorLogin {
    private PantallaLogin pantallaLogin;
    private PantallaPrincipal pantallaPrincipal;
    private ControladorReservaVisita controladorNuevaReservaVisita;
    private final UsuarioServicio usuarioServicio;
    private Sesion sesionActual;


    @Autowired
    public ControladorLogin(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;;
    }

    @Autowired
    public void setPantallaLogin(PantallaLogin pantallaLogin) {
        this.pantallaLogin = pantallaLogin;
    }

    @Autowired
    public void setPantallaPrincipal(PantallaPrincipal pantallaPrincipal) {
        this.pantallaPrincipal = pantallaPrincipal;
    }

    @Autowired
    public void setControladorNuevaReservaVisita(ControladorReservaVisita controladorNuevaReservaVisita) {
        this.controladorNuevaReservaVisita = controladorNuevaReservaVisita;
    }

    /**
     * Método que verifica que el usuario y contraseña pasados por parámetro sean válidos
     * y que además el usuario ingresado sea un Responsable de Visitas.
     * @param nombreUsuario nombre de usuario ingresado
     * @param contrasena contraseña ingresada
     */
    public void login(String nombreUsuario, String contrasena) {
        //
        // Primero, se obtienen todos los usuarios:
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        for (Usuario usuario:
                usuarios) {
            // Mientras haya usuarios, le pregunto a cada uno si coinciden con el usuario y contraseña, y
            // si además su empleado correspondiente es Responsable de visitas. Si coincide, se corta
            // la iteración y se crea un nuevo objeto Sesión.
            if (usuario.autenticar(nombreUsuario, contrasena)
                    && usuario.correspondeAResponsableDeVisitas()) {
                sesionActual = new Sesion(LocalDateTime.now(), usuario);
                break;
            }
        }

        // Si se encontró el usuario correspondiente, se informa un mensaje de éxito en la pantalla.
        if (sesionActual != null) {
            controladorNuevaReservaVisita.setSesionActual(sesionActual);
            pantallaPrincipal.habilitarPantalla();
        }

        // Si no, se informa un mensaje de ingreso fallido.
        else
            pantallaLogin.informarLoginFallido();
    }
}//end ControladorLogin