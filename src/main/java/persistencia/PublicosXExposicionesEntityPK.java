package persistencia;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class PublicosXExposicionesEntityPK implements Serializable {
    private int idExposicion;
    private int idPublico;

    @Column(name = "id_exposicion")
    @Id
    public int getIdExposicion() {
        return idExposicion;
    }

    public void setIdExposicion(int idExposicion) {
        this.idExposicion = idExposicion;
    }

    @Column(name = "id_publico")
    @Id
    public int getIdPublico() {
        return idPublico;
    }

    public void setIdPublico(int idPublico) {
        this.idPublico = idPublico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicosXExposicionesEntityPK that = (PublicosXExposicionesEntityPK) o;

        if (idExposicion != that.idExposicion) return false;
        if (idPublico != that.idPublico) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idExposicion;
        result = 31 * result + idPublico;
        return result;
    }
}
