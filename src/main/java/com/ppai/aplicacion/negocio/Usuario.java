package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

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


    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", empleado=" + empleado +
                '}';
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public boolean autenticar(String nombreUsuario, String contrasena) {
        // Método que verifica si el nombre de usuario y contraseña pasados por parámetro son correctos
        return this.nombreUsuario.equals(nombreUsuario) && this.contrasena.equals(contrasena);
    }

    public boolean correspondeAResponsableDeVisitas() {
        // Método que verifica si el empleado correspondiente al usuario es Responsable de Visitas.
        return this.empleado.esResponsableDeVisitas();
    }
}//end Usuario
