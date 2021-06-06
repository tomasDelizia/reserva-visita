package persistencia;

import javax.persistence.*;

@Entity
@Table(name = "TIPOS_DE_EXPOSICION", schema = "dbo", catalog = "museo_pictorico")
public class TiposDeExposicionEntity {
    private byte idTipoExposicion;
    private String nombre;
    private String descripcion;

    @Id
    @Column(name = "id_tipo_exposicion")
    public byte getIdTipoExposicion() {
        return idTipoExposicion;
    }

    public void setIdTipoExposicion(byte idTipoExposicion) {
        this.idTipoExposicion = idTipoExposicion;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TiposDeExposicionEntity that = (TiposDeExposicionEntity) o;

        if (idTipoExposicion != that.idTipoExposicion) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) idTipoExposicion;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }
}
