package com.ppai.aplicacion.persistencia;

import javax.persistence.*;

@Entity
@Table(name = "ESCUELAS", schema = "dbo", catalog = "museo_pictorico")
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
    private Long telefonoFijo;


    public Escuela() {}

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
}
