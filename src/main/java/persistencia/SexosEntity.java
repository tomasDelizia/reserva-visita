package persistencia;

import javax.persistence.*;

@Entity
@Table(name = "SEXOS", schema = "dbo", catalog = "museo_pictorico")
public class SexosEntity {
    private byte idSexo;
    private String nombre;

    @Id
    @Column(name = "id_sexo")
    public byte getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(byte idSexo) {
        this.idSexo = idSexo;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SexosEntity that = (SexosEntity) o;

        if (idSexo != that.idSexo) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) idSexo;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
