package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "EstadoReserva{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    public boolean esPendienteDeConfirmacion(){
        return nombre.equals("Pendiente de Confirmación");
    }
}//end EstadoReserva
