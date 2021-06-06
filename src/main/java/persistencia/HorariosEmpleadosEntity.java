package persistencia;

import javax.persistence.*;

@Entity
@Table(name = "HORARIOS_EMPLEADOS", schema = "dbo", catalog = "museo_pictorico")
@IdClass(HorariosEmpleadosEntityPK.class)
public class HorariosEmpleadosEntity {
    private int idEmpleado;
    private byte idDia;
    private Object horarioIngreso;
    private Object horarioSalida;

    @Id
    @Column(name = "id_empleado")
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
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
    @Column(name = "horario_ingreso")
    public Object getHorarioIngreso() {
        return horarioIngreso;
    }

    public void setHorarioIngreso(Object horarioIngreso) {
        this.horarioIngreso = horarioIngreso;
    }

    @Basic
    @Column(name = "horario_salida")
    public Object getHorarioSalida() {
        return horarioSalida;
    }

    public void setHorarioSalida(Object horarioSalida) {
        this.horarioSalida = horarioSalida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HorariosEmpleadosEntity that = (HorariosEmpleadosEntity) o;

        if (idEmpleado != that.idEmpleado) return false;
        if (idDia != that.idDia) return false;
        if (horarioIngreso != null ? !horarioIngreso.equals(that.horarioIngreso) : that.horarioIngreso != null)
            return false;
        if (horarioSalida != null ? !horarioSalida.equals(that.horarioSalida) : that.horarioSalida != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEmpleado;
        result = 31 * result + (int) idDia;
        result = 31 * result + (horarioIngreso != null ? horarioIngreso.hashCode() : 0);
        result = 31 * result + (horarioSalida != null ? horarioSalida.hashCode() : 0);
        return result;
    }
}
