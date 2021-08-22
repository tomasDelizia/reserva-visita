package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Clase que representa las entidades persistentes Sesiones.
 */
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


    /**
     * Método constructor de una Sesión.
     * @param fechaHoraInicio la fecha y hora de inicio de la sesión.
     * @param usuario el usuario al que pertenece la sesión.
     */
    public Sesion(LocalDateTime fechaHoraInicio, Usuario usuario) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.usuario = usuario;
    }

    public Sesion() {}

    /**
     * Método que devuelve el empleado al que pertenece el usuario en sesión.
     * @return el empleado logueado.
     */
    public Empleado getEmpleadoEnSesion() {
        return usuario.getEmpleado();
    }

    /**
     * Método para saber si la sesión es actual.
     * @return verdadero si la sesión aún no tiene fecha y hora de finalización, y falso en cualquier otro caso.
     */
    public boolean esActual() {
        return fechaHoraFin == null;
    }

    /**
     * Método para tomar la fecha y hora de fin pasados por parámetro y asignarla a la sesión.
     * @param fechaHoraFin la fecha y hora de finalización de la sesión.
     */
    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }
}//end Sesion
