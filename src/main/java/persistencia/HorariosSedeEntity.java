package persistencia;

import javax.persistence.*;

@Entity
@Table(name = "HORARIOS_SEDE", schema = "dbo", catalog = "museo_pictorico")
@IdClass(HorariosSedeEntityPK.class)
public class HorariosSedeEntity {
    private int idSede;
    private byte idDia;
    private Object horarioApertura;
    private Object horarioCierre;

    @Id
    @Column(name = "id_sede")
    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    @Id
    @Column(name = "id_dia")
    public byte getIdDia() {
        return idDia;
    }

    public void setIdDia(byte idDia) {
        this.idDia = idDia;
    }

    @Basic
    @Column(name = "horario_apertura")
    public Object getHorarioApertura() {
        return horarioApertura;
    }

    public void setHorarioApertura(Object horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    @Basic
    @Column(name = "horario_cierre")
    public Object getHorarioCierre() {
        return horarioCierre;
    }

    public void setHorarioCierre(Object horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HorariosSedeEntity that = (HorariosSedeEntity) o;

        if (idSede != that.idSede) return false;
        if (idDia != that.idDia) return false;
        if (horarioApertura != null ? !horarioApertura.equals(that.horarioApertura) : that.horarioApertura != null)
            return false;
        if (horarioCierre != null ? !horarioCierre.equals(that.horarioCierre) : that.horarioCierre != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSede;
        result = 31 * result + (int) idDia;
        result = 31 * result + (horarioApertura != null ? horarioApertura.hashCode() : 0);
        result = 31 * result + (horarioCierre != null ? horarioCierre.hashCode() : 0);
        return result;
    }
}
