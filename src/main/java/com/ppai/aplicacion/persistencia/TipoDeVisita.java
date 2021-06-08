package com.ppai.aplicacion.persistencia;

import javax.persistence.*;

@Entity
@Table(name = "TIPOS_DE_VISITA", schema = "dbo", catalog = "museo_pictorico")
public class TipoDeVisita {
    @Id
    @Column(name = "id_tipo_visita")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte idTipoVisita;

    @Basic
    @Column(name = "nombre", nullable = false)
    private String nombre;


    public TipoDeVisita() {}

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
