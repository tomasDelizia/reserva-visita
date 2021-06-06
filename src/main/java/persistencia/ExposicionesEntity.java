package persistencia;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "EXPOSICIONES", schema = "dbo", catalog = "museo_pictorico")
public class ExposicionesEntity {
    private int idExposicion;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaInicioReplanificada;
    private Date fechaFinReplanificada;
    private Object horaApertura;
    private Object horaCierre;

    @Id
    @Column(name = "id_exposicion")
    public int getIdExposicion() {
        return idExposicion;
    }

    public void setIdExposicion(int idExposicion) {
        this.idExposicion = idExposicion;
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
    @Column(name = "fecha_inicio")
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Basic
    @Column(name = "fecha_fin")
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Basic
    @Column(name = "fecha_inicio_replanificada")
    public Date getFechaInicioReplanificada() {
        return fechaInicioReplanificada;
    }

    public void setFechaInicioReplanificada(Date fechaInicioReplanificada) {
        this.fechaInicioReplanificada = fechaInicioReplanificada;
    }

    @Basic
    @Column(name = "fecha_fin_replanificada")
    public Date getFechaFinReplanificada() {
        return fechaFinReplanificada;
    }

    public void setFechaFinReplanificada(Date fechaFinReplanificada) {
        this.fechaFinReplanificada = fechaFinReplanificada;
    }

    @Basic
    @Column(name = "hora_apertura")
    public Object getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(Object horaApertura) {
        this.horaApertura = horaApertura;
    }

    @Basic
    @Column(name = "hora_cierre")
    public Object getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(Object horaCierre) {
        this.horaCierre = horaCierre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExposicionesEntity that = (ExposicionesEntity) o;

        if (idExposicion != that.idExposicion) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) return false;
        if (fechaFin != null ? !fechaFin.equals(that.fechaFin) : that.fechaFin != null) return false;
        if (fechaInicioReplanificada != null ? !fechaInicioReplanificada.equals(that.fechaInicioReplanificada) : that.fechaInicioReplanificada != null)
            return false;
        if (fechaFinReplanificada != null ? !fechaFinReplanificada.equals(that.fechaFinReplanificada) : that.fechaFinReplanificada != null)
            return false;
        if (horaApertura != null ? !horaApertura.equals(that.horaApertura) : that.horaApertura != null) return false;
        if (horaCierre != null ? !horaCierre.equals(that.horaCierre) : that.horaCierre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idExposicion;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (fechaFin != null ? fechaFin.hashCode() : 0);
        result = 31 * result + (fechaInicioReplanificada != null ? fechaInicioReplanificada.hashCode() : 0);
        result = 31 * result + (fechaFinReplanificada != null ? fechaFinReplanificada.hashCode() : 0);
        result = 31 * result + (horaApertura != null ? horaApertura.hashCode() : 0);
        result = 31 * result + (horaCierre != null ? horaCierre.hashCode() : 0);
        return result;
    }
}
