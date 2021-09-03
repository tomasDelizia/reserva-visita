package com.ppai.aplicacion.negocio;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase que representa las entidades persistentes Sedes.
 */
@Entity
@Table(name = "SEDES", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class Sede {
    @Id
    @Column(name = "id_sede")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSede;

    @Basic
    @Column(name = "nombre")
    private String nombre;

    @Basic
    @Column(name = "cantidad_maxima_visitantes")
    private int cantidadMaximaVisitantes;

    @Basic
    @Column(name = "cantidad_maxima_por_guia")
    private int cantidadMaximaVisitantesPorGuia;

    @Basic
    @Column(name = "calle_nombre")
    private String calleNombre;

    @Basic
    @Column(name = "calle_numero")
    private Integer calleNumero;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "EXPOSICIONES_X_SEDES",
            joinColumns = @JoinColumn(name = "id_sede"),
            inverseJoinColumns = @JoinColumn(name = "id_exposicion"))
    private final List<Exposicion> exposicion = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "HORARIOS_DE_SEDES",
            joinColumns = @JoinColumn(name = "id_sede"),
            inverseJoinColumns = @JoinColumn(name = "id_horario"))
    private final List<HorarioSede> horarioSede = new ArrayList<>();


    public String getNombre() {
        return nombre;
    }

    /**
     * Método que busca las exposiciones temporales y vigentes de la sede.
     * @return una lista de tipo Exposicion con las exposiciones temporales y vigentes.
     */
    public List<Exposicion> listarExposicionesTemporalesYVigentes(){
        // Se inicializa una lista para almacenar dichas exposiciones.
        List<Exposicion> exposicionesTempYVig = new ArrayList<>();
        // Recorro todas las exposiciones de una sede.
        for (Exposicion expo:
                exposicion) {
            // A cada exposición le pregunto si es temporal y vigente. Si lo es, la agrego a la lista.
            if (expo.esVigenteYTemporal())
                exposicionesTempYVig.add(expo);
        }
        // Retorno la lista con las exposiciones resultantes de la iteración.
        return exposicionesTempYVig;
    }

    /**
     * Método para obtener las exposiciones temporales y vigentes de la sede con sus datos generales.
     * @return una lista de exposiciones temporales y vigentes con sus datos generales.
     */
    public String[][] buscarExposicionesTemporalesYVigentes(){
        // Se genera la lista de exposiciones temporales y vigentes.
        List<Exposicion> listaExposicionesTempYVig = listarExposicionesTemporalesYVigentes();
        // Se inicializa una lista para almacenar dichas exposiciones con sus datos generales.
        String[][] datosExposicionesTempYVig = new String[listaExposicionesTempYVig.size()][4];
        // Para cada elemento de la lista, creo un objeto exposición con sus datos generales y lo agrego a la lista.
        for (int i = 0; i < listaExposicionesTempYVig.size(); i++) {
            String[] exposicionTempYVig = {
                    listaExposicionesTempYVig.get(i).getNombre(),
                    listaExposicionesTempYVig.get(i).getPublicoDestino(),
                    listaExposicionesTempYVig.get(i).getHoraApertura(),
                    listaExposicionesTempYVig.get(i).getHoraCierre()
            };
            datosExposicionesTempYVig[i] = exposicionTempYVig;
        }
        // Retorno la lista con las exposiciones y sus datos generales.
        return datosExposicionesTempYVig;
    }

    /**
     * Método para encontrar la exposición temporal y vigente con el nombre pasado por parámetro.
     * @param nombreExposicion el nombre de la exposición temporal y vigente que deseo encontrar.
     * @return la exposición con el nombre buscado si se encuentra o nulo si no se encuentra.
     */
    public Exposicion encontrarExposicionTemporalYVigentePorNombre(String nombreExposicion) {
        // Creo la lista con las exposicione temporales y vigentes.
        List<Exposicion> listaExposicioneTemporalesYVigentes = listarExposicionesTemporalesYVigentes();
        for (Exposicion exposicion:
             listaExposicioneTemporalesYVigentes) {
            // Si el nombre coincide, devuelvo esa exposición.
            if (exposicion.esTuNombre(nombreExposicion))
                return exposicion;
        }
        // Si no se encuentra, devuelve vacío.
        return null;
    }

    /**
     * Método que calcula la duración estimada de la visita para unas exposiciones determinadas.
     * @param listaExposiciones las exposiciones que serán parte de la visita guiada.
     * @return la duración estimada de la visita en un objeto LocalTime.
     */
    public LocalTime calcularDuracionEstimadaVisita(List<Exposicion> listaExposiciones){
        // Se inicializan los contadores de horas, minutos y segundos totales.
        int horasTotales = 0;
        int minutosTotales = 0;
        int segundosTotales = 0;
        // Mientras haya exposiciones, obtenemos sus duraciones.
        for (Exposicion exposicion:
             listaExposiciones) {
            LocalTime duracionExposicion = exposicion.calcularDuracionExposicion();
            // Añadimos la duración (horas, minutos y segundos) de cada exposición a los contadores.
            horasTotales += duracionExposicion.getHour();
            minutosTotales += duracionExposicion.getMinute();
            segundosTotales += duracionExposicion.getSecond();
        }
        // Creamos un objeto duración para obtener la duración total sumando los 3 contadores.
        Duration duracionTotal = Duration.
                ofHours(horasTotales).
                plusMinutes(minutosTotales).
                plusSeconds(segundosTotales);
        // Retornamos el objeto duración convertido apropiadamente al tipo LocalTime.
        return LocalTime.of(
                duracionTotal.toHoursPart(), duracionTotal.toMinutesPart(), duracionTotal.toSecondsPart());
    }

    /**
     * Método que devuelve la cantidad de visitantes que habrá en una sede para un día y hora determinados.
     * @param fechaYHora la fecha y hora de la reserva a registrar.
     * @param duracionNuevaReserva la duración de la reserva a registrar.
     * @param listaReservas la lista de todas las reservas.
     * @return la cantidad entera de visitantes de la sede en la fecha y hora para la duración de la reserva.
     */
    public int getCantidadVisitantesParaFechaYHora(LocalDateTime fechaYHora,
                                                   LocalTime duracionNuevaReserva,
                                                   List<ReservaVisita> listaReservas) {

        // Se inicializa un contador de visitantes.
        int cantVisitantes = 0;
        // Se obtiene la fecha y hora de fin de la reserva a partir de su fecha y hora inicial y su duración.
        LocalDateTime fechaHoraFinReserva = fechaYHora
                .plusHours(duracionNuevaReserva.getHour()).plusMinutes(duracionNuevaReserva.getMinute());
        // Se iteran todas las reservas de la colección.
        for (ReservaVisita reservaVisita:
                listaReservas) {
            // Se pregunta si la reserva corresponde a esta sede y si está dentro del rango horario de la reserva nueva.
            if (reservaVisita.esTuSede(this) && reservaVisita.esEnRangoHorario(fechaYHora, fechaHoraFinReserva))
                if (reservaVisita.getCantidadAlumnosConfirmada() != null)
                    cantVisitantes += reservaVisita.getCantidadAlumnosConfirmada();
                else
                    cantVisitantes += reservaVisita.getCantidadAlumnos();
        }
        return cantVisitantes;
    }

    /**
     * Método que indica si la cantidad de visitantes pasada por parámetro junto con la cantidad de visitantes que
     * hay en las visitas confirmadas de la sede supera el límite de capacidad para la sede.
     * @param cantidadVisitantesReserva la cantidad de visitantes de la reserva a registrar.
     * @param fechaYHora la fecha y hora de la reserva a registrar.
     * @param duracionReserva la duración de la reserva a registrar.
     * @param reservasDeVisita la lista de todas las reservas.
     * @return verdadero si la cantidad de visitantes de la reserva nueva supera la capacidad de la sede para la
     * cantidad de reservas confirmadas de la sede, y falso en cualquier otro caso.
     */
    public boolean superaLimiteVisitantesParaFechaYHora(int cantidadVisitantesReserva,
                                                        LocalDateTime fechaYHora,
                                                        LocalTime duracionReserva,
                                                        List<ReservaVisita> reservasDeVisita){
        return cantidadVisitantesReserva + getCantidadVisitantesParaFechaYHora(
                fechaYHora, duracionReserva, reservasDeVisita)
                > cantidadMaximaVisitantes;
    }

    /**
     * Método que determina si el empleado pasado por parámetro pertenece a la sede.
     * @param unEmpleado el empleado a determinar si pertenece a la sede.
     * @return verdadero si el empleado pertenece a esta sede, y falso en cualquier otro caso.
     */
    public boolean esTuEmpleado(Empleado unEmpleado) {
        return unEmpleado.esTuSede(this);
    }

    /**
     * Método para buscar los guías disponibles para una sede en una fecha y hora pasadas por parámetro.
     * @param fechaYHora la fecha y hora de la reserva nueva.
     * @param empleados la lista de todos los empleados.
     * @param asignacionesGuia la lista de todas las asignaciones de guía.
     * @return la lista de todos los guías disponibles para el horario de la reserva nueva.
     */
	public List<Empleado> listarGuiasDisponiblesPorHorarioDeReserva(LocalDateTime fechaYHora,
                                                                    List<Empleado> empleados,
                                                                    List<AsignacionGuia> asignacionesGuia) {
	    // Iniciamos la lista donde se almacenarán todos los guías.
		List<Empleado> guiasDisponibles = new ArrayList<>();
        for (Empleado empleado:
			 empleados) {
            /* Si el empleado pertenece a la sede, es guía, trabaja y no tiene asignaciones en el día y hora de
               la reserva, se añade a la lista. */
			if (this.esTuEmpleado(empleado)
                    && empleado.esGuia()
                    && empleado.trabajaDentroDeDiaYHorario(fechaYHora)
                    && !empleado.tieneAsignacionParaDiaYHora(fechaYHora, asignacionesGuia))
			    guiasDisponibles.add(empleado);
		}
		return guiasDisponibles;
	}

    /**
     * Método para obtener las los guías disponibles de la sede con sus datos generales para un horario determinado.
     * @param fechaYHora la fecha y hora de la reserva nueva.
     * @param empleados la lista de todos los empleados.
     * @param asignacionesGuia la lista de todas las asignaciones de guía.
     * @return una lista de guías disponibles para un horario determinado con sus datos generales.
     */
    public String[][] buscarGuiasDisponiblesPorHorarioDeReserva(LocalDateTime fechaYHora,
                                                                List<Empleado> empleados,
                                                                List<AsignacionGuia> asignacionesGuia) {
        List<Empleado> listaGuiasDisponibles =
                listarGuiasDisponiblesPorHorarioDeReserva(fechaYHora,empleados, asignacionesGuia);
        String[][] datosGuiasDisponibles = new String[listaGuiasDisponibles.size()][2];
        for (int i = 0; i < listaGuiasDisponibles.size(); i++) {
            String[] guiaDisponible = {
                    listaGuiasDisponibles.get(i).getNombre(),
                    listaGuiasDisponibles.get(i).getApellido(),
            };
            datosGuiasDisponibles[i] = guiaDisponible;
        }
        return datosGuiasDisponibles;
    }

    /**
     * Método que calcula los guías necesarios para la cantidad de visitantes de la reserva a registra.
     * @param cantidadVisitantes la cantidad e visitantes de la reserva nueva.
     * @return la cantidad de guías necesarios para realizar la reserva.
     */
	public int calcularGuiasNecesariosParaVisitantesIngresados(int cantidadVisitantes) {
        if (cantidadVisitantes / cantidadMaximaVisitantesPorGuia == 0)
            return 1;
        else
            return cantidadVisitantes / cantidadMaximaVisitantesPorGuia;
    }
}//end Sede
