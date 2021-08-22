package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Clase que representa las entidades persistentes Usuarios.
 */
@Entity
@Table(name = "USUARIOS", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class Usuario {
    @Id
    @Column(name = "id_usuario")
    private int idUsuario;

    @Basic
    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Basic
    @Column(name = "contrasena")
    private String contrasena;

    @Basic
    @Column(name = "caducidad")
    private LocalDate caducidad;

    @OneToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    private Empleado empleado;


    /**
     * Método que devuelve el empleado asociado al usuario.
     * @return el empleado asociado al usuario.
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * Método que verifica si el nombre de usuario y contraseña pasados por parámetro son correctos.
     * @param nombreUsuario el nombre del usuario ingresado.
     * @param contrasena la contraseña ingresada.
     * @return verdadero si el nombre del usuario y contraseña ingresados coinciden con el usuario, y falso en
     * cualquier otro caso.
     */
    public boolean autenticar(String nombreUsuario, String contrasena) {
        return this.nombreUsuario.equals(nombreUsuario) && this.contrasena.equals(contrasena);
    }

    /**
     * Método que verifica si el empleado correspondiente al usuario es "Responsable de Visitas".
     * @return verdadero si el empleado correspondiente al usuario es "Responsable de Visitas", y falso en cualquier
     * otro caso.
     */
    public boolean correspondeAResponsableDeVisitas() {
        return this.empleado.esResponsableDeVisitas();
    }
}//end Usuario
