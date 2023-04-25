package com.ppai.aplicacion.modulos.empleado.modelo;

import javax.persistence.*;

/**
 * Clase que representa las entidades persistentes Cargos.
 */
@Entity
@Table(name = "CARGOS", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class Cargo {
    @Id
    @Column(name = "id_cargo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte idCargo;

    @Basic
    @Column(name = "nombre")
    private String nombre;

    @Basic
    @Column(name = "descripcion")
    private String descripcion;

    /**
     * Método que dice si el cargo corresponde a un "Guía".
     * @return verdadero si el nombre del cargo es "Guía", y falso en cualquier otro caso.
     */
    public boolean esGuia() {
        return nombre.equals("Guía");
    }

    /**
     * Método que dice si el cargo corresponde a un "Responsable de Visitas".
     * @return verdadero si el nombre del cargo es "Respnsable de Visitas", y falso en cualquier otro caso.
     */
    public boolean esResponsableDeVisitas() {
        return this.nombre.equals("Responsable de Visitas");
    }
}//end Cargo