package persistencia;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "EMPLEADOS", schema = "dbo", catalog = "museo_pictorico")
public class EmpleadosEntity {
    private int idEmpleado;
    private Integer dni;
    private Integer cuit;
    private String nombre;
    private String apellido;
    private String mail;
    private Date fechaNacimiento;
    private Date fechaIngreso;
    private String calleNombre;
    private Integer calleNumero;
    private String telefono;
    private String codigoValidacion;

    @Id
    @Column(name = "id_empleado")
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Basic
    @Column(name = "dni")
    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    @Basic
    @Column(name = "cuit")
    public Integer getCuit() {
        return cuit;
    }

    public void setCuit(Integer cuit) {
        this.cuit = cuit;
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
    @Column(name = "apellido")
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Basic
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "fecha_nacimiento")
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Basic
    @Column(name = "fecha_ingreso")
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
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

    @Basic
    @Column(name = "telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "codigo_validacion")
    public String getCodigoValidacion() {
        return codigoValidacion;
    }

    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpleadosEntity that = (EmpleadosEntity) o;

        if (idEmpleado != that.idEmpleado) return false;
        if (dni != null ? !dni.equals(that.dni) : that.dni != null) return false;
        if (cuit != null ? !cuit.equals(that.cuit) : that.cuit != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellido != null ? !apellido.equals(that.apellido) : that.apellido != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (fechaNacimiento != null ? !fechaNacimiento.equals(that.fechaNacimiento) : that.fechaNacimiento != null)
            return false;
        if (fechaIngreso != null ? !fechaIngreso.equals(that.fechaIngreso) : that.fechaIngreso != null) return false;
        if (calleNombre != null ? !calleNombre.equals(that.calleNombre) : that.calleNombre != null) return false;
        if (calleNumero != null ? !calleNumero.equals(that.calleNumero) : that.calleNumero != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;
        if (codigoValidacion != null ? !codigoValidacion.equals(that.codigoValidacion) : that.codigoValidacion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEmpleado;
        result = 31 * result + (dni != null ? dni.hashCode() : 0);
        result = 31 * result + (cuit != null ? cuit.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellido != null ? apellido.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (fechaNacimiento != null ? fechaNacimiento.hashCode() : 0);
        result = 31 * result + (fechaIngreso != null ? fechaIngreso.hashCode() : 0);
        result = 31 * result + (calleNombre != null ? calleNombre.hashCode() : 0);
        result = 31 * result + (calleNumero != null ? calleNumero.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (codigoValidacion != null ? codigoValidacion.hashCode() : 0);
        return result;
    }
}
