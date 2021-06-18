package com.ppai.aplicacion.negocio;


import javax.persistence.*;

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

    public boolean esTipoDeVisita(String nombreTipoVisita) {return nombre.equals(nombreTipoVisita);}


}//end TipoDeVisita
