package com.ppai.aplicacion.negocio;

import javax.persistence.*;

/**
 * Clase que representa las entidades persistentes Detalles de Exposición.
 */
@Entity
@Table(name = "DETALLES_DE_EXPOSICION", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class DetalleExposicion {
    @Id
    @Column(name = "id_exposicion")
    private int idExposicion;

    @OneToOne
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra")
    private Obra obra;

    @Basic
    @Column(name = "lugar_asignado")
    private Integer lugarAsignado;

    public Obra getObra() {
        return obra;
    }
}//end DetalleExposicion
