package negocio;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:34 am
 */
public class HorarioEmpleado {

	private LocalTime horaIngreso;
	private LocalTime horaSalida;
	private List<DiaSemana> diaSemana;

	public HorarioEmpleado(LocalTime horaIngreso, LocalTime horaSalida, List<DiaSemana> diaSemana) {
		this.horaIngreso = horaIngreso;
		this.horaSalida = horaSalida;
		this.diaSemana = diaSemana;
	}

	public LocalTime getHoraIngreso() {
		return horaIngreso;
	}

	public LocalTime getHoraSalida() {
		return horaSalida;
	}

	public List<DiaSemana> getDiaSemana() {
		return diaSemana;
	}

	public void setHoraIngreso(LocalTime horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
	}


	@Override
	public String toString() {
		return "HorarioEmpleado{" +
				"horaIngreso=" + horaIngreso +
				", horaSalida=" + horaSalida +
				", diaSemana=" + diaSemana +
				'}';
	}
}