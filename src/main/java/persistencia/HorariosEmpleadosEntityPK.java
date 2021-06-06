package persistencia;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class HorariosEmpleadosEntityPK implements Serializable {
    private int idEmpleado;
    private byte idDia;

    @Column(name = "id_empleado")
    @Id
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Column(name = "id_dia")
    @Id
    public byte getIdDia() {
        return idDia;
    }

    public void setIdDia(byte idDia) {
        this.idDia = idDia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HorariosEmpleadosEntityPK that = (HorariosEmpleadosEntityPK) o;

        if (idEmpleado != that.idEmpleado) return false;
        if (idDia != that.idDia) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEmpleado;
        result = 31 * result + (int) idDia;
        return result;
    }
}
