package persistencia;

import javax.persistence.*;

@Entity
@Table(name = "SEDES", schema = "dbo", catalog = "museo_pictorico")
public class SedesEntity {
    private int idSede;
    private String nombre;
    private Integer cantidadMaximaVisitantes;
    private Integer cantidadMaximaPorGuia;
    private String calleNombre;
    private Integer calleNumero;

    @Id
    @Column(name = "id_sede")
    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
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
    @Column(name = "cantidad_maxima_visitantes")
    public Integer getCantidadMaximaVisitantes() {
        return cantidadMaximaVisitantes;
    }

    public void setCantidadMaximaVisitantes(Integer cantidadMaximaVisitantes) {
        this.cantidadMaximaVisitantes = cantidadMaximaVisitantes;
    }

    @Basic
    @Column(name = "cantidad_maxima_por_guia")
    public Integer getCantidadMaximaPorGuia() {
        return cantidadMaximaPorGuia;
    }

    public void setCantidadMaximaPorGuia(Integer cantidadMaximaPorGuia) {
        this.cantidadMaximaPorGuia = cantidadMaximaPorGuia;
    }

    @Basic
    @Column(name = "calle_nombre")
    public String getCalleNombre() {
        return calleNombre;
    }

    public void setCalleNombre(String calleNombre) {
        this.calleNombre = calleNombre;
    }

    @Basic
    @Column(name = "calle_numero")
    public Integer getCalleNumero() {
        return calleNumero;
    }

    public void setCalleNumero(Integer calleNumero) {
        this.calleNumero = calleNumero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SedesEntity that = (SedesEntity) o;

        if (idSede != that.idSede) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (cantidadMaximaVisitantes != null ? !cantidadMaximaVisitantes.equals(that.cantidadMaximaVisitantes) : that.cantidadMaximaVisitantes != null)
            return false;
        if (cantidadMaximaPorGuia != null ? !cantidadMaximaPorGuia.equals(that.cantidadMaximaPorGuia) : that.cantidadMaximaPorGuia != null)
            return false;
        if (calleNombre != null ? !calleNombre.equals(that.calleNombre) : that.calleNombre != null) return false;
        if (calleNumero != null ? !calleNumero.equals(that.calleNumero) : that.calleNumero != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSede;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (cantidadMaximaVisitantes != null ? cantidadMaximaVisitantes.hashCode() : 0);
        result = 31 * result + (cantidadMaximaPorGuia != null ? cantidadMaximaPorGuia.hashCode() : 0);
        result = 31 * result + (calleNombre != null ? calleNombre.hashCode() : 0);
        result = 31 * result + (calleNumero != null ? calleNumero.hashCode() : 0);
        return result;
    }
}
