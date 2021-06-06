package negocio;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:22 am
 */
public class Sede {

	private int capacidadMaxVisitantes;
	private int capacidadVisitantesMaxPorGuia;
	private String nombre;
	private String calleNombre;
	private int calleNumero;
	private List<HorarioSede> horarioSede;
	private List<Exposicion> exposicion;

	public Sede(int capacidadMaxVisitantes, int capacidadVisitantesMaxPorGuia, String nombre,
				List<HorarioSede> horarioSede, String calleNombre, int calleNumero) {
		this.capacidadMaxVisitantes = capacidadMaxVisitantes;
		this.capacidadVisitantesMaxPorGuia = capacidadVisitantesMaxPorGuia;
		this.nombre = nombre;
		this.calleNombre = calleNombre;
		this.calleNumero = calleNumero;
	}

	public int getCapacidadMaxVisitantes() {
		return capacidadMaxVisitantes;
	}

	public void setCapacidadMaxVisitantes(int capacidadMaxVisitantes) {
		this.capacidadMaxVisitantes = capacidadMaxVisitantes;
	}

	public int getCapacidadVisitantesMaxPorGuia() {
		return capacidadVisitantesMaxPorGuia;
	}

	public void setCapacidadVisitantesMaxPorGuia(int capacidadVisitantesMaxPorGuia) {
		this.capacidadVisitantesMaxPorGuia = capacidadVisitantesMaxPorGuia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<HorarioSede> getHorarioSede() {
		return horarioSede;
	}

	public String getCalleNombre() {
		return calleNombre;
	}

	public void setCalleNombre(String calleNombre) {
		this.calleNombre = calleNombre;
	}

	public int getCalleNumero() {
		return calleNumero;
	}

	public void setCalleNumero(int calleNumero) {
		this.calleNumero = calleNumero;
	}

	@Override
	public String toString() {
		return "Sede{" +
				"capacidadMaxVisitantes=" + capacidadMaxVisitantes +
				", capacidadVisitantesMaxPorGuia=" + capacidadVisitantesMaxPorGuia +
				", nombre='" + nombre + '\'' +
				", horarioSede=" + horarioSede +
				", calleNombre='" + calleNombre + '\'' +
				", calleNumero=" + calleNumero +
				'}';
	}

	public void addExposicion(Exposicion expo) {
		exposicion.add(expo);
	}

	public void addHorarioSede(HorarioSede horario) {
		horarioSede.add(horario);
	}

	public Sede getSede() {return this;}

	public List<Exposicion> buscarExposicionesTemporalesYVigentes(){
		// Método para encontrar las exposiciones temporales y vigentes de una sede
		// Se inicializa una lista para almacenar las exposiciones deseadas
		List<Exposicion> exposicionesTempYVig = new ArrayList<>();
		// Recorro todas las exposiciones de una sede
		for (Exposicion expo:
			 exposicion) {
			// A cada exposición le pregunto si es temporal y vigente. Si lo es, la agrego a la lista
			if (expo.esVigenteYTemporal())
				exposicionesTempYVig.add(expo);
		}
		// Retorno la lista con las exposiciones resultantes de la iteración
		return exposicionesTempYVig;
	}

	public List<DiaSemana> getDiasAtencionSede() {
		List<DiaSemana> dias = new ArrayList<>();
		for (HorarioSede horario:
			 horarioSede) {
			dias.addAll(horario.getDiaSemana());
		}
		return dias;
	}

	public void buscarGuiasDisponiblesPorHorarioReserva(){

	}

	public Duration calcularDuracionEstimadaVisita(List<Exposicion> exposiciones, TipoVisita tipoVisita){
		Duration duracionVisita = Duration.parse("00:00:00");
		for (Exposicion expo:
			 exposiciones) {
			Duration duracionExpo = expo.calcularDuracionExposicion(tipoVisita);
			duracionVisita.plus(duracionExpo);
		}
		return duracionVisita;
	}

	public boolean esTuReserva(ReservaVisita reservaVisita) {
		// Método que dice si la reserva pasada por parámetro es de esta reserva
		return reservaVisita.esTuSede(this);
	}

	public int getCantidadVisitantesParaFechaYHora(LocalDateTime fechaYHora, List<ReservaVisita> reservasVisitas) {
		int cantVisitantes = 0;
		for (ReservaVisita reservaVisita:
			 reservasVisitas) {
			if (reservaVisita.esTuSede(this) && reservaVisita.esEnDiaYHora(fechaYHora))
				cantVisitantes += reservaVisita.getCantidadAlumnos();
		}
		return cantVisitantes;
	}

	public boolean superaLimiteVisitantesParaFechaYHora(int cantidadVisitantesReserva, LocalDateTime fechaYHora, List<ReservaVisita> reservaVisitas){
		return cantidadVisitantesReserva + getCantidadVisitantesParaFechaYHora(fechaYHora, reservaVisitas) > capacidadMaxVisitantes;
	}

}//end Sede