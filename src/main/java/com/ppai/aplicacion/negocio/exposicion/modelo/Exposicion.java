package com.ppai.aplicacion.negocio.exposicion.modelo;

import com.ppai.aplicacion.negocio.empleado.modelo.Empleado;
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
     * M??todo que devuelve el id de la exposici??n.
     * @return el id de la exposici??n.
     */
    public int getIdExposicion() {
        return idExposicion;
    }

    /**
     * M??todo que devuelve el nombre de la exposici??n.
     * @return el nombre de la exposici??n.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * M??todo que devuelve la hora de apertura de la exposici??n.
     * @return la hora de apertura de la exposici??n.
     */
    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    /**
     * M??todo que devuelve la hora de cierre de la exposici??n.
     * @return la hora de cierre de la exposici??n.
     */
    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    /**
     * M??todo que devuelve los p??blicos destino de la exposici??n.
     * @return todos los p??blicos destino en formato de cadena de texto, separados por un salto de l??nea.
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
     * M??todo para saber si la exposici??n es vigente y de tipo "Temporal".
     * @return verdadero si la exposici??n es vigente y "Temporal", y falso en cualquier otro caso.
     */
    public boolean esVigenteYTemporal() {
        return esTemporal() && esVigente();
    }

    /**
     * M??todo para saber si la exposici??n es "Temporal".
     * @return verdadero si el tipo de exposici??n de la misma es "Temporal", y falso en cualquier otro caso.
     */
    public boolean esTemporal() {
        return tipoExposicion.esTemporal();
    }

    /**
     * M??todo para saber si la exposici??n es vigente.
     * @return verdadero si la fecha de fin de una exposici??n (o fecha de fin de una exposici??n replanificada)
     * es posterior a la fecha actual, y falso en cualquier otro caso.
     */
    public boolean esVigente() {
        // Primero, se obtiene la fecha actual.
        LocalDate fechaActual = LocalDate.now();
        // Si no fue replanificada, pregunto si la fechaActual es menor a la fechaFin.
        if (fechaFinReplanificada == null)
            return fechaActual.compareTo(fechaFin) <= 0;
        // Si fue replanificada, pregunto si la fechaActual es menor a la fechaFin replanificada.
        else return (fechaActual.compareTo(fechaInicioReplanificada) >= 0 &&
                fechaActual.compareTo(fechaFinReplanificada) <= 0);
    }

    /**
     * M??todo para obtener la duraci??n extendida de una exposici??n.
     * @return la duraci??n extendida en horas, minutos y segundos en un objeto de tipo LocalTime de la exposici??n.
     */
    public LocalTime calcularDuracionExtendida() {
        // Se inicializan contadores para horas, minutos y segundos.
        int horasTotales = 0;
        int minutosTotales = 0;
        int segundosTotales = 0;
        // Mientras la exposici??n tenga detalles de exposici??n, obtenemos su duraci??n extendida.
        for (DetalleExposicion detalleExpo:
             detalleExposicion) {
            LocalTime duracionObra = detalleExpo.getDuracionExtendida();
            // A??adimos la duraci??n (horas, minutos y segundos) de cada detalle a los contadores.
            horasTotales += duracionObra.getHour();
            minutosTotales += duracionObra.getMinute();
            segundosTotales += duracionObra.getSecond();
        }
        // Creamos un objeto duraci??n para obtener la duraci??n total sumando los 3 contadores.
        Duration duracionTotal = Duration.
                ofHours(horasTotales).
                plusMinutes(minutosTotales).
                plusSeconds(segundosTotales);
        // Retornamos el objeto duraci??n convertido apropiadamente al tipo LocalTime.
        return LocalTime.of(
                duracionTotal.toHoursPart(), duracionTotal.toMinutesPart(), duracionTotal.toSecondsPart());
    }

    /**
     * M??todo para obtener la duraci??n resumida de una exposici??n.
     * @return la duraci??n resumida en horas, minutos y segundos en un objeto de tipo LocalTime de la exposici??n.
     */
    public LocalTime calcularDuracionResumida() {
        // Se inicializan contadores para horas, minutos y segundos.
        int horasTotales = 0;
        int minutosTotales = 0;
        int segundosTotales = 0;
        // Mientras la exposici??n tenga detalles de exposici??n, obtenemos su duraci??n extendida.
        for (DetalleExposicion detalleExpo:
                detalleExposicion) {
            LocalTime duracionObra = detalleExpo.getDuracionResumida();
            // A??adimos la duraci??n (horas, minutos y segundos) de cada detalle a los contadores.
            horasTotales += duracionObra.getHour();
            minutosTotales += duracionObra.getMinute();
            segundosTotales += duracionObra.getSecond();
        }
        // Creamos un objeto duraci??n para obtener la duraci??n total sumando los 3 contadores.
        Duration duracionTotal = Duration.
                ofHours(horasTotales).
                plusMinutes(minutosTotales).
                plusSeconds(segundosTotales);
        // Retornamos el objeto duraci??n convertido apropiadamente al tipo LocalTime.
        return LocalTime.of(
                duracionTotal.toHoursPart(), duracionTotal.toMinutesPart(), duracionTotal.toSecondsPart());
    }

    /**
     * M??todo para saber si la exposici??n tiene el mismo id que el pasado por par??metro.
     * @return verdadero si su id coincide con el pasado por par??metro, y falso en cualquier otro caso.
     */
    public boolean esTuId(int idExposicion) {
        return this.idExposicion == idExposicion;
    }
}//end Exposicion