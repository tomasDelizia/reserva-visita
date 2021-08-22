package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Clase que representa las entidades persistentes Asignaciones de Guía.
 */
@Entity
@Table(name = "ASIGNACIONES_DE_GUIA", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class AsignacionGuia {
    @Id
    @Column(name = "id_asignacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAsignacion;

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

    public AsignacionGuia(Empleado empleado, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        this.empleado = empleado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
    }

    /**
     * Método que dice si la asignación es en el día y la hora pasada por parámetro.
     * @param fechaYHora la fecha y hora en la cual se desea saber si está la asignación de guía.
     * @return verdadero si la asignación es en el día y la hora indicados, y falso en cualquier otro caso.
     */
    public boolean esEnDiaYHora(LocalDateTime fechaYHora){
        LocalDate fecha = fechaYHora.toLocalDate();
        LocalTime hora = fechaYHora.toLocalTime();
        return (fecha.equals(fechaHoraInicio.toLocalDate())
                && (hora.isAfter(fechaHoraInicio.toLocalTime()) || hora.equals(fechaHoraInicio.toLocalTime()))
                && hora.isBefore(fechaHoraFin.toLocalTime()));
    }

    /**
     * Método para saber si la asignación corresponde al guía pasado por parámetro.
     * @param empleado el guía a saber si corresponde la asignación.
     * @return verdadero si la asignación corresponde al guía, y falso en cualquier otro caso.
     */
    public boolean esTuGuia(Empleado empleado) {
        return empleado.getNombre().equals(this.empleado.getNombre());
    }
}//end AsignacionGuia
