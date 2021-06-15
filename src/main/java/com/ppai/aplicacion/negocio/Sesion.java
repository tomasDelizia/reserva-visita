package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

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
}//end Sesion
