package com.ppai.aplicacion.modulos.visita_programada.modelo;

import javax.persistence.*;

/**
 * Clase que representa las entidades persistentes Estados de Reserva.
 */
@Entity
@Table(name = "ESTADOS_DE_RESERVA", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class EstadoReserva {
    @Id
    @Column(name = "id_estado_reserva")
    private byte idEstadoReserva;

    @Basic
    @Column(name = "nombre")
    private String nombre;

    @Basic
    @Column(name = "descripcion")
    private String descripcion;


    /**
     * Método que dice si el estado de la reserva es "Pendiente de Confirmación".
     * @return verdadero si el estado de la reserva es "Pendiente de Confirmación", y falso en cualquier otro caso.
     */
    public boolean esPendienteDeConfirmacion(){
        return nombre.equals("Pendiente de Confirmación");
    }
}//end EstadoReserva
