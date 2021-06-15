package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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


    public AsignacionGuia() {}

    public AsignacionGuia(Empleado empleado, LocalDateTime fechaHoraInicio) {
        this.empleado = empleado;
        this.fechaHoraInicio = fechaHoraInicio;
    }

    @Override
    public String toString() {
        return "AsignacionGuia{" +
                "fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", empleado=" + empleado +
                '}';
    }

    public boolean esEnDiaYHora(LocalDateTime fechaYHora){
        // Método que dice si la asignación es en el día y la hora pasada por parámetro.
        LocalDate fecha = fechaYHora.toLocalDate();
        LocalTime hora = fechaYHora.toLocalTime();
        return (fecha.compareTo(fechaHoraInicio.toLocalDate()) == 0
                && hora.isAfter(fechaHoraInicio.toLocalTime())
                && hora.isBefore(fechaHoraFin.toLocalTime()));
    }

    public boolean esTuGuia(Empleado empleado) {
        return empleado == this.empleado;
    }
}//end AsignacionGuia
