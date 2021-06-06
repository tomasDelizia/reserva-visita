package negocio;
import java.time.LocalDateTime;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:46 am
 */
public class CambioEstadoReserva {

	private LocalDateTime fechaYHoraFin;
	private LocalDateTime fechaYHoraInicio;
	private EstadoReserva estado;

	public CambioEstadoReserva(LocalDateTime fechaYHoraFin, LocalDateTime fechaYHoraInicio, EstadoReserva estado) {
		this.fechaYHoraFin = fechaYHoraFin;
		this.fechaYHoraInicio = fechaYHoraInicio;
		this.estado = estado;
	}

	public LocalDateTime getFechaYHoraFin() {
		return fechaYHoraFin;
	}

	public void setFechaYHoraFin(LocalDateTime fechaYHoraFin) {
		this.fechaYHoraFin = fechaYHoraFin;
	}

	public LocalDateTime getFechaYHoraInicio() {
		return fechaYHoraInicio;
	}

	public void setFechaYHoraInicio(LocalDateTime fechaYHoraInicio) {
		this.fechaYHoraInicio = fechaYHoraInicio;
	}

	public EstadoReserva getEstado() {
		return estado;
	}

	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "CambioEstadoReserva{" +
				"fechaYHoraFin=" + fechaYHoraFin +
				", fechaYHoraInicio=" + fechaYHoraInicio +
				", estado=" + estado +
				'}';
	}
}//end CambioEstadoReserva