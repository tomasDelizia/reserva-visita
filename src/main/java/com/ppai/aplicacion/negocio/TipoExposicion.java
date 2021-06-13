package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.util.Objects;
/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:39 am
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


    @Override
    public String toString() {
        return "TipoExposicion{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    public byte getIdTipoExposicion() {
        return idTipoExposicion;
    }

    public void setIdTipoExposicion(byte idTipoExposicion) {
        this.idTipoExposicion = idTipoExposicion;
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

    public boolean esPermanente(){
        return this.nombre.equals("Permanente");
    }

    public boolean esTemporal(){
        return this.nombre.equals("Temporal");
    }

}//end TipoExposicion
