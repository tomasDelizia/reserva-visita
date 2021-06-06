package persistencia;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "OBRAS", schema = "dbo", catalog = "museo_pictorico")
public class ObrasEntity {
    private int idObra;
    private String nombreObra;
    private BigDecimal alto;
    private BigDecimal ancho;
    private BigDecimal peso;
    private BigDecimal valuacion;
    private Object duracionResumida;
    private Object duracionExtendida;
    private Date fechaCreacion;
    private Date fechaPrimerIngreso;
    private Date fechaRegistracion;
    private String descripcion;
    private Integer codigoSensor;

    @Id
    @Column(name = "id_obra")
    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    @Basic
    @Column(name = "nombre_obra")
    public String getNombreObra() {
        return nombreObra;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    @Basic
    @Column(name = "alto")
    public BigDecimal getAlto() {
        return alto;
    }

    public void setAlto(BigDecimal alto) {
        this.alto = alto;
    }

    @Basic
    @Column(name = "ancho")
    public BigDecimal getAncho() {
        return ancho;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }

    @Basic
    @Column(name = "peso")
    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    @Basic
    @Column(name = "valuacion")
    public BigDecimal getValuacion() {
        return valuacion;
    }

    public void setValuacion(BigDecimal valuacion) {
        this.valuacion = valuacion;
    }

    @Basic
    @Column(name = "duracion_resumida")
    public Object getDuracionResumida() {
        return duracionResumida;
    }

    public void setDuracionResumida(Object duracionResumida) {
        this.duracionResumida = duracionResumida;
    }

    @Basic
    @Column(name = "duracion_extendida")
    public Object getDuracionExtendida() {
        return duracionExtendida;
    }

    public void setDuracionExtendida(Object duracionExtendida) {
        this.duracionExtendida = duracionExtendida;
    }

    @Basic
    @Column(name = "fecha_creacion")
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Basic
    @Column(name = "fecha_primer_ingreso")
    public Date getFechaPrimerIngreso() {
        return fechaPrimerIngreso;
    }

    public void setFechaPrimerIngreso(Date fechaPrimerIngreso) {
        this.fechaPrimerIngreso = fechaPrimerIngreso;
    }

    @Basic
    @Column(name = "fecha_registracion")
    public Date getFechaRegistracion() {
        return fechaRegistracion;
    }

    public void setFechaRegistracion(Date fechaRegistracion) {
        this.fechaRegistracion = fechaRegistracion;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "codigo_sensor")
    public Integer getCodigoSensor() {
        return codigoSensor;
    }

    public void setCodigoSensor(Integer codigoSensor) {
        this.codigoSensor = codigoSensor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObrasEntity that = (ObrasEntity) o;

        if (idObra != that.idObra) return false;
        if (nombreObra != null ? !nombreObra.equals(that.nombreObra) : that.nombreObra != null) return false;
        if (alto != null ? !alto.equals(that.alto) : that.alto != null) return false;
        if (ancho != null ? !ancho.equals(that.ancho) : that.ancho != null) return false;
        if (peso != null ? !peso.equals(that.peso) : that.peso != null) return false;
        if (valuacion != null ? !valuacion.equals(that.valuacion) : that.valuacion != null) return false;
        if (duracionResumida != null ? !duracionResumida.equals(that.duracionResumida) : that.duracionResumida != null)
            return false;
        if (duracionExtendida != null ? !duracionExtendida.equals(that.duracionExtendida) : that.duracionExtendida != null)
            return false;
        if (fechaCreacion != null ? !fechaCreacion.equals(that.fechaCreacion) : that.fechaCreacion != null)
            return false;
        if (fechaPrimerIngreso != null ? !fechaPrimerIngreso.equals(that.fechaPrimerIngreso) : that.fechaPrimerIngreso != null)
            return false;
        if (fechaRegistracion != null ? !fechaRegistracion.equals(that.fechaRegistracion) : that.fechaRegistracion != null)
            return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (codigoSensor != null ? !codigoSensor.equals(that.codigoSensor) : that.codigoSensor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idObra;
        result = 31 * result + (nombreObra != null ? nombreObra.hashCode() : 0);
        result = 31 * result + (alto != null ? alto.hashCode() : 0);
        result = 31 * result + (ancho != null ? ancho.hashCode() : 0);
        result = 31 * result + (peso != null ? peso.hashCode() : 0);
        result = 31 * result + (valuacion != null ? valuacion.hashCode() : 0);
        result = 31 * result + (duracionResumida != null ? duracionResumida.hashCode() : 0);
        result = 31 * result + (duracionExtendida != null ? duracionExtendida.hashCode() : 0);
        result = 31 * result + (fechaCreacion != null ? fechaCreacion.hashCode() : 0);
        result = 31 * result + (fechaPrimerIngreso != null ? fechaPrimerIngreso.hashCode() : 0);
        result = 31 * result + (fechaRegistracion != null ? fechaRegistracion.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (codigoSensor != null ? codigoSensor.hashCode() : 0);
        return result;
    }
}
