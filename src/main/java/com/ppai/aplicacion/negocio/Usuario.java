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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    public LocalDate getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(LocalDate caducidad) {
        this.caducidad = caducidad;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}//end Usuario