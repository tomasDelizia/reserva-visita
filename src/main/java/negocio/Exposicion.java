package negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalTime;
import java.util.List;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:44:08 am
 */
public class Exposicion {

	private LocalDate fechaFin;
	private LocalDate fechaFinReplanificada;
	private LocalDate fechaInicio;
	private LocalDate fechaInicioReplanificada;
	private LocalTime horaApertura;
	private LocalTime horaCierre;
	private String nombre;
	private TipoExposicion tipoExposicion;
	private Empleado empleadoCreo;
	private List<PublicoDestino> publicoDestino;
	private List<DetalleExposicion> detalleExposicion;

	public Exposicion(LocalDate fechaFin, LocalDate fechaFinReplanificada, LocalDate fechaInicio,
					  LocalDate fechaInicioReplanificada, LocalTime horaApertura, LocalTime horaCierre,
					  String nombre, TipoExposicion tipoExposicion, Empleado empleadoCreo,
					  List<PublicoDestino> publicoDestino, List<DetalleExposicion> detalleExposicion) {
		this.fechaFin = fechaFin;
		this.fechaFinReplanificada = fechaFinReplanificada;
		this.fechaInicio = fechaInicio;
		this.fechaInicioReplanificada = fechaInicioReplanificada;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.nombre = nombre;
		this.tipoExposicion = tipoExposicion;
		this.empleadoCreo = empleadoCreo;
		this.publicoDestino = publicoDestino;
		this.detalleExposicion = detalleExposicion;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public LocalDate getFechaFinReplanificada() {
		return fechaFinReplanificada;
	}

	public void setFechaFinReplanificada(LocalDate fechaFinReplanificada) {
		this.fechaFinReplanificada = fechaFinReplanificada;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaInicioReplanificada() {
		return fechaInicioReplanificada;
	}

	public void setFechaInicioReplanificada(LocalDate fechaInicioReplanificada) {
		this.fechaInicioReplanificada = fechaInicioReplanificada;
	}

	public LocalTime getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(LocalTime horaApertura) {
		this.horaApertura = horaApertura;
	}

	public LocalTime getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(LocalTime horaCierre) {
		this.horaCierre = horaCierre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoExposicion getTipoExposicion() {
		return tipoExposicion;
	}

	public void setTipoExposicion(TipoExposicion tipoExposicion) {
		this.tipoExposicion = tipoExposicion;
	}

	public Empleado getEmpleadoCreo() {
		return empleadoCreo;
	}

	public void setEmpleadoCreo(Empleado empleadoCreo) {
		this.empleadoCreo = empleadoCreo;
	}

	public void addPublicoDestino(PublicoDestino pd) {
		publicoDestino.add(pd);
	}

	public List<PublicoDestino> getPublicoDestino() {
		return this.publicoDestino;
	}

	@Override
	public String toString() {
		return "Exposicion{" +
				"fechaFin=" + fechaFin +
				", fechaFinReplanificada=" + fechaFinReplanificada +
				", fechaInicio=" + fechaInicio +
				", fechaInicioReplanificada=" + fechaInicioReplanificada +
				", horaApertura=" + horaApertura +
				", horaCierre=" + horaCierre +
				", nombre='" + nombre + '\'' +
				", tipoExposicion=" + tipoExposicion +
				", empleadoCreo=" + empleadoCreo +
				", publicoDestino=" + publicoDestino +
				", detalleExposicion=" + detalleExposicion +
				'}';
	}

	public void addDetalleExposicion(DetalleExposicion detalle) {
		detalleExposicion.add(detalle);
	}

	public boolean esVigenteYTemporal() {
		return this.tipoExposicion.esTemporal() && this.esVigente();
	}

	public boolean esVigente() {
		LocalDate ld = LocalDate.now();
		if (this.fechaFinReplanificada == null)
			return this.fechaFin.compareTo(ld) >= 0;
		else
			return this.fechaFinReplanificada.compareTo(ld) >= 0;
	}

	public ArrayList<String> getNombresPublicoDestino() {
		ArrayList<String> nombresPublicos = new ArrayList<String>();
		for (PublicoDestino o:
				publicoDestino) {
			String nombre = o.getNombre();
			nombresPublicos.add(nombre);
		}
		return nombresPublicos;
	}

	public ArrayList<LocalTime> getHorarioExposicionTemporal () {
		ArrayList<LocalTime> horario = new ArrayList<LocalTime>();
		if (this.tipoExposicion.esTemporal()) {
			horario.add(this.horaApertura);
			horario.add(this.horaCierre);
		}
		return horario;
	}

	public int calcularDuracionExposicion() {
		int duracion = 0;
		for (DetalleExposicion de:
			 detalleExposicion) {
			duracion += de.getObra().getDuracionExtendida();
		}
		return duracion;
	}

}//end Exposicion