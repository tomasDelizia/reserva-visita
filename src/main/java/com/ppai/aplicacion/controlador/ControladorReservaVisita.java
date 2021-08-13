package com.ppai.aplicacion.controlador;


import com.ppai.aplicacion.interfaz.PantallaReservaVisita;
import com.ppai.aplicacion.negocio.*;
import com.ppai.aplicacion.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class ControladorReservaVisita {

	private PantallaReservaVisita pantallaReservaVisita;
	private List<Escuela> escuelas;
	private Escuela escuelaSeleccionada;
	private List<Sede> sedes;
	private Sede sedeSeleccionada;
	private List<TipoVisita> tiposDeVisita;
	private TipoVisita tipoVisitaSeleccionado;
	private int cantGuiasNecesarios, cantidadVisitantes, numeroReserva;
	private List<Exposicion> exposicionesTemporalesYVigentes;
	private final List<Exposicion> exposicionesSeleccionadas = new ArrayList<>();
	private LocalDateTime fechaYHoraReserva, fechaYHoraActual;
	private LocalTime duracionEstimadaExposicion;
	private List<Empleado> guiasDisponibles;
	private final List<Empleado> guiasSeleccionados = new ArrayList<>();
	private EstadoReserva estadoPendienteDeConfirmacion;
	private Empleado empleadoEnSesion;
	private Sesion sesionActual;
	private final EscuelaServicio escuelaServicio;
	private final SedeServicio sedeServicio;
	private final TipoVisitaServicio tipoVisitaServicio;
	private final ReservaVisitaServicio reservaVisitaServicio;
	private final EmpleadoServicio empleadoServicio;
	private final AsignacionGuiaServicio asignacionGuiaServicio;
	private final SesionServicio sesionServicio;
	private final EstadoReservaServicio estadoReservaServicio;


	@Autowired
	public ControladorReservaVisita(EscuelaServicio escuelaServicio, SedeServicio sedeServicio,
									TipoVisitaServicio tipoVisitaServicio,
									ReservaVisitaServicio reservaVisitaServicio,
									EmpleadoServicio empleadoServicio,
									AsignacionGuiaServicio asignacionGuiaServicio,
									SesionServicio sesionServicio,
									EstadoReservaServicio estadoReservaServicio) {
		this.escuelaServicio = escuelaServicio;
		this.sedeServicio = sedeServicio;
		this.tipoVisitaServicio = tipoVisitaServicio;
		this.reservaVisitaServicio = reservaVisitaServicio;
		this.empleadoServicio = empleadoServicio;
		this.asignacionGuiaServicio = asignacionGuiaServicio;
		this.sesionServicio = sesionServicio;
		this.estadoReservaServicio = estadoReservaServicio;
	}

	@Autowired
	public void setPantallaReservaVisita(PantallaReservaVisita pantallaReservaVisita) {
		this.pantallaReservaVisita = pantallaReservaVisita;
	}

	public void setSesionActual(Sesion sesionActual) {
		this.sesionActual = sesionActual;
	}

	public void opcionRegistrarReservaVisita(){
		buscarEscuelas();
		pantallaReservaVisita.presentarEscuelas(escuelas);
		pantallaReservaVisita.solicitarSeleccionEscuela();
	}

	public void buscarEscuelas() {
		// Método que busca y guarda en una lista todas las escuelas existentes.
		escuelas = escuelaServicio.listarEscuelas();
	}

	public void escuelaSeleccionada(Escuela escuelaSeleccionada){
		// Método que recibe por parámetro la escuela seleccionada y la guarda como atributo.
		this.escuelaSeleccionada =	escuelaSeleccionada;
		pantallaReservaVisita.solicitarCantidadVisitantes();
	}

	public void cantidadDeVisitantesIngresados(int cantidadVisitantes){
		// Método que guarda en una atributo la cantidad de visitantes pasados por parámetro.
		this.cantidadVisitantes = cantidadVisitantes;
		buscarSedes();
		pantallaReservaVisita.presentarSedes(sedes);
		pantallaReservaVisita.solicitarSeleccionSede();
	}

	public void buscarSedes(){
		// Método que busca todas las sedes existentes y las guarda en un atributo.
		sedes = sedeServicio.listarSedes();
	}

	public void sedeSeleccionada(Sede sedeSeleccionada){
		// Método que guarda como atributo la sede seleccionada.
		this.sedeSeleccionada = sedeSeleccionada;
		buscarTiposDeVisita();
		pantallaReservaVisita.solicitarSeleccionTipoVisita();
	}

	public void buscarTiposDeVisita() {
		// Método que busca todos los tipos de visita existentes y los guarda como atributo.
		tiposDeVisita = tipoVisitaServicio.listarTiposVisita();
	}

	public void tipoVisitaSeleccionada(String nombreTipoVisita){
		// Método que toma el nombre el tipo de visita seleccionado.
		// Iteramos los tipos de visita existentes:
		for (TipoVisita tipoDeVisita:
			 tiposDeVisita) {
			// Si el nombre coincide con el nombre del tipo de visita seleccionado, se guarda en un atributo.
			if (tipoDeVisita.esTipoDeVisita(nombreTipoVisita))
				tipoVisitaSeleccionado = tipoDeVisita;
		}
		buscarExposicionesTemporalesYVigentes();
		pantallaReservaVisita.presentarExposicionesTemporalesYVigentes(exposicionesTemporalesYVigentes);
		pantallaReservaVisita.solicitarSeleccionExposiciones();
	}

	public void buscarExposicionesTemporalesYVigentes(){
		// Método que busca y guarda en un atributo todas exposiciones temporales y vigentes para
		// la sede seleccionada.
		exposicionesTemporalesYVigentes = sedeSeleccionada.buscarExposicionesTemporalesYVigentes();
	}

	public void exposicionSeleccionada(Exposicion exposicionSeleccionada){
		// Método que toma una exposición seleccionada y añade a la lista de exposiciones seleccionadas.
		exposicionesSeleccionadas.add(exposicionSeleccionada);
		// Si la lista de exposiciones seleccionadas tiene al menos un elemento,
		// se continua con el caso de uso.
		if (exposicionesSeleccionadas.size() == 1)
			pantallaReservaVisita.solicitarFechaYHoraReserva();
	}

	public void fechaYHoraReservaIngresados(LocalDateTime fechaYHoraIngresados){
		// Método que toma y guarda en un atributo la fecha y hora ingresados por el usuario.
		fechaYHoraReserva = fechaYHoraIngresados;
		calcularDuracionEstimada();
		pantallaReservaVisita.presentarDuracionEstimada(duracionEstimadaExposicion);
		// Se verifica que la cantidad de visitantes ingresados no sobrepase el límite de visitantes
		// de la sede para la duración de la visita en la fecha y hora ingreados.
		// Si no lo supera, se continua con el caso de uso.
		if (!superaLimiteVisitantes())
			buscarGuiasDisponiblesPorHorarioReserva();
		// Si sobrepasa, se informa un mensaje de error por pantalla.
		else
			pantallaReservaVisita.informarLimiteVisitantesSuperado();
	}

	public void calcularDuracionEstimada(){
		// Método que calcula la duración estimada para la visita en una sede, a partir de las exposiciones
		// seleccionadas.
		duracionEstimadaExposicion = sedeSeleccionada.
				calcularDuracionEstimadaVisitaPorExposicion(exposicionesSeleccionadas);
	}

	public boolean superaLimiteVisitantes(){
		// Método que determina si la visita supera el límite de visitantes para la sede para la duración
		// de la visita.
		List<ReservaVisita> reservasDeVisita = reservaVisitaServicio.listarReservasVisita();
		return sedeSeleccionada.superaLimiteVisitantesParaFechaYHora(
				cantidadVisitantes, fechaYHoraReserva, duracionEstimadaExposicion, reservasDeVisita);
	}

	public void buscarGuiasDisponiblesPorHorarioReserva(){
		// Método que busca todos los guías disponibles según la fecha y hora de reserva ingresados.
		List<Empleado> empleados = empleadoServicio.listarEmpleados();
		List<AsignacionGuia> asignacionesDeGuia = asignacionGuiaServicio.listarAsignacionesGuia();
		guiasDisponibles = sedeSeleccionada.buscarGuiasDisponiblesPorHorarioDeReserva(
				fechaYHoraReserva, empleados, asignacionesDeGuia);
		calcularGuiasNecesarios();
		pantallaReservaVisita.presentarGuiasDisponibles(guiasDisponibles, cantGuiasNecesarios);
		pantallaReservaVisita.solicitarSeleccionGuiasDisponibles();
	}

	public void calcularGuiasNecesarios(){
		// Método que calcula los guías necesarios para la visita según la capacidad de visitantes por guía
		// de la sede.
		cantGuiasNecesarios = sedeSeleccionada.
				calcularGuiasNecesariosParaVisitantesIngresados(cantidadVisitantes);
	}

	public void guiaDisponibleSeleccionado(Empleado empleadoSeleccionado){
		// Método que toma un guía seleccionado y lo añade a la lista de guías seleccionados.
		guiasSeleccionados.add(empleadoSeleccionado);
		// Si la lista se llena con la cantidad de guías necesarios, se procede con el caso de uso.
		if (guiasSeleccionados.size() == cantGuiasNecesarios)
			pantallaReservaVisita.solicitarConfirmacionReserva();
	}

	public void confirmacionReservaSeleccionada() {
		// Método que toma la confirmación de la reserva para poder generarla.
		crearReserva();
		finCU();
	}

	public void crearReserva(){
		generarNroReserva();
		getFechaYHoraActual();
		getEmpleadoEnSesion();
		sesionActual.setFechaHoraFin(fechaYHoraActual);
		sesionServicio.guardarSesion(sesionActual);
		buscarEstadoPendienteDeConfirmacion();
		// Se genera una nueva reserva de visita con todos los datos correspondientes
		// y se la guarda en la base de datos.
		ReservaVisita nuevaReserva = new ReservaVisita(
				numeroReserva,
				cantidadVisitantes,
				fechaYHoraActual,
				fechaYHoraReserva,
				duracionEstimadaExposicion,
				escuelaSeleccionada,
				sedeSeleccionada,
				empleadoEnSesion,
				exposicionesSeleccionadas,
				estadoPendienteDeConfirmacion,
				guiasSeleccionados
				);
		reservaVisitaServicio.guardarReservaVisita(nuevaReserva);
	}

	public void generarNroReserva(){
		// Método que genera un número único para la nueva reserva.
		numeroReserva = obtenerUltimoNumeroReserva() + 1;
	}

	public int obtenerUltimoNumeroReserva(){
		// Método que recorre todas las reservas existentes y obtiene el número de la última de ellas.
		int ultimoNumeroReserva = 0;
		List<ReservaVisita> reservasDeVisita = reservaVisitaServicio.listarReservasVisita();
		for (ReservaVisita reservaVisita:
			 reservasDeVisita) {
			if (reservaVisita.getNumeroReserva() > ultimoNumeroReserva)
				ultimoNumeroReserva = reservaVisita.getNumeroReserva();
		}
		return ultimoNumeroReserva;
	}

	public void getEmpleadoEnSesion() {
		// Método que obtiene el empleado en la sesión actual.
		empleadoEnSesion = sesionActual.getEmpleadoEnSesion();
	}

	public void getFechaYHoraActual(){
		// Método que obtiene la fecha y hora actual del sistema.
		fechaYHoraActual = LocalDateTime.now();
	}

	public void buscarEstadoPendienteDeConfirmacion(){
		// Método que busca el estado "Pendiente de Confirmación" de entre todos los estados existentes.
		List<EstadoReserva> estadosDeReserva = estadoReservaServicio.listarEstadosReserva();
		for (EstadoReserva estadoReserva:
			 estadosDeReserva) {
			if (estadoReserva.esPendienteDeConfirmacion()) {
				estadoPendienteDeConfirmacion = estadoReserva;
				break;
			}
		}
	}

	public void finCU(){}
}//end ControladorReservaVisita