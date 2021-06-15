package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    public int getIdPublico() {
        return idPublico;
    }

    public void setIdPublico(int idPublico) {
        this.idPublico = idPublico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public String toString() {
        return "PublicoDestino{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}//end PublicoDestino
