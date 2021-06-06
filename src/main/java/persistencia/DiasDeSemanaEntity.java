package persistencia;

import javax.persistence.*;

@Entity
@Table(name = "DIAS_DE_SEMANA", schema = "dbo", catalog = "museo_pictorico")
public class DiasDeSemanaEntity {
    private byte idDia;
    private String nombre;

    @Id
    @Column(name = "id_dia")
    public byte getIdDia() {
        return idDia;
    }

    public void setIdDia(byte idDia) {
        this.idDia = idDia;
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

        DiasDeSemanaEntity that = (DiasDeSemanaEntity) o;

        if (idDia != that.idDia) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) idDia;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
