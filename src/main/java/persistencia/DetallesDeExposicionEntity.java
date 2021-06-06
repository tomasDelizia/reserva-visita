package persistencia;

import javax.persistence.*;

@Entity
@Table(name = "DETALLES_DE_EXPOSICION", schema = "dbo", catalog = "museo_pictorico")
@IdClass(DetallesDeExposicionEntityPK.class)
public class DetallesDeExposicionEntity {
    private int idExposicion;
    private int idObra;
    private Integer lugarAsignado;

    @Id
    @Column(name = "id_exposicion")
    public int getIdExposicion() {
        return idExposicion;
    }

    public void setIdExposicion(int idExposicion) {
        this.idExposicion = idExposicion;
    }

    @Id
    @Column(name = "id_obra")
    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    @Basic
    @Column(name = "lugar_asignado")
    public Integer getLugarAsignado() {
        return lugarAsignado;
    }

    public void setLugarAsignado(Integer lugarAsignado) {
        this.lugarAsignado = lugarAsignado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetallesDeExposicionEntity that = (DetallesDeExposicionEntity) o;

        if (idExposicion != that.idExposicion) return false;
        if (idObra != that.idObra) return false;
        if (lugarAsignado != null ? !lugarAsignado.equals(that.lugarAsignado) : that.lugarAsignado != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idExposicion;
        result = 31 * result + idObra;
        result = 31 * result + (lugarAsignado != null ? lugarAsignado.hashCode() : 0);
        return result;
    }
}
