package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Table(name = "HORARIOS", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class HorarioSede {
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
        return "HorarioSede{" +
                "horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", diaSemana=" + diaSemana +
                '}';
    }

}//end HorarioSede
