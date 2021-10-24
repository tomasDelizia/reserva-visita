package com.ppai.aplicacion.negocio.empleado.modelo;

import com.ppai.aplicacion.negocio.enums.DiaSemana;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Clase que representa las entidades persistentes Horarios de Empleado.
 */
@Entity
@Table(name = "HORARIOS", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class HorarioEmpleado {
    @Id
    @Column(name = "id_horario")
    private int idHorario;

    @Basic
    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Basic
    @Column(name = "hora_fin")
    private LocalTime horaFin;

    @Basic
    @Column(name = "id_dia")
    @Enumerated(EnumType.ORDINAL)
    private DiaSemana diaSemana;

    /**
     * Método que dice si la fecha y hora pasada por parámetro está dentro del horario de trabajo.
     * @param fechaYHora la fecha y hora que se desea saber si coincide con el horario de trabajo.
     * @return verdadero si la fecha y hora pasada por parámetro está dentro del horario de trabajo, y falso en
     * cualquier otro caso.
     */
    public boolean estaDentroDeDiaYHorario(LocalDateTime fechaYHora) {
        return (fechaYHora.getDayOfWeek().getValue() == diaSemana.getValue()
                && (fechaYHora.toLocalTime().isAfter(horaInicio) || fechaYHora.toLocalTime().equals(horaInicio))
                && fechaYHora.toLocalTime().isBefore(horaFin));
    }
}//end HorarioEmpleado
