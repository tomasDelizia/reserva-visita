package persistencia;

import javax.persistence.*;

@Entity
@Table(name = "PUBLICOS_X_EXPOSICIONES", schema = "dbo", catalog = "museo_pictorico")
@IdClass(PublicosXExposicionesEntityPK.class)
public class PublicosXExposicionesEntity {
    private int idExposicion;
    private int idPublico;

    @Id
    @Column(name = "id_exposicion")
    public int getIdExposicion() {
        return idExposicion;
    }

    public void setIdExposicion(int idExposicion) {
        this.idExposicion = idExposicion;
    }

    @Id
    @Column(name = "id_publico")
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

        PublicosXExposicionesEntity that = (PublicosXExposicionesEntity) o;

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
