package com.ppai.aplicacion.negocio;

import javax.persistence.*;

@Entity
@Table(name = "DIAS_DE_SEMANA", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class DiaSemana {
    @Id
    @Column(name = "id_dia")
    private byte idDia;

    @Basic
    @Column(name = "nombre")
    private String nombre;


    @Override
    public String toString() {
        return "DiaSemana{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
