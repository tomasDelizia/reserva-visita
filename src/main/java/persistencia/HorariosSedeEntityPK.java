package persistencia;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class HorariosSedeEntityPK implements Serializable {
    private int idSede;
    private byte idDia;

    @Column(name = "id_sede")
    @Id
    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
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

        HorariosSedeEntityPK that = (HorariosSedeEntityPK) o;

        if (idSede != that.idSede) return false;
        if (idDia != that.idDia) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSede;
        result = 31 * result + (int) idDia;
        return result;
    }
}
