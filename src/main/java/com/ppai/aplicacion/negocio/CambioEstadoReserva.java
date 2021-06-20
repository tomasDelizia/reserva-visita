package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "CAMBIOS_DE_ESTADO_DE_RESERVA", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class CambioEstadoReserva {
    @Id
    @Column(name = "id_reserva")
    private int idReserva;

    @OneToOne
    @JoinColumn(name = "id_estado_reserva", referencedColumnName = "id_estado_reserva")
    private EstadoReserva estadoReserva;

    @Basic
    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;

    @Basic
    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;


    public CambioEstadoReserva(int idReserva, EstadoReserva estadoReserva, LocalDateTime fechaHoraInicio) {
        this.idReserva = idReserva;
        this.estadoReserva = estadoReserva;
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public CambioEstadoReserva() {}

    @Override
    public String toString() {
        return "CambioEstadoReserva{" +
                "estadoReserva=" + estadoReserva +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                '}';
    }
}//end CambioEstadoReserva
