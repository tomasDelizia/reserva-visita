package com.ppai.aplicacion.negocio;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa las entidades persistentes Exposiciones.
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
    private final List<PublicoDestino> publicoDestino = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "idExposicion")
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

    /**
     * Método que devuelve el nombre de la exposición.
     *
     * @return el nombre de la exposición en formato de cadena de texto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que devuelve la hora de apertura de la exposición.
     *
     * @return la hora de apertura de la exposición en formato de cadena de texto.
     */
    public String getHoraApertura() {
        return horaApertura.toString();
    }

    /**
     * Método que devuelve la hora de cierre de la exposición.
     * @return la hora de cierre de la exposición en formato de cadena de texto.
     */
    public String getHoraCierre() {
        return horaCierre.toString();
    }

    /**
     * Método que devuelve los públicos destino de la exposición.
     * @return todos los públicos destino en formato de cadena de texto, separados por un salto de línea.
     */
    public String getPublicoDestino() {
        StringBuilder stringBuilder = new StringBuilder("");
        for (PublicoDestino publicoDestino:
             publicoDestino) {
            stringBuilder.append(publicoDestino.getNombre()).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Método para saber si la exposición es vigente y de tipo "Temporal".
     * @return verdadero si la exposición es vigente y "Temporal", y falso en cualquier otro caso.
     */
    public boolean esVigenteYTemporal() {
        return esTemporal() && esVigente();
    }

    /**
     * Método para saber si la exposición es "Temporal".
     * @return verdadero si el tipo de exposición de la misma es "Temporal", y falso en cualquier otro caso.
     */
    public boolean esTemporal() {
        return tipoExposicion.esTemporal();
    }

    /**
     * Método para saber si la exposición es vigente.
     * @return verdadero si la fecha de fin de una exposición (o fecha de fin de una exposición replanificada)
     * es posterior a la fecha actual, y falso en cualquier otro caso.
     */
    public boolean esVigente() {
        // Primero, se obtiene la fecha actual:
        LocalDate fechaActual = LocalDate.now();
        // Si no fue replanificada, pregunto si la fechaActual es menor a la fechaFin:
        if (fechaFinReplanificada == null)
            return fechaActual.compareTo(fechaFin) <= 0;
        // Si fue replanificada, pregunto si la fechaActual es menor a la fechaFin replanificada:
        else return (fechaActual.compareTo(fechaInicioReplanificada) >= 0 &&
                fechaActual.compareTo(fechaFinReplanificada) <= 0);
    }

    /**
     * Método para obtener la duración de una exposición.
     * @return la duración en horas, minutos y segundos en un objeto de tipo LocalTime de la exposición.
     */
    public LocalTime calcularDuracionExposicion() {
        // Se inicializan contadores para horas, minutos y segundos:
        int horasTotales = 0;
        int minutosTotales = 0;
        int segundosTotales = 0;
        // Mientras la exposición tenga detalles de exposición, obtenemos su duración extendida:
        for (DetalleExposicion detalleExpo:
             detalleExposicion) {
            LocalTime duracionObra = detalleExpo.getObra().getDuracionExtendida();
            // Añadimos la duración (horas, minutos y segundos) de cada detalle a los contadores:
            horasTotales += duracionObra.getHour();
            minutosTotales += duracionObra.getMinute();
            segundosTotales += duracionObra.getSecond();
        }
        // Creamos un objeto duración para obtener la duración total sumando los 3 contadores:
        Duration duracionTotal = Duration.
                ofHours(horasTotales).
                plusMinutes(minutosTotales).
                plusSeconds(segundosTotales);
        // Retornamos el objeto duración convertido apropiadamente al tipo LocalTime:
        return LocalTime.of(
                duracionTotal.toHoursPart(), duracionTotal.toMinutesPart(), duracionTotal.toSecondsPart());
    }

    /**
     * Método para saber si la exposición tiene el mismo nombre que el pasado por parámetro.
     * @return verdadero si su nombre coincide con el pasado por parámetro, y falso en cualquier otro caso.
     */
    public boolean esTuNombre(String nombreExposicion) {
        return nombre.equals(nombreExposicion);
    }
}//end Exposicion