package com.ppai.aplicacion.negocio.visita_programada.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Clase que representa las entidades persistentes Cambios de Estado de Reserva.
 */
@Entity
@Table(name = "CAMBIOS_DE_ESTADO", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class CambioEstadoReserva {
    @Id
    @Column(name = "id_cambio_de_estado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCambioEstado;

    @OneToOne
    @JoinColumn(name = "id_estado_reserva", referencedColumnName = "id_estado_reserva")
    private EstadoReserva estadoReserva;

    @Basic
    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;

    @Basic
    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;


    public CambioEstadoReserva(EstadoReserva estadoReserva, LocalDateTime fechaHoraInicio) {
        this.estadoReserva = estadoReserva;
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public CambioEstadoReserva() {}
}//end CambioEstadoReserva
