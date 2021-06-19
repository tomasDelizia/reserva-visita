package com.ppai.aplicacion.negocio;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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


    @Override
    public String toString() {
        return nombre;
    }

    public List<Exposicion> getExposicion() {
        return exposicion;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadMaximaVisitantes() {
        return cantidadMaximaVisitantes;
    }

    public void setCantidadMaximaVisitantes(int cantidadMaximaVisitantes) {
        this.cantidadMaximaVisitantes = cantidadMaximaVisitantes;
    }

    public int getCantidadMaximaVisitantesPorGuia() {
        return cantidadMaximaVisitantesPorGuia;
    }

    public void setCantidadMaximaVisitantesPorGuia(int cantidadMaximaPorGuia) {
        this.cantidadMaximaVisitantesPorGuia = cantidadMaximaPorGuia;
    }

    public List<Exposicion> buscarExposicionesTemporalesYVigentes(){
        // Método para encontrar las exposiciones temporales y vigentes de una sede.
        // Se inicializa una lista para almacenar dichas exposiciones:
        List<Exposicion> exposicionesTempYVig = new ArrayList<>();
        // Recorro todas las exposiciones de una sede:
        for (Exposicion expo:
                exposicion) {
            // A cada exposición le pregunto si es temporal y vigente. Si lo es, la agrego a la lista:
            if (expo.esVigenteYTemporal())
                exposicionesTempYVig.add(expo);
        }
        // Retorno la lista con las exposiciones resultantes de la iteración
        return exposicionesTempYVig;
    }

    public List<DiaSemana> getDiasAtencionSede() {
        // Método que permite obtener los días de la semana cuándo funciona la sede.
        List<DiaSemana> diasDeAtencion = new ArrayList<>();
        // Guardamos en una lista de días todos los días de la semana habilitados para la sede:
        for (HorarioSede horario:
                horarioSede) {
            if (!diasDeAtencion.contains(horario.getDiaSemana()))
                diasDeAtencion.add(horario.getDiaSemana());
        }
        return diasDeAtencion;
    }

        public LocalTime calcularDuracionEstimadaVisitaPorExposicion(List<Exposicion> listaExposiciones){
            int horasTotales = 0;
            int minutosTotales = 0;
            int segundosTotales = 0;
            // Mientras haya exposiciones, obtenemos sus duraciones
            for (Exposicion exposicion:
                 listaExposiciones) {
                LocalTime duracionExposicion = exposicion.calcularDuracionExposicion();
                horasTotales += duracionExposicion.getHour();
                minutosTotales += duracionExposicion.getMinute();
                segundosTotales += duracionExposicion.getSecond();
            }
            Duration duracionTotal = Duration.
                    ofHours(horasTotales).
                    plusMinutes(minutosTotales).
                    plusSeconds(segundosTotales);
            return LocalTime.of(
                    duracionTotal.toHoursPart(), duracionTotal.toMinutesPart(), duracionTotal.toSecondsPart());
    }

    public boolean esTuReserva(ReservaVisita reservaVisita) {
        // Método que dice si la reserva pasada por parámetro es de esta reserva
        return reservaVisita.esTuSede(this);
    }

    public int getCantidadVisitantesParaFechaYHora(LocalDateTime fechaYHora,
                                                   List<ReservaVisita> listaReservas) {
        // Método que devuelve la cantidad de visitantes que habrá en una sede para un día y hora determinados
        int cantVisitantes = 0;
        for (ReservaVisita reservaVisita:
                listaReservas) {
            if (reservaVisita.esTuSede(this) && reservaVisita.esEnDiaYHora(fechaYHora))
                if (reservaVisita.getCantidadAlumnosConfirmada() != null)
                    cantVisitantes += reservaVisita.getCantidadAlumnosConfirmada();
                else
                    cantVisitantes += reservaVisita.getCantidadAlumnos();
        }
        return cantVisitantes;
    }

    public boolean superaLimiteVisitantesParaFechaYHora(int cantidadVisitantesReserva,
                                                        LocalDateTime fechaYHora,
                                                        List<ReservaVisita> reservaVisitas){
        // Método que indica si la cantidad de visitantes pasada por parámetro junto con la cantidad de
        // visitantes que hay en las visitas confirmadas de la sede supera el límite de capacidad para
        // la sede.
        return cantidadVisitantesReserva + getCantidadVisitantesParaFechaYHora(fechaYHora, reservaVisitas)
                > cantidadMaximaVisitantes;
    }

    public boolean esTuEmpleado(Empleado empleado) {
        return empleado.esTuSede(this);
    }

	public List<Empleado> buscarGuiasDisponiblesPorHorarioDeReserva(LocalDateTime fechaYHora,
                                                                    List<Empleado> empleados,
                                                                    List<AsignacionGuia> asignacionesGuia) {
        // Método para buscar los guías disponibles para una sede en una fecha y hora pasadas por parámetro.
		List<Empleado> guiasDisponibles = new ArrayList<>();
        for (Empleado empleado:
			 empleados) {
			if (this.esTuEmpleado(empleado)
                    && empleado.esGuia()
                    && empleado.trabajaDentroDeDiaYHorario(fechaYHora)
                    && !empleado.tieneAsignacionParaDiaYHora(fechaYHora, asignacionesGuia))
			    guiasDisponibles.add(empleado);
		}
		return guiasDisponibles;
	}

	public int calcularGuiasNecesariosParaVisitantesIngresados(int cantidadVisitantes) {
        return cantidadVisitantes / cantidadMaximaVisitantesPorGuia;
    }

}//end Sede
