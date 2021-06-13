package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:41 am
 */
@Entity
@Table(name = "OBRAS", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class Obra {
    @Id
    @Column(name = "id_obra")
    private int idObra;

    @Basic
    @Column(name = "nombre_obra")
    private String nombreObra;

    @Basic
    @Column(name = "alto")
    private BigDecimal alto;

    @Basic
    @Column(name = "ancho")
    private BigDecimal ancho;

    @Basic
    @Column(name = "peso")
    private BigDecimal peso;

    @Basic
    @Column(name = "valuacion")
    private BigDecimal valuacion;

    @Basic
    @Column(name = "duracion_resumida")
    private LocalTime duracionResumida;

    @Basic
    @Column(name = "duracion_extendida")
    private LocalTime duracionExtendida;

    @Basic
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Basic
    @Column(name = "fecha_primer_ingreso")
    private LocalDate fechaPrimerIngreso;

    @Basic
    @Column(name = "fecha_registracion")
    private LocalDate fechaRegistracion;

    @Basic
    @Column(name = "descripcion")
    private String descripcion;

    @Basic
    @Column(name = "codigo_sensor")
    private Integer codigoSensor;

    @OneToOne
    @JoinColumn(name = "id_empleado_creador", referencedColumnName = "id_empleado")
    private Empleado empleadoCreo;


    @Override
    public String toString() {
        return "Obra{" +
                "nombreObra='" + nombreObra + '\'' +
                ", valuacion= $" + valuacion +
                ", duracionResumida=" + duracionResumida +
                ", duracionExtendida=" + duracionExtendida +
                '}';
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public String getNombreObra() {
        return nombreObra;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    public BigDecimal getAlto() {
        return alto;
    }

    public void setAlto(BigDecimal alto) {
        this.alto = alto;
    }

    public BigDecimal getAncho() {
        return ancho;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getValuacion() {
        return valuacion;
    }

    public void setValuacion(BigDecimal valuacion) {
        this.valuacion = valuacion;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCodigoSensor() {
        return codigoSensor;
    }

    public void setCodigoSensor(Integer codigoSensor) {
        this.codigoSensor = codigoSensor;
    }

    public void newCambioEstado(){

    }

    public void estaDiponibleParaExposicionEnPeriodo(){

    }

    public void estaEnDeposito(){

    }

    public void estaEnExposicion(){

    }

    public void estaParaRestauracion(){

    }

    public void registrarBaja(){

    }

    public void registrarPendienteAsignacionDeposito(){

    }


}//end Obra
