package negocio;
import java.time.LocalDateTime;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:29 am
 */
public class AsignacionGuia {

	private LocalDateTime fechaYHoraFin;
	private LocalDateTime fechaYHoraInicio;
	private Empleado guiaAsignado;

	public AsignacionGuia(LocalDateTime fechaYHoraFin, LocalDateTime fechaYHoraInicio, Empleado guiaAsignado) {
		this.fechaYHoraFin = fechaYHoraFin;
		this.fechaYHoraInicio = fechaYHoraInicio;
		this.guiaAsignado = guiaAsignado;
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

	public Empleado getGuiaAsignado() {
		return guiaAsignado;
	}

	public void setGuiaAsignado(Empleado guiaAsignado) {
		this.guiaAsignado = guiaAsignado;
	}

	@Override
	public String toString() {
		return "AsignacionGuia{" +
				"fechaYHoraFin=" + fechaYHoraFin +
				", fechaYHoraInicio=" + fechaYHoraInicio +
				", guiaAsignado=" + guiaAsignado +
				'}';
	}
}//end AsignacionGuia