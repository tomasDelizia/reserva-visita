package com.ppai.aplicacion.negocio;

import javax.persistence.*;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:18 am
 */

@Entity
@Table(name = "ESCUELAS", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class Escuela {
    @Id
    @Column(name = "id_escuela")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEscuela;

    @Basic
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Basic
    @Column(name = "mail")
    private String mail;

    @Basic
    @Column(name = "calle_nombre")
    private String calleNombre;

    @Basic
    @Column(name = "calle_numero")
    private Integer calleNumero;

    @Basic
    @Column(name = "telefono_celular")
    private String telefonoCelular;

    @Basic
    @Column(name = "telefono_fijo")
    private String telefonoFijo;


    public Escuela() {}

    public Escuela(String nombre, String mail, String calleNombre, Integer calleNumero,
                   String telefonoCelular, String telefonoFijo) {
        this.nombre = nombre;
        this.mail = mail;
        this.calleNombre = calleNombre;
        this.calleNumero = calleNumero;
        this.telefonoCelular = telefonoCelular;
        this.telefonoFijo = telefonoFijo;
    }
    public Escuela getEscuela() {return this;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}//end Escuela
