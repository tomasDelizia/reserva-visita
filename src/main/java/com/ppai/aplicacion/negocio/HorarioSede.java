package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * Clase que representa las entidades persistentes Horarios de Sede.
 */
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
}//end HorarioSede
