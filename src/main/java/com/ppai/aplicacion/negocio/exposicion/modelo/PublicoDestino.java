package com.ppai.aplicacion.negocio.exposicion.modelo;

import javax.persistence.*;

/**
 * Clase que representa las entidades persistentes Públicos Destino.
 */
@Entity
@Table(name = "PUBLICOS_DESTINO", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class PublicoDestino {
    @Id
    @Column(name = "id_publico")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPublico;

    @Basic
    @Column(name = "nombre")
    private String nombre;

    @Basic
    @Column(name = "caracteristicas")
    private String caracteristicas;

    /**
     * Método que retorna el nombre del público destino.
     * @return el nombre del público destino como cadena de texto.
     */
    public String getNombre() {
        return nombre;
    }
}//end PublicoDestino
