package com.ppai.aplicacion.negocio;


import javax.persistence.*;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:25 am
 */

@Entity
@Table(name = "TIPOS_DE_VISITA", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class TipoVisita {
    @Id
    @Column(name = "id_tipo_visita")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte idTipoVisita;

    @Basic
    @Column(name = "nombre", nullable = false)
    private String nombre;


    public TipoVisita() {}

    public TipoVisita(String nombre) {
        this.nombre = nombre;
    }

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

    public boolean esCompleta() {
        return nombre.equals("Completa");
    }

    public boolean esPorExposicion() {
        return nombre.equals("Por Exposición");
    }


}//end TipoDeVisita
