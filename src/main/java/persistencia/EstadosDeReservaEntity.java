package persistencia;

import javax.persistence.*;

@Entity
@Table(name = "ESTADOS_DE_RESERVA", schema = "dbo", catalog = "museo_pictorico")
public class EstadosDeReservaEntity {
    private byte idEstadoReserva;
    private String nombre;
    private String descripcion;

    @Id
    @Column(name = "id_estado_reserva")
    public byte getIdEstadoReserva() {
        return idEstadoReserva;
    }

    public void setIdEstadoReserva(byte idEstadoReserva) {
        this.idEstadoReserva = idEstadoReserva;
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
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadosDeReservaEntity that = (EstadosDeReservaEntity) o;

        if (idEstadoReserva != that.idEstadoReserva) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) idEstadoReserva;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }
}
