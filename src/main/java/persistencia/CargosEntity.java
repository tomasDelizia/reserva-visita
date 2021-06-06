package persistencia;

import javax.persistence.*;

@Entity
@Table(name = "CARGOS", schema = "dbo", catalog = "museo_pictorico")
public class CargosEntity {
    private byte idCargo;
    private String nombre;
    private String descripcion;

    @Id
    @Column(name = "id_cargo")
    public byte getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(byte idCargo) {
        this.idCargo = idCargo;
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

        CargosEntity that = (CargosEntity) o;

        if (idCargo != that.idCargo) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) idCargo;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }
}
