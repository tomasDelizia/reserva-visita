package com.ppai.aplicacion.negocio;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "HORARIOS_EMPLEADOS", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class HorarioEmpleado {
    @Id
    @Column(name = "id_empleado")
    private int idEmpleado;

    @Column(name = "horario_ingreso")
    private LocalTime horarioIngreso;

    @Basic
    @Column(name = "horario_salida")
    private LocalTime horarioSalida;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "HORARIOS_EMPLEADOS",
            joinColumns = @JoinColumn(name = "id_empleado"),
            inverseJoinColumns = @JoinColumn(name = "id_dia"))
    private List<DiaSemana> diaSemana;


    @Override
    public String toString() {
        return "HorarioEmpleado{" +
                "horarioIngreso=" + horarioIngreso +
                ", horarioSalida=" + horarioSalida +
                ", diaSemana=" + diaSemana +
                '}';
    }
}
