package negocio;


import java.time.LocalDateTime;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 7:58:57 pm
 */
public class Sesion {

	private LocalDateTime fechaYHoraFin;
	private LocalDateTime fechaYHoraInicio;
	private Usuario usuario;

	public Sesion(LocalDateTime fechaYHoraFin, LocalDateTime fechaYHoraInicio, Usuario usuario) {
		this.fechaYHoraFin = fechaYHoraFin;
		this.fechaYHoraInicio = fechaYHoraInicio;
		this.usuario = usuario;
	}

	public LocalDateTime getFechaYHoraFin() {
		return fechaYHoraFin;
	}

	public LocalDateTime getFechaYHoraInicio() {
		return fechaYHoraInicio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setFechaYHoraFin(LocalDateTime fechaYHoraFin) {
		this.fechaYHoraFin = fechaYHoraFin;
	}

	public void setFechaYHoraInicio(LocalDateTime fechaYHoraInicio) {
		this.fechaYHoraInicio = fechaYHoraInicio;
	}

	@Override
	public String toString() {
		return "Sesion{" +
				"fechaYHoraFin=" + fechaYHoraFin +
				", fechaYHoraInicio=" + fechaYHoraInicio +
				", usuario=" + usuario +
				'}';
	}

	public Empleado getEmpleadoEnSesion(){
		return usuario.getEmpleado();
	}

	public Sesion getSesion(){
		return this;
	}

}//end Sesion