package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

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


    @Override
    public String toString() {
        return "HorarioEmpleado{" +
                "horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", diaSemana=" + diaSemana +
                '}';
    }

    public boolean estaDentroDeDiaYHorario(LocalDateTime fechaYHora) {
        // Método que dice si el horario de trabajo está dentro del día y hora pasada por parámetro.
        return (fechaYHora.getDayOfWeek().getValue() == diaSemana.getValue()
                && fechaYHora.toLocalTime().isAfter(horaInicio)
                && fechaYHora.toLocalTime().isBefore(horaFin));
    }
}//end HorarioEmpleado
