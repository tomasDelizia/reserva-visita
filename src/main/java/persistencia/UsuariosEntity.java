package persistencia;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "USUARIOS", schema = "dbo", catalog = "museo_pictorico")
public class UsuariosEntity {
    private int idUsuario;
    private String nombreUsuario;
    private String contrasena;
    private Date caducidad;

    @Id
    @Column(name = "id_usuario")
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Basic
    @Column(name = "nombre_usuario")
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Basic
    @Column(name = "contrasena")
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Basic
    @Column(name = "caducidad")
    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuariosEntity that = (UsuariosEntity) o;

        if (idUsuario != that.idUsuario) return false;
        if (nombreUsuario != null ? !nombreUsuario.equals(that.nombreUsuario) : that.nombreUsuario != null)
            return false;
        if (contrasena != null ? !contrasena.equals(that.contrasena) : that.contrasena != null) return false;
        if (caducidad != null ? !caducidad.equals(that.caducidad) : that.caducidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUsuario;
        result = 31 * result + (nombreUsuario != null ? nombreUsuario.hashCode() : 0);
        result = 31 * result + (contrasena != null ? contrasena.hashCode() : 0);
        result = 31 * result + (caducidad != null ? caducidad.hashCode() : 0);
        return result;
    }
}
