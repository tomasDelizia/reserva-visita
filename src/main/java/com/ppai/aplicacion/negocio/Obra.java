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

    public LocalTime getDuracionResumida() {
        return duracionResumida;
    }

    public void setDuracionResumida(LocalTime duracionResumida) {
        this.duracionResumida = duracionResumida;
    }

    public LocalTime getDuracionExtendida() {
        return duracionExtendida;
    }

    public void setDuracionExtendida(LocalTime duracionExtendida) {
        this.duracionExtendida = duracionExtendida;
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
