package com.ppai.aplicacion.negocio;

import javax.persistence.*;

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


    public byte getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(byte idCargo) {
        this.idCargo = idCargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    public boolean esGuia() {
        return this.nombre.equals("Guía");
    }
}//end Cargo