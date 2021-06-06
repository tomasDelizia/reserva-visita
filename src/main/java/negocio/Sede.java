package negocio;

import java.lang.reflect.Array;
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

	public ArrayList<Exposicion> buscarExposicionesTemporalesYVigentes(){
		ArrayList<Exposicion> exposicionesTempYVig = new ArrayList<Exposicion>();
		for (Exposicion e:
			 exposicion) {
			if (e.esVigenteYTemporal())
				exposicionesTempYVig.add(e);
		}
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

	public int calcularDuracionEstimadaVisita(ArrayList<Exposicion> exposiciones){
		int duracion = 0;
		for (Exposicion e:
			 exposiciones) {
			duracion += e.calcularDuracionExposicion();
		}
		return duracion;
	}

	/*
	public void superaLimiteVisitantesParaFechaYHora(){
		for (ReservaVisita r:
			 reserva) {

		}
	}
	*/
}//end Sede