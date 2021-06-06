package persistencia;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class AsignacionesDeGuiaEntityPK implements Serializable {
    private int idReserva;
    private int idGuia;

    @Column(name = "id_reserva")
    @Id
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    @Column(name = "id_guia")
    @Id
    public int getIdGuia() {
        return idGuia;
    }

    public void setIdGuia(int idGuia) {
        this.idGuia = idGuia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsignacionesDeGuiaEntityPK that = (AsignacionesDeGuiaEntityPK) o;

        if (idReserva != that.idReserva) return false;
        if (idGuia != that.idGuia) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idReserva;
        result = 31 * result + idGuia;
        return result;
    }
}
