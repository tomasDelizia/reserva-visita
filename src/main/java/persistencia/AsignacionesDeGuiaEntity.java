package persistencia;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ASIGNACIONES_DE_GUIA", schema = "dbo", catalog = "museo_pictorico")
@IdClass(AsignacionesDeGuiaEntityPK.class)
public class AsignacionesDeGuiaEntity {
    private int idReserva;
    private int idGuia;
    private Timestamp fechaHoraInicio;
    private Timestamp fechaHoraFin;

    @Id
    @Column(name = "id_reserva")
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    @Id
    @Column(name = "id_guia")
    public int getIdGuia() {
        return idGuia;
    }

    public void setIdGuia(int idGuia) {
        this.idGuia = idGuia;
    }

    @Basic
    @Column(name = "fecha_hora_inicio")
    public Timestamp getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Timestamp fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    @Basic
    @Column(name = "fecha_hora_fin")
    public Timestamp getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Timestamp fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsignacionesDeGuiaEntity that = (AsignacionesDeGuiaEntity) o;

        if (idReserva != that.idReserva) return false;
        if (idGuia != that.idGuia) return false;
        if (fechaHoraInicio != null ? !fechaHoraInicio.equals(that.fechaHoraInicio) : that.fechaHoraInicio != null)
            return false;
        if (fechaHoraFin != null ? !fechaHoraFin.equals(that.fechaHoraFin) : that.fechaHoraFin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idReserva;
        result = 31 * result + idGuia;
        result = 31 * result + (fechaHoraInicio != null ? fechaHoraInicio.hashCode() : 0);
        result = 31 * result + (fechaHoraFin != null ? fechaHoraFin.hashCode() : 0);
        return result;
    }
}
