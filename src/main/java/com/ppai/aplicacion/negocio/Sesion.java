package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SESIONES", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class Sesion {
    @Id
    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;

    @Basic
    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;


    public Sesion(LocalDateTime fechaHoraInicio, Usuario usuario) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.usuario = usuario;
    }

    public Sesion(){}

    @Override
    public String toString() {
        return "Sesion{" +
                "fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", usuario=" + usuario +
                '}';
    }

    public Empleado getEmpleadoEnSesion() {
        return usuario.getEmpleado();
    }

    public boolean autenticarUsuario(String nombreUsuario, String contrasena) {
        // Método para verificar que el usuario y contraseña ingresados sean los correctos
        return usuario.autenticar(nombreUsuario, contrasena);
    }

    public boolean esActual() {
        return fechaHoraFin == null;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }
}//end Sesion
