package com.ppai.aplicacion.negocio.visita_programada.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Clase que representa las entidades persistentes Cambios de Estado de Reserva.
 */
@Entity
@Table(name = "CAMBIOS_DE_ESTADO", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class CambioEstadoReservaVisita {
    @Id
    @Column(name = "id_cambio_de_estado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCambioEstado;

    @OneToOne
    @JoinColumn(name = "id_estado_reserva", referencedColumnName = "id_estado_reserva")
    private EstadoReservaVisita estadoReservaVisita;

    @Basic
    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;

    @Basic
    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;


    /**
     * Constructor del cambio de estado.
     * @param estadoReservaVisita el estado de reserva asociado al cambio de estado.
     * @param fechaHoraInicio la fecha y hora en la que se produjo el cambio de estado.
     */
    public CambioEstadoReservaVisita(EstadoReservaVisita estadoReservaVisita, LocalDateTime fechaHoraInicio) {
        this.estadoReservaVisita = estadoReservaVisita;
        this.fechaHoraInicio = fechaHoraInicio;
    }

    /**
     * Constructor del cambio de estado.
     * @param estadoReservaVisita el estado de reserva asociado al cambio de estado.
     */
    public CambioEstadoReservaVisita(EstadoReservaVisita estadoReservaVisita) {
        this.estadoReservaVisita = estadoReservaVisita;
        this.fechaHoraInicio = LocalDateTime.now();
    }

    public CambioEstadoReservaVisita() {

    }

    /**
     * Método para saber si el cambio de estado corresponde al estado actual.
     * @return verdadero si la fecha y hora de fin es nula, y falso si tiene valor asignado.
     */
    public boolean esEstadoActual() {
        return fechaHoraFin == null;
    }

    /**
     * Método para obtener el estado de reserva asociado al cambio de estado.
     * @return el estado de la reserva.
     */
    public EstadoReservaVisita getEstadoReservaVisita() {
        return estadoReservaVisita;
    }

    /**
     * Método para tomar la fecha y hora de fin de un cambio de estado.
     * @param fechaHoraFin la fecha y hora en la que se cambió a un nuevo estado.
     */
    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }
}//end CambioEstadoReserva
