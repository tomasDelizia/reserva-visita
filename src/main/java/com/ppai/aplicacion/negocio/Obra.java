package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase que representa las entidades persistentes Obras.
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


    /**
     * Método que devuelve la duración extendida de la obra.
     * @return la duración extendida de la obra en formato de fecha.
     */
    public LocalTime getDuracionExtendida() {
        return duracionExtendida;
    }
}//end Obra
