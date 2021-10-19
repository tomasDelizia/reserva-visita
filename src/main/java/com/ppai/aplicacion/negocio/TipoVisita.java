package com.ppai.aplicacion.negocio;

import javax.persistence.*;

/**
 * Clase que representa las entidades persistentes Tipos de Visita.
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

    /**
     * Método que devuelve el nombre del tipo de visita.
     * @return el nombre del tipo de visita como cadena de texto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para saber si el tipo de visita es "Por Exposición".
     * @return verdadero si el tipo de visita es "Por Exposición", y falso en cualquier otro caso.
     */
    public boolean esPorExposicion() {
        return nombre.equals("Por Exposición");
    }

    /**
     * Método para saber si el tipo de visita es "Completa".
     * @return verdadero si el tipo de visita es "Completa", y falso en cualquier otro caso.
     */
    public boolean esCompleta() {
        return nombre.equals("Completa");
    }
}//end TipoDeVisita
