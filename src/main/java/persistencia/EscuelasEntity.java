package persistencia;

import javax.persistence.*;

@Entity
@Table(name = "ESCUELAS", schema = "dbo", catalog = "museo_pictorico")
public class EscuelasEntity {
    private int idEscuela;
    private String nombre;
    private String mail;
    private String calleNombre;
    private Integer calleNumero;
    private String telefonoCelular;
    private Long telefonoFijo;

    @Id
    @Column(name = "id_escuela")
    public int getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
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
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
    @Column(name = "telefono_celular")
    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    @Basic
    @Column(name = "telefono_fijo")
    public Long getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(Long telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EscuelasEntity that = (EscuelasEntity) o;

        if (idEscuela != that.idEscuela) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (calleNombre != null ? !calleNombre.equals(that.calleNombre) : that.calleNombre != null) return false;
        if (calleNumero != null ? !calleNumero.equals(that.calleNumero) : that.calleNumero != null) return false;
        if (telefonoCelular != null ? !telefonoCelular.equals(that.telefonoCelular) : that.telefonoCelular != null)
            return false;
        if (telefonoFijo != null ? !telefonoFijo.equals(that.telefonoFijo) : that.telefonoFijo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEscuela;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (calleNombre != null ? calleNombre.hashCode() : 0);
        result = 31 * result + (calleNumero != null ? calleNumero.hashCode() : 0);
        result = 31 * result + (telefonoCelular != null ? telefonoCelular.hashCode() : 0);
        result = 31 * result + (telefonoFijo != null ? telefonoFijo.hashCode() : 0);
        return result;
    }
}
