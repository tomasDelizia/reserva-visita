package com.ppai.aplicacion.modulos.empleado.modelo;

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
    private LocalDateTime fechaYHoraInicio;

    @Basic
    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaYHoraFin;

    @OneToOne
    @JoinColumn(name = "id_guia", referencedColumnName = "id_empleado")
    private Empleado guiaAsignado;


    public AsignacionGuia() {}

    public AsignacionGuia(Empleado guiaAsignado, LocalDateTime fechaYHoraInicio, LocalDateTime fechaYHoraFin) {
        this.guiaAsignado = guiaAsignado;
        this.fechaYHoraInicio = fechaYHoraInicio;
        this.fechaYHoraFin = fechaYHoraFin;
    }

    /**
     * Método que dice si la asignación es en el día y la hora pasada por parámetro.
     * @param fechaYHora la fecha y hora en la cual se desea saber si está la asignación de guía.
     * @return verdadero si la asignación es en el día y la hora indicados, y falso en cualquier otro caso.
     */
    public boolean esEnDiaYHora(LocalDateTime fechaYHora){
        LocalDate fecha = fechaYHora.toLocalDate();
        LocalTime hora = fechaYHora.toLocalTime();
        return (fecha.equals(fechaYHoraInicio.toLocalDate())
                && (hora.isAfter(fechaYHoraInicio.toLocalTime()) || hora.equals(fechaYHoraInicio.toLocalTime()))
                && hora.isBefore(fechaYHoraFin.toLocalTime()));
    }

    /**
     * Método para saber si la asignación corresponde al guía pasado por parámetro.
     * @param empleado el guía a saber si corresponde la asignación.
     * @return verdadero si la asignación corresponde al guía, y falso en cualquier otro caso.
     */
    public boolean esTuGuia(Empleado empleado) {
        return empleado.getNombre().equals(this.guiaAsignado.getNombre());
    }
}//end AsignacionGuia
