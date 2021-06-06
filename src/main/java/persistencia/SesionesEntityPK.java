package persistencia;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

public class SesionesEntityPK implements Serializable {
    private int idUsuario;
    private Timestamp fechaHoraInicio;

    @Column(name = "id_usuario")
    @Id
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

        SesionesEntityPK that = (SesionesEntityPK) o;

        if (idUsuario != that.idUsuario) return false;
        if (fechaHoraInicio != null ? !fechaHoraInicio.equals(that.fechaHoraInicio) : that.fechaHoraInicio != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUsuario;
        result = 31 * result + (fechaHoraInicio != null ? fechaHoraInicio.hashCode() : 0);
        return result;
    }
}
