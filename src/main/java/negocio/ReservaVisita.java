package negocio;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:26 am
 */
public class ReservaVisita {

	private int cantidadAlumnos, cantidadAlumnosConfirmada, duracionEstimada;
	private LocalDateTime fechaYHoraCreacion, fechaYHoraReserva;
	private LocalTime horaFinReal, horaInicioReal;
	private int numeroReserva;
	private TipoVisita tipoVisita;
	private Escuela escuela;
	private List<Exposicion> exposicion;
	private Sede sede;
	private List<CambioEstadoReserva> cambioEstado;
	private AsignacionGuia asignacionGuia;
	private Empleado empleadoCreo;

	public ReservaVisita(int cantidadAlumnos, int cantidadAlumnosConfirmada, int duracionEstimada,
						 LocalDateTime fechaYHoraCreacion, LocalDateTime fechaYHoraReserva, int numeroReserva,
						 TipoVisita tipoVisita, Escuela escuela, List<Exposicion> exposicion, Sede sede,
						 EstadoReserva estado, AsignacionGuia asignacionGuia,
						 Empleado empleadoCreo) {
		this.cantidadAlumnos = cantidadAlumnos;
		this.cantidadAlumnosConfirmada = cantidadAlumnosConfirmada;
		this.duracionEstimada = duracionEstimada;
		this.fechaYHoraCreacion = fechaYHoraCreacion;
		this.fechaYHoraReserva = fechaYHoraReserva;
		this.numeroReserva = numeroReserva;
		this.tipoVisita = tipoVisita;
		this.escuela = escuela;
		this.exposicion = exposicion;
		this.sede = sede;
		this.cambioEstado = new ArrayList<>();
		CambioEstadoReserva cambio = new CambioEstadoReserva(null, fechaYHoraCreacion, estado);
		cambioEstado.add(cambio);
		this.asignacionGuia = asignacionGuia;
		this.empleadoCreo = empleadoCreo;
	}

	public int getCantidadAlumnos() {
		return cantidadAlumnos;
	}

	public void setCantidadAlumnos(int cantidadAlumnos) {
		this.cantidadAlumnos = cantidadAlumnos;
	}

	public int getCantidadAlumnosConfirmada() {
		return cantidadAlumnosConfirmada;
	}

	public void setCantidadAlumnosConfirmada(int cantidadAlumnosConfirmada) {
		this.cantidadAlumnosConfirmada = cantidadAlumnosConfirmada;
	}

	public int getDuracionEstimada() {
		return duracionEstimada;
	}

	public void setDuracionEstimada(int duracionEstimada) {
		this.duracionEstimada = duracionEstimada;
	}

	public LocalDateTime getFechaYHoraCreacion() {
		return fechaYHoraCreacion;
	}

	public void setFechaYHoraCreacion(LocalDateTime fechaYHoraCreacion) {
		this.fechaYHoraCreacion = fechaYHoraCreacion;
	}

	public LocalDateTime getFechaYHoraReserva() {
		return fechaYHoraReserva;
	}

	public void setFechaYHoraReserva(LocalDateTime fechaYHoraReserva) {
		this.fechaYHoraReserva = fechaYHoraReserva;
	}

	public LocalTime getHoraFinReal() {
		return horaFinReal;
	}

	public void setHoraFinReal(LocalTime horaFinReal) {
		this.horaFinReal = horaFinReal;
	}

	public LocalTime getHoraInicioReal() {
		return horaInicioReal;
	}

	public void setHoraInicioReal(LocalTime horaInicioReal) {
		this.horaInicioReal = horaInicioReal;
	}

	public int getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(int numeroReserva) {
		this.numeroReserva = numeroReserva;
	}

	public TipoVisita getTipoVisita() {
		return tipoVisita;
	}

	public void setTipoVisita(TipoVisita tipoVisita) {
		this.tipoVisita = tipoVisita;
	}

	public Escuela getEscuela() {
		return escuela;
	}

	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}

	public List<Exposicion> getExposicion() {
		return exposicion;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public List<CambioEstadoReserva> getCambioEstado() {
		return cambioEstado;
	}

	public AsignacionGuia getAsignacionGuia() {
		return asignacionGuia;
	}

	public void setAsignacionGuia(AsignacionGuia asignacionGuia) {
		this.asignacionGuia = asignacionGuia;
	}

	public Empleado getEmpleadoCreo() {
		return empleadoCreo;
	}

	public void setEmpleadoCreo(Empleado empleadoCreo) {
		this.empleadoCreo = empleadoCreo;
	}

	@Override
	public String toString() {
		return "ReservaVisita{" +
				"cantidadAlumnos=" + cantidadAlumnos +
				", cantidadAlumnosConfirmada=" + cantidadAlumnosConfirmada +
				", duracionEstimada=" + duracionEstimada +
				", fechaYHoraCreacion=" + fechaYHoraCreacion +
				", fechaYHoraReserva=" + fechaYHoraReserva +
				", horaFinReal=" + horaFinReal +
				", horaInicioReal=" + horaInicioReal +
				", numeroReserva=" + numeroReserva +
				", tipoVisita=" + tipoVisita +
				", escuela=" + escuela +
				", exposicion=" + exposicion +
				", sede=" + sede +
				", cambioEstado=" + cambioEstado +
				", asignacionGuia=" + asignacionGuia +
				", empleadoCreo=" + empleadoCreo +
				'}';
	}

	public boolean esEnDiaYHora(LocalDateTime fechaYHora){
		// Método que dice si la reserva es en el día y la hora pasada por parámetro
		LocalDate fecha = fechaYHora.toLocalDate();
		LocalTime hora = fechaYHora.toLocalTime();
		return (fecha.compareTo(this.fechaYHoraReserva.toLocalDate()) == 0
			&& hora.isAfter(fechaYHoraReserva.toLocalTime())
			&& hora.isBefore(fechaYHoraReserva.toLocalTime().plusMinutes(duracionEstimada)));
	}

	public boolean esTuSede(Sede sede) {
		// Dice si la sede pasada por parámetro es la misma que tiene asociada este objeto sede
		return sede == this.sede;
	}

	public void anularReservaVisitaGuiada(){

	}

	public void buscarGuiasLibresPorHorarioReserva(){

	}

	public void calcularTiempoApreciacionObra(){

	}

	public void cancelarReserva(){

	}

	public void confirmarReserva(){

	}

	public void obtenerDuracion(){

	}

	public void registrarConfirmacionReserva(){

	}
}//end ReservaVisita