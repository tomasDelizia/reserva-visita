package com.ppai.aplicacion.modulos.visita_programada.modelo;

import javax.persistence.*;

/**
 * Clase que representa las entidades persistentes Escuelas.
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


    public String getNombre() {
        return nombre;
    }
}//end Escuela
