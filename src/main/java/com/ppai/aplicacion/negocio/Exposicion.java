package com.ppai.aplicacion.negocio;



import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.javatuples.Pair;
import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


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
    private final List<PublicoDestino> publicoDestino = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="idExposicion")
    private final List<DetalleExposicion> detalleExposicion = new ArrayList<>();

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
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", fechaInicioReplanificada=" + fechaInicioReplanificada +
                ", fechaFinReplanificada=" + fechaFinReplanificada +
                '}';
    }

    public boolean esVigenteYTemporal() {
        // Devuelve verdadero si la exposición es vigente y temporal:
        return esTemporal() && esVigente();
    }

    public boolean esTemporal() {
        return tipoExposicion.esTemporal();
    }

    public boolean esVigente() {
        // Devuelve verdadero si la exposición es vigente. Primero, se obtiene la fecha actual:
        LocalDate fechaActual = LocalDate.now();
        // Si no fue replanificada, pregunto si la fechaActual es menor a la fechaFin original:
        if (fechaFinReplanificada == null)
            return fechaActual.compareTo(fechaFin) <= 0;
        // Si fue replanificada, pregunto si la fechaActual es menor a la fechaFin replanificada:
        else return (fechaActual.compareTo(fechaInicioReplanificada) >= 0 &&
                fechaActual.compareTo(fechaFinReplanificada) <= 0);
    }

    public List<String> getNombresPublicoDestino() {
        // Método que retorna una lista con todos los nombres de los públicos destino de la exposición.
        // Inicialización de lista que contendrá los nombres:
        List<String> nombresPublicos = new ArrayList<>();
        // Iteramos mientras la exposición tenga públicos destino asociados
        for (PublicoDestino publicoDest:
                publicoDestino) {
            // Si la lista aún no contiene el nombre del público destino de la iteración,
            // lo agrego a la lista:
            if (!nombresPublicos.contains(publicoDest.getNombre()))
                nombresPublicos.add(publicoDest.getNombre());
        }
        return nombresPublicos;
    }

    public Pair<LocalTime, LocalTime> getHorarioExposicionTemporal () {
        // Método para obtener los horarios en los que funciona una exposición temporal.
        // Añadimos sus horarios de apertura y cierre de una exposición temporal a una tupla de horarios:
        Pair<LocalTime, LocalTime> horarios;
        horarios = Pair.with(horaApertura, horaCierre);
        return horarios;
    }

    public LocalTime calcularDuracionExposicion() {
        // Método para obtener la duración en horas, minutos y segundos de una exposición.
        int horasTotales = 0;
        int minutosTotales = 0;
        int segundosTotales = 0;
        // Mientras la obra tenga exposiciones, obtenemos su duración extendida:
        for (DetalleExposicion detalleExpo:
             detalleExposicion) {
            LocalTime duracionObra = detalleExpo.getObra().getDuracionExtendida();
            segundosTotales += duracionObra.getHour();
            minutosTotales += duracionObra.getMinute();
            segundosTotales += duracionObra.getSecond();
        }
        return LocalTime.parse(horasTotales + ":" + minutosTotales + ":" + segundosTotales);
    }

}//end Exposicion
