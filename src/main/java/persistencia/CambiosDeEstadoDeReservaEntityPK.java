package persistencia;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

public class CambiosDeEstadoDeReservaEntityPK implements Serializable {
    private int idReserva;
    private byte idEstadoReserva;
    private Timestamp fechaHoraInicio;

    @Column(name = "id_reserva")
    @Id
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    @Column(name = "id_estado_reserva")
    @Id
    public byte getIdEstadoReserva() {
        return idEstadoReserva;
    }

    public void setIdEstadoReserva(byte idEstadoReserva) {
        this.idEstadoReserva = idEstadoReserva;
    }

    @Column(name = "fecha_hora_inicio")
    @Id
    public Timestamp getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Timestamp fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CambiosDeEstadoDeReservaEntityPK that = (CambiosDeEstadoDeReservaEntityPK) o;

        if (idReserva != that.idReserva) return false;
        if (idEstadoReserva != that.idEstadoReserva) return false;
        if (fechaHoraInicio != null ? !fechaHoraInicio.equals(that.fechaHoraInicio) : that.fechaHoraInicio != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idReserva;
        result = 31 * result + (int) idEstadoReserva;
        result = 31 * result + (fechaHoraInicio != null ? fechaHoraInicio.hashCode() : 0);
        return result;
    }
}
