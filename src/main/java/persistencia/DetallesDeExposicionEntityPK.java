package persistencia;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class DetallesDeExposicionEntityPK implements Serializable {
    private int idExposicion;
    private int idObra;

    @Column(name = "id_exposicion")
    @Id
    public int getIdExposicion() {
        return idExposicion;
    }

    public void setIdExposicion(int idExposicion) {
        this.idExposicion = idExposicion;
    }

    @Column(name = "id_obra")
    @Id
    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetallesDeExposicionEntityPK that = (DetallesDeExposicionEntityPK) o;

        if (idExposicion != that.idExposicion) return false;
        if (idObra != that.idObra) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idExposicion;
        result = 31 * result + idObra;
        return result;
    }
}
