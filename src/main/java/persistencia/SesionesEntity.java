package persistencia;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "SESIONES", schema = "dbo", catalog = "museo_pictorico")
@IdClass(SesionesEntityPK.class)
public class SesionesEntity {
    private int idUsuario;
    private Timestamp fechaHoraInicio;
    private Timestamp fechaHoraFin;

    @Id
    @Column(name = "id_usuario")
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Id
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

        SesionesEntity that = (SesionesEntity) o;

        if (idUsuario != that.idUsuario) return false;
        if (fechaHoraInicio != null ? !fechaHoraInicio.equals(that.fechaHoraInicio) : that.fechaHoraInicio != null)
            return false;
        if (fechaHoraFin != null ? !fechaHoraFin.equals(that.fechaHoraFin) : that.fechaHoraFin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUsuario;
        result = 31 * result + (fechaHoraInicio != null ? fechaHoraInicio.hashCode() : 0);
        result = 31 * result + (fechaHoraFin != null ? fechaHoraFin.hashCode() : 0);
        return result;
    }
}
