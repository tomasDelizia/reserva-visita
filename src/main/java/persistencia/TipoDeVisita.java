package persistencia;

import javax.persistence.*;

@Entity
@Table(name = "TIPOS_DE_VISITA", schema = "dbo", catalog = "museo_pictorico")
public class TipoDeVisita {
    private byte idTipoVisita;
    private String nombre;

    public TipoDeVisita() {

    }

    public TipoDeVisita(String nombre) {
        this.nombre = nombre;
    }

    @Id
    @Column(name = "id_tipo_visita")
    public byte getIdTipoVisita() {
        return idTipoVisita;
    }

    public void setIdTipoVisita(byte idTipoVisita) {
        this.idTipoVisita = idTipoVisita;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoDeVisita{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoDeVisita that = (TipoDeVisita) o;

        if (idTipoVisita != that.idTipoVisita) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) idTipoVisita;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
