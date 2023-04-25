package com.ppai.aplicacion.modulos.visita_programada.modelo;

import javax.persistence.*;
import java.util.List;

/**
 * Clase que representa las entidades persistentes Estados de Reserva para las visitas programadas.
 */
@Entity
@Table(name = "ESTADOS_DE_RESERVA", schema = "dbo", catalog = "MUSEO_PICTORICO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="nombre")
public abstract class EstadoReservaVisita {
    @Id
    @Column(name = "id_estado_reserva")
    protected byte idEstadoReserva;

    @Basic
    @Column(name = "nombre", insertable = false, updatable = false)
    protected String nombre;

    @Basic
    @Column(name = "descripcion")
    private String descripcion;


    public EstadoReservaVisita() {}

    public void notificarEscuelas(List<Escuela> escuelasANotificar, ReservaVisita reservaVisita) {}

    public void confirmar(ReservaVisita reservaVisita) {}

    public void cumplimentar(ReservaVisita reservaVisita) {}

    public void cancelar(ReservaVisita reservaVisita) {}

    public void anular(ReservaVisita reservaVisita) {}

    public boolean esPendienteDeConfirmacion() {
        return false;
    }

    public boolean esEscuelaNotificada() {
        return false;
    }

    public boolean esConfirmada() {
        return false;
    }

    public boolean esCumplimentada() {
        return false;
    }

    public boolean esCancelada() {
        return false;
    }

    public boolean esAnulada() {
        return false;
    }
}