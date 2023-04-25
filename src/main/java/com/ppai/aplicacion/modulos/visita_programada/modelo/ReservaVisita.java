package com.ppai.aplicacion.modulos.visita_programada.modelo;

import com.ppai.aplicacion.modulos.empleado.modelo.AsignacionGuia;
import com.ppai.aplicacion.modulos.empleado.modelo.Empleado;
import com.ppai.aplicacion.modulos.exposicion.modelo.Exposicion;
import com.ppai.aplicacion.modulos.sede.modelo.Sede;
import com.ppai.aplicacion.modulos.visita_programada.estado.PendienteDeConfirmacion;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa las entidades persistentes Reservas de Visita Guiada.
 */
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "CAMBIOS_DE_ESTADO_DE_RESERVAS",
            joinColumns = @JoinColumn(name = "id_reserva"),
            inverseJoinColumns = @JoinColumn(name = "id_cambio_de_estado"))
    private List<CambioEstadoReserva> cambioEstadoReserva = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "CAMBIOS_DE_ESTADO_DE_RESERVAS",
            joinColumns = @JoinColumn(name = "id_reserva"),
            inverseJoinColumns = @JoinColumn(name = "id_cambio_de_estado"))
    private List<CambioEstadoReservaVisita> cambioEstadoReservaVisita = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ASIGNACIONES_DE_GUIA_X_RESERVA",
            joinColumns = @JoinColumn(name = "id_reserva"),
            inverseJoinColumns = @JoinColumn(name = "id_asignacion"))
    private List<AsignacionGuia> asignacionGuia = new ArrayList<>();


    public ReservaVisita(){};

    /**
     * Método contructor de la una reserva de visita.
     * @param numeroReserva el número de la reserva.
     * @param cantidadAlumnos la cantidad de alumnos que asistirán a la visita.
     * @param fechaYHoraCreacion la fecha y hora en la que se crea la reserva.
     * @param fechaYHoraReserva la fecha y hora en la que se realizará la visita.
     * @param duracionEstimada la duración estimada de la visita.
     * @param escuela la escuela que realizará la visita.
     * @param sede la sede donde se realizará la visita.
     * @param empleadoCreo el empleado Responsable de Visitas que registra la reserva.
     * @param exposicion la lista de exposiciones que se visitarán.
     * @param estadoPendiente el estado inicial de la reserva, que es "Pendiente de Confirmación".
     * @param guiasSeleccionados la lista de guías para la visita.
     */
    public ReservaVisita(int numeroReserva,
                         int cantidadAlumnos,
                         LocalDateTime fechaYHoraCreacion,
                         LocalDateTime fechaYHoraReserva,
                         LocalTime duracionEstimada,
                         Escuela escuela,
                         Sede sede,
                         Empleado empleadoCreo,
                         List<Exposicion> exposicion,
                         EstadoReserva estadoPendiente,
                         List<Empleado> guiasSeleccionados
                        ) {
        this.numeroReserva = numeroReserva;
        this.cantidadAlumnos = cantidadAlumnos;
        this.fechaYHoraCreacion = fechaYHoraCreacion;
        this.fechaYHoraReserva = fechaYHoraReserva;
        this.duracionEstimada = duracionEstimada;
        this.escuela = escuela;
        this.sede = sede;
        this.empleadoCreo = empleadoCreo;
        this.exposicion = exposicion;

        CambioEstadoReserva nuevoCambioEstado =
                new CambioEstadoReserva(estadoPendiente, fechaYHoraCreacion);
        cambioEstadoReserva.add(nuevoCambioEstado);

        LocalDateTime fechaYHoraFinReserva = getFechaYHoraFinReserva();

        for (Empleado guia :
                guiasSeleccionados) {
            AsignacionGuia asignacionGuia = new AsignacionGuia(
                    guia, fechaYHoraReserva, fechaYHoraFinReserva);
            this.asignacionGuia.add(asignacionGuia);
      }
    }

    /**
     * Constructor aplicando patrón State.
     * @param numeroReserva el número de la reserva.
     * @param cantidadAlumnos la cantidad de alumnos que asistirán a la visita.
     * @param fechaYHoraCreacion la fecha y hora en la que se crea la reserva.
     * @param fechaYHoraReserva la fecha y hora en la que se realizará la visita.
     * @param duracionEstimada la duración estimada de la visita.
     * @param escuela la escuela que realizará la visita.
     * @param sede la sede donde se realizará la visita.
     * @param empleadoCreo el empleado Responsable de Visitas que registra la reserva.
     * @param exposicionesAVisitar la lista de exposiciones que se visitarán.
     * @param guiasSeleccionados la lista de guías para la visita.
     */
    public ReservaVisita(int numeroReserva,
                         int cantidadAlumnos,
                         LocalDateTime fechaYHoraCreacion,
                         LocalDateTime fechaYHoraReserva,
                         LocalTime duracionEstimada,
                         Escuela escuela,
                         Sede sede,
                         Empleado empleadoCreo,
                         List<Exposicion> exposicionesAVisitar,
                         List<Empleado> guiasSeleccionados
    ) {
        this.numeroReserva = numeroReserva;
        this.cantidadAlumnos = cantidadAlumnos;
        this.fechaYHoraCreacion = fechaYHoraCreacion;
        this.fechaYHoraReserva = fechaYHoraReserva;
        this.duracionEstimada = duracionEstimada;
        this.escuela = escuela;
        this.sede = sede;
        this.empleadoCreo = empleadoCreo;
        this.exposicion = exposicionesAVisitar;

        LocalDateTime fechaYHoraFinReserva = getFechaYHoraFinReserva();

        for (Empleado guia :
                guiasSeleccionados) {
            AsignacionGuia asignacionGuia = new AsignacionGuia(guia, fechaYHoraReserva, fechaYHoraFinReserva);
            addAsignacionGuia(asignacionGuia);
        }

        EstadoReservaVisita estadoInicial = new PendienteDeConfirmacion();
        CambioEstadoReservaVisita cambioEstado = new CambioEstadoReservaVisita(estadoInicial, fechaYHoraCreacion);
        addCambioEstado(cambioEstado);
    }

    /**
     * Método para añadir una asignación de guía a la lista de asignaciones de guía.
     * @param asignacionGuia la asignación a añadir.
     */
    public void addAsignacionGuia(AsignacionGuia asignacionGuia) {
        this.asignacionGuia.add(asignacionGuia);
    }

    /**
     * Método para añadir un cambio de estado a la colección de cambios de estado, tomando la fecha y
     * hora de fin del cambio de estado anterior.
     * @param cambioEstado el cambio de estado a agregar a la colección.
     */
    public void addCambioEstado(CambioEstadoReservaVisita cambioEstado) {
        LocalDateTime fechaYHoraActual = LocalDateTime.now();
        for (CambioEstadoReservaVisita cambio:
             cambioEstadoReservaVisita) {
            if (cambio.getEstadoReservaVisita() == getEstadoActual())
                cambio.setFechaHoraFin(fechaYHoraActual);
        }
        this.cambioEstadoReservaVisita.add(cambioEstado);
    }

    /**
     * Método que devuelve la cantidad de alumnos de la reserva.
     * @return la cantidad de alumnos de la reserva en formato entero.
     */
    public int getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    /**
     * Método que devuelve la cantidad de alumnos confirmada de la reserva.
     * @return la cantidad de alumnos confirmada de la reserva en formato entero.
     */
    public Integer getCantidadAlumnosConfirmada() {
        return cantidadAlumnosConfirmada;
    }

    /**
     * Método que devuelve el número de la reserva.
     * @return el número de la reserva en formato entero.
     */
    public int getNroReserva() {
        return numeroReserva;
    }

    /**
     * Método que determina si una visita se realiza dentro del rango horario pasado por parámetro.
     * @param fechaYHoraInicio la fecha y hora de inicio de la reserva nueva.
     * @param fechaYHoraFin la fecha y hora de fin de la reserva nueva.
     * @return verdadero si la visita se realiza en el mismo horario que la reserva a registrar, y falso en
     * cualquier otro caso.
     */
    public boolean esEnRangoHorario(LocalDateTime fechaYHoraInicio, LocalDateTime fechaYHoraFin) {
        return esEnDiaYHora(fechaYHoraInicio) || esEnDiaYHora(fechaYHoraFin);
    }

    /**
     * Método que dice si la reserva es en el día y la hora pasada por parámetro.
     * @param fechaYHora la fecha y hora en la que se realizará una reserva.
     * @return verdadero si la visita se realiza a la misma hora que la reserva nueva, y falso en cualquier otro
     * caso.
     */
    public boolean esEnDiaYHora(LocalDateTime fechaYHora) {
        LocalDate fecha = fechaYHora.toLocalDate();
        LocalTime hora = fechaYHora.toLocalTime();
        return (fecha.compareTo(this.fechaYHoraReserva.toLocalDate()) == 0
                && hora.isAfter(this.fechaYHoraReserva.toLocalTime())
                && hora.isBefore(this.getFechaYHoraFinReserva().toLocalTime()));
    }

    /**
     * Método para obtener la fecha y hora de fin de la reserva a partir de su duración estimada y su
     * hora de inicio.
     * @return la fecha y hora de fin de la reserva.
     */
    public LocalDateTime getFechaYHoraFinReserva() {
        return fechaYHoraReserva
                .plusHours(duracionEstimada.getHour()).plusMinutes(duracionEstimada.getMinute());
    }

    /**
     * Método para saber si la sede pasada por parámetro corresponde a esta reserva de visita.
     * @param sede la sede de la que se desea saber si corresponde a esta reserva de visita.
     * @return verdadero si esta reserva de visita corresponde a la sede.
     */
    public boolean esTuSede(Sede sede) {
        return sede.esTuNombre(this.sede.getNombre());
    }

    /**
     * Método para obtener el estado actual de la visita.
     * @return el estado actual de la visita.
     */
    public EstadoReservaVisita getEstadoActual() {
        for (CambioEstadoReservaVisita cambioEstado:
             cambioEstadoReservaVisita) {
            if (cambioEstado.esEstadoActual())
                return cambioEstado.getEstadoReservaVisita();
        }
        return null;
    }

    public void notificarEscuelas(List<Escuela> escuelasANotificar) {
        getEstadoActual().notificarEscuelas(escuelasANotificar, this);
    }

    public void confirmar() {
        getEstadoActual().confirmar(this);
    }

    public void cumplimentar() {
        getEstadoActual().cumplimentar(this);
    }

    public void cancelar() {
        getEstadoActual().cancelar(this);
    }

    public void anular() {
        getEstadoActual().anular(this);
    }

    public boolean esPendienteDeConfirmacion() {
        return getEstadoActual().esPendienteDeConfirmacion();
    }

    public boolean esEscuelaNotificada() {
        return getEstadoActual().esEscuelaNotificada();
    }

    public boolean esConfirmada() {
        return getEstadoActual().esConfirmada();
    }

    public boolean esCumplimentada() {
        return getEstadoActual().esCumplimentada();
    }

    public boolean esCancelada() {
        return getEstadoActual().esCancelada();
    }

    public boolean esAnulada() {
        return getEstadoActual().esAnulada();
    }
}//end ReservaVisita
