package persistencia;

import javax.persistence.*;

@Entity
@Table(name = "PUBLICOS_DESTINO", schema = "dbo", catalog = "museo_pictorico")
public class PublicosDestinoEntity {
    private int idPublico;
    private String nombre;
    private String caracteristicas;

    @Id
    @Column(name = "id_publico")
    public int getIdPublico() {
        return idPublico;
    }

    public void setIdPublico(int idPublico) {
        this.idPublico = idPublico;
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
    @Column(name = "caracteristicas")
    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicosDestinoEntity that = (PublicosDestinoEntity) o;

        if (idPublico != that.idPublico) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (caracteristicas != null ? !caracteristicas.equals(that.caracteristicas) : that.caracteristicas != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPublico;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (caracteristicas != null ? caracteristicas.hashCode() : 0);
        return result;
    }
}
