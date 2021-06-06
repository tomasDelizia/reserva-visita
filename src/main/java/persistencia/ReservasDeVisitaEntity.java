package persistencia;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "RESERVAS_DE_VISITA", schema = "dbo", catalog = "museo_pictorico")
public class ReservasDeVisitaEntity {
    private int idReserva;
    private Integer cantidadAlumnos;
    private Integer cantidadAlumnosConfirmada;
    private Timestamp fechaHoraCreacion;
    private Timestamp fechaHoraReserva;
    private Object duracionEstimada;
    private Object horaInicioReal;
    private Object horaFinReal;

    @Id
    @Column(name = "id_reserva")
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    @Basic
    @Column(name = "cantidad_alumnos")
    public Integer getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(Integer cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }

    @Basic
    @Column(name = "cantidad_alumnos_confirmada")
    public Integer getCantidadAlumnosConfirmada() {
        return cantidadAlumnosConfirmada;
    }

    public void setCantidadAlumnosConfirmada(Integer cantidadAlumnosConfirmada) {
        this.cantidadAlumnosConfirmada = cantidadAlumnosConfirmada;
    }

    @Basic
    @Column(name = "fecha_hora_creacion")
    public Timestamp getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Timestamp fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    @Basic
    @Column(name = "fecha_hora_reserva")
    public Timestamp getFechaHoraReserva() {
        return fechaHoraReserva;
    }

    public void setFechaHoraReserva(Timestamp fechaHoraReserva) {
        this.fechaHoraReserva = fechaHoraReserva;
    }

    @Basic
    @Column(name = "duracion_estimada")
    public Object getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(Object duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    @Basic
    @Column(name = "hora_inicio_real")
    public Object getHoraInicioReal() {
        return horaInicioReal;
    }

    public void setHoraInicioReal(Object horaInicioReal) {
        this.horaInicioReal = horaInicioReal;
    }

    @Basic
    @Column(name = "hora_fin_real")
    public Object getHoraFinReal() {
        return horaFinReal;
    }

    public void setHoraFinReal(Object horaFinReal) {
        this.horaFinReal = horaFinReal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservasDeVisitaEntity that = (ReservasDeVisitaEntity) o;

        if (idReserva != that.idReserva) return false;
        if (cantidadAlumnos != null ? !cantidadAlumnos.equals(that.cantidadAlumnos) : that.cantidadAlumnos != null)
            return false;
        if (cantidadAlumnosConfirmada != null ? !cantidadAlumnosConfirmada.equals(that.cantidadAlumnosConfirmada) : that.cantidadAlumnosConfirmada != null)
            return false;
        if (fechaHoraCreacion != null ? !fechaHoraCreacion.equals(that.fechaHoraCreacion) : that.fechaHoraCreacion != null)
            return false;
        if (fechaHoraReserva != null ? !fechaHoraReserva.equals(that.fechaHoraReserva) : that.fechaHoraReserva != null)
            return false;
        if (duracionEstimada != null ? !duracionEstimada.equals(that.duracionEstimada) : that.duracionEstimada != null)
            return false;
        if (horaInicioReal != null ? !horaInicioReal.equals(that.horaInicioReal) : that.horaInicioReal != null)
            return false;
        if (horaFinReal != null ? !horaFinReal.equals(that.horaFinReal) : that.horaFinReal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idReserva;
        result = 31 * result + (cantidadAlumnos != null ? cantidadAlumnos.hashCode() : 0);
        result = 31 * result + (cantidadAlumnosConfirmada != null ? cantidadAlumnosConfirmada.hashCode() : 0);
        result = 31 * result + (fechaHoraCreacion != null ? fechaHoraCreacion.hashCode() : 0);
        result = 31 * result + (fechaHoraReserva != null ? fechaHoraReserva.hashCode() : 0);
        result = 31 * result + (duracionEstimada != null ? duracionEstimada.hashCode() : 0);
        result = 31 * result + (horaInicioReal != null ? horaInicioReal.hashCode() : 0);
        result = 31 * result + (horaFinReal != null ? horaFinReal.hashCode() : 0);
        return result;
    }
}
