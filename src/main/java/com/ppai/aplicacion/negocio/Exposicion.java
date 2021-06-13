package com.ppai.aplicacion.negocio;



import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:44:08 am
 */
@Entity
@Table(name = "EXPOSICIONES", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class Exposicion {
    @Id
    @Column(name = "id_exposicion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idExposicion;

    @Basic
    @Column(name = "nombre")
    private String nombre;

    @OneToOne
    @JoinColumn(name = "id_tipo_exposicion", referencedColumnName = "id_tipo_exposicion")
    private TipoExposicion tipoExposicion;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "PUBLICOS_X_EXPOSICIONES",
            joinColumns = @JoinColumn(name = "id_exposicion"),
            inverseJoinColumns = @JoinColumn(name = "id_publico"))
    private List<PublicoDestino> publicoDestino;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="idExposicion")
    private List<DetalleExposicion> detalleExposicion;

    @Basic
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Basic
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Basic
    @Column(name = "fecha_inicio_replanificada")
    private LocalDate fechaInicioReplanificada;

    @Basic
    @Column(name = "fecha_fin_replanificada")
    private LocalDate fechaFinReplanificada;

    @Basic
    @Column(name = "hora_apertura")
    private LocalTime horaApertura;

    @Basic
    @Column(name = "hora_cierre")
    private LocalTime horaCierre;

    @OneToOne
    @JoinColumn(name = "id_empleado_creador", referencedColumnName = "id_empleado")
    private Empleado empleadoCreo;


    public int getIdExposicion() {
        return idExposicion;
    }

    public void setIdExposicion(int idExposicion) {
        this.idExposicion = idExposicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaInicioReplanificada() {
        return fechaInicioReplanificada;
    }

    public void setFechaInicioReplanificada(LocalDate fechaInicioReplanificada) {
        this.fechaInicioReplanificada = fechaInicioReplanificada;
    }

    public LocalDate getFechaFinReplanificada() {
        return fechaFinReplanificada;
    }

    public void setFechaFinReplanificada(LocalDate fechaFinReplanificada) {
        this.fechaFinReplanificada = fechaFinReplanificada;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }


    @Override
    public String toString() {
        return "Exposicion{" +
                "nombre='" + nombre + '\'' +
                ", tipoExposicion=" + tipoExposicion +
                ", publicoDestino=" + publicoDestino +
                ", detalleExposicion=" + detalleExposicion +
                '}';
    }

    public boolean esVigenteYTemporal() {
        return this.tipoExposicion.esTemporal() && this.esVigente();
    }

    public boolean esVigente() {
        LocalDate ld = LocalDate.now();
        if (this.fechaFinReplanificada == null)
            return this.fechaFin.compareTo(ld) >= 0;
        else
            return this.fechaFinReplanificada.compareTo(ld) >= 0;
    }

//    public List<String> getNombresPublicoDestino() {
//        // Método que retorna una lista con todos los nombres de los públicos destino de la exposición
//        // Inicialización de lista que contendrá los nombres
//        List<String> nombresPublicos = new ArrayList<>();
//        // Iteramos mientras la exposición tenga públicos destino asociados
//        for (PublicoDestino publicoDest:
//                publicoDestino) {
//            nombresPublicos.add(publicoDest.getNombre());
//        }
//        return nombresPublicos;
//    }

    public List<LocalTime> getHorarioExposicionTemporal () {
        // Método para obtener los horarios en los que funciona una exposición temporal
        List<LocalTime> horario = new ArrayList<>();
        // Si la exposición es temporal, añadimos sus horarios de apertura y cierre a una lista de horarios
        if (tipoExposicion.esTemporal()) {
            horario.add(horaApertura);
            horario.add(horaCierre);
        }
        return horario;
    }

//	public int calcularDuracionExposicion() {
//		// Método para obtener la duración en minutos de una exposición
//		int duracion = 0;
//		for (DetalleExposicion de:
//			 detalleExposicion) {
//			duracion += de.getObra().getDuracionExtendida();
//		}
//		return duracion;
//	}

//    public Duration calcularDuracionExposicion(TipoVisita tipoVisita) {
//        // Método para obtener la duración en minutos y segundos de una exposición
//        // Inicializamos una duración de 00hs 00min 00seg
//        Duration duracionExposicion = Duration.parse("00:00:00");
//        // Mientras la obra tenga exposiciones, obtenemos su duración
//        if (tipoVisita.esCompleta()) {
//            for (DetalleExposicion detalleExpo:
//                    detalleExposicion) {
//                Duration duracionExtendida = detalleExpo.getObra().getDuracionExtendida();
//                duracionExposicion.plus(duracionExtendida);
//            }
//        }
//        return duracionExposicion;
//    }

}//end Exposicion
