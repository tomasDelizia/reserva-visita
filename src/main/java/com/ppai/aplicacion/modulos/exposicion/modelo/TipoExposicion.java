package com.ppai.aplicacion.modulos.exposicion.modelo;

import javax.persistence.*;

/**
 * Clase que representa las entidades de Tipos de Exposición.
 */
@Entity
@Table(name = "TIPOS_DE_EXPOSICION", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class TipoExposicion {
    @Id
    @Column(name = "id_tipo_exposicion")
    private byte idTipoExposicion;

    @Basic
    @Column(name = "nombre")
    private String nombre;

    @Basic
    @Column(name = "descripcion")
    private String descripcion;


    /**
     * Método para saber si el tipo de exposición es "Temporal".
     * @return devuelve verdadero si el tipo de exposición de la misma es "Temporal", y falso en cualquier otro caso.
     */
    public boolean esTemporal(){
        return this.nombre.equals("Temporal");
    }

}//end TipoExposicion
