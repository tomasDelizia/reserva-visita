package com.ppai.aplicacion.negocio;

import com.ppai.aplicacion.repo.AsignacionGuiaRepo;
import com.ppai.aplicacion.repo.CambioEstadoReservaRepo;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "RESERVAS_DE_VISITA", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class ReservaVisita {
    @Id
    @Column(name = "id_reserva")
    private int numeroReserva;

    @Basic
    @Column(name = "cantidad_alumnos")
    private int cantidadAlumnos;

    @Basic
    @Column(name = "cantidad_alumnos_confirmada")
    private Integer cantidadAlumnosConfirmada;

    @Basic
    @Column(name = "fecha_hora_creacion")
    private LocalDateTime fechaYHoraCreacion;

    @Basic
    @Column(name = "fecha_hora_reserva")
    private LocalDateTime fechaYHoraReserva;

    @Basic
    @Column(name = "duracion_estimada")
    private LocalTime duracionEstimada;

    @Basic
    @Column(name = "hora_inicio_real")
    private LocalTime horaInicioReal;

    @Basic
    @Column(name = "hora_fin_real")
    private LocalTime horaFinReal;

    @OneToOne
    @JoinColumn(name = "id_escuela", referencedColumnName = "id_escuela")
    private Escuela escuela;

    @OneToOne
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede")
    private Sede sede;

    @OneToOne
    @JoinColumn(name = "id_empleado_creador", referencedColumnName = "id_empleado")
    private Empleado empleadoCreo;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "EXPOSICIONES_X_RESERVAS",
            joinColumns = @JoinColumn(name = "id_reserva"),
            inverseJoinColumns = @JoinColumn(name = "id_exposicion"))
    private List<Exposicion> exposicion = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="idReserva")
    private final List<CambioEstadoReserva> cambioEstadoReserva = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="idReserva")
    private final List<AsignacionGuia> asignacionGuia = new ArrayList<>();

    public ReservaVisita() {}

    public int getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public Integer getCantidadAlumnosConfirmada() {
        return cantidadAlumnosConfirmada;
    }

    public ReservaVisita(int numeroReserva,
                         int cantidadAlumnos,
                         LocalDateTime fechaYHoraCreacion,
                         LocalDateTime fechaYHoraReserva,
                         Escuela escuela,
                         Sede sede,
                         Empleado empleadoCreo,
                         List<Exposicion> exposicion,
                         EstadoReserva estadoPendiente,
                         List<Empleado> guiasSeleccionados) {
        this.numeroReserva = numeroReserva;
        this.cantidadAlumnos = cantidadAlumnos;
        this.fechaYHoraCreacion = fechaYHoraCreacion;
        this.fechaYHoraReserva = fechaYHoraReserva;
        this.escuela = escuela;
        this.sede = sede;
        this.empleadoCreo = empleadoCreo;
        this.exposicion = exposicion;
        CambioEstadoReserva nuevoCambioEstado =
                new CambioEstadoReserva(estadoPendiente, fechaYHoraCreacion);
        cambioEstadoReserva.add(nuevoCambioEstado);
        for (Empleado guia:
             guiasSeleccionados) {
            AsignacionGuia asignacionGuia = new AsignacionGuia(guia, fechaYHoraCreacion);
            this.asignacionGuia.add(asignacionGuia);
        }
    }

    @Override
    public String toString() {
        return "ReservaVisita{" +
                "numeroReserva=" + numeroReserva +
                ", cantidadAlumnos=" + cantidadAlumnos +
                ", fechaHoraCreacion=" + fechaYHoraCreacion +
                ", duracionEstimada=" + duracionEstimada +
                ", escuela=" + escuela +
                ", sede=" + sede +
                ", exposicion=" + exposicion +
                ", cambioEstadoReserva=" + cambioEstadoReserva +
                ", asignacionGuias=" + asignacionGuia +
                '}';
    }

    public boolean esEnDiaYHora(LocalDateTime fechaYHora){
        // Método que dice si la reserva es en el día y la hora pasada por parámetro.
        LocalDate fecha = fechaYHora.toLocalDate();
        LocalTime hora = fechaYHora.toLocalTime();
        return (fecha.compareTo(this.fechaYHoraReserva.toLocalDate()) == 0
                && hora.isAfter(fechaYHoraReserva.toLocalTime())
                && hora.isBefore(fechaYHoraReserva.toLocalTime()));
    }

    public boolean esTuSede(Sede sede) {
        // Dice si la sede pasada por parámetro es la misma que tiene asociada este objeto sede
        return sede == this.sede;
    }

    public int getNumeroReserva() {
        return numeroReserva;
    }

}//end ReservaVisita
