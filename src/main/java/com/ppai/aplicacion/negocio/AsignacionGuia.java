package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ASIGNACIONES_DE_GUIA", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class AsignacionGuia {
    @Id
    @Column(name = "id_reserva")
    private int idReserva;

    @Basic
    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;

    @Basic
    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    @OneToOne
    @JoinColumn(name = "id_guia", referencedColumnName = "id_empleado")
    private Empleado empleado;

    @Override
    public String toString() {
        return "AsignacionGuia{" +
                "fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", empleado=" + empleado +
                '}';
    }
}//end AsignacionGuia
