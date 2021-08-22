package com.ppai.aplicacion.controlador;


import com.ppai.aplicacion.interfaz.ExposicionVisible;
import com.ppai.aplicacion.interfaz.GuiaVisible;
import com.ppai.aplicacion.interfaz.PantallaReservaVisita;
import com.ppai.aplicacion.negocio.*;
import com.ppai.aplicacion.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase de control que se encarga de la comunicación entre la interfaz y las entidades para el CU Registrar
 * Reserva de Visita Guiada.
 */
@Component
public class ControladorReservaVisita {

	private PantallaReservaVisita pantallaReservaVisita;
	private final List<String> escuelas = new ArrayList<>();
	private Escuela escuelaSeleccionada;
	private final List<String> sedes = new ArrayList<>();
	private Sede sedeSeleccionada;
	private final List<String> tiposDeVisita = new ArrayList<>();
	private TipoVisita tipoVisitaSeleccionado;
	private int cantGuiasNecesarios, cantidadVisitantes, numeroReserva;
	private List<ExposicionVisible> exposicionesTemporalesYVigentes = new ArrayList<>();
	private final List<Exposicion> exposicionesSeleccionadas = new ArrayList<>();
	private LocalDateTime fechaYHoraReserva, fechaYHoraActual;
	private LocalTime duracionEstimadaExposicion;
	private List<GuiaVisible> guiasDisponibles;
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

	/**
	 * Método que guarda como atributo sesionActual a un objeto sesión pasado por parámetro.
	 * @param sesionActual la sesión pasada por parámetro.
	 */
	public void setSesionActual(Sesion sesionActual) {
		this.sesionActual = sesionActual;
	}

	/**
	 * Método que se invoca desde la pantalla una vez iniciada la interfaz. Comienza buscando las escuelas, y luego
	 * le pide a la pantalla que las presente para su selección.
	 */
	public void opcionRegistrarReservaVisita(){
		buscarEscuelas();
		pantallaReservaVisita.presentarEscuelas(escuelas);
		pantallaReservaVisita.solicitarSeleccionEscuela();
	}

	/**
	 * Método que busca y guarda en el atributo escuelas el nombre de todas las escuelas existentes.
	 */
	public void buscarEscuelas() {
		List<Escuela> listaEscuelas = escuelaServicio.listarEscuelas();
		for (Escuela escuela:
				listaEscuelas) {
			escuelas.add(escuela.getNombre());
		}
	}

	/**
	 *  Método que recibe por parámetro la escuela seleccionada, la busca y la guarda el objeto Escuela como atributo.
	 *  Luego le pide a la pantalla que solicite el ingreso de la cantidad de visitantes.
	 * @param escuelaSeleccionada el nombre de la escuela seleccionada a buscar.
	 */
	public void escuelaSeleccionada(String escuelaSeleccionada){
		this.escuelaSeleccionada =	escuelaServicio.encontrarPorNombre(escuelaSeleccionada);
		pantallaReservaVisita.solicitarCantidadVisitantes();
	}

	/**
	 * Método que guarda en una atributo la cantidad de visitantes pasados por parámetro.
	 * @param cantidadVisitantes la cantidad de visitantes ingresada por el usuario.
	 */
	public void cantidadDeVisitantesIngresados(int cantidadVisitantes){
		this.cantidadVisitantes = cantidadVisitantes;
		buscarSedes();
		pantallaReservaVisita.presentarSedes(sedes);
		pantallaReservaVisita.solicitarSeleccionSede();
	}

	/**
	 * Método que busca y guarda en una lista el nombre de todas las sedes existentes.
	 */
	public void buscarSedes(){
		List<Sede> listaSedes = sedeServicio.listarSedes();
		for (Sede sede:
			 listaSedes) {
			sedes.add(sede.getNombre());
		}
	}

	/**
	 * Método que guarda como atributo la sede seleccionada pasada por parámetro. Luego continúa buscando los tipos
	 * de visita y presentándolos para su selección.
	 * @param sedeSeleccionada la sede seleccionada por el usuario.
	 */
	public void sedeSeleccionada(String sedeSeleccionada){
		this.sedeSeleccionada = sedeServicio.encontrarPorNombre(sedeSeleccionada);
		buscarTiposDeVisita();
		pantallaReservaVisita.presentarTiposVisita(tiposDeVisita);
		pantallaReservaVisita.solicitarSeleccionTipoVisita();
	}

	/**
	 * Método que busca el nombre de todos los tipos de visita existentes y los guarda en una lista como atributo.
	 */
	public void buscarTiposDeVisita() {
		List<TipoVisita> listaTiposDeVisita = tipoVisitaServicio.listarTiposVisita();
		for (TipoVisita tipoVisita:
			 listaTiposDeVisita) {
			tiposDeVisita.add(tipoVisita.getNombre());
		}
	}

	/**
	 * Método que toma el nombre el tipo de visita seleccionado, verifica que sea "Por Exposición", lo guarda y
	 * continúa pidiéndole a la pantalla que busque y muestre las exposiciones temporales y vigentes para su selección.
	 * @param nombreTipoVisita el nombre del tipo de visita seleccionado por el usuario.
	 */
	public void tipoVisitaSeleccionado(String nombreTipoVisita){
		this.tipoVisitaSeleccionado = tipoVisitaServicio.encontrarPorNombre(nombreTipoVisita);
		// Si se selecciona el tipo de visita "Por Exposición", se continúa con el caso de uso.
		if (tipoVisitaSeleccionado.esPorExposicion()) {
			buscarExposicionesTemporalesYVigentes();
			pantallaReservaVisita.presentarExposicionesTemporalesYVigentesNuevo(exposicionesTemporalesYVigentes);
			pantallaReservaVisita.solicitarSeleccionExposiciones();
		}
		// Si se selecciona el tipo de visita "Completa", se informa que seleccione "Por Exposición".
		else
			pantallaReservaVisita.informarSeleccionTipoVisitaPorExposicion();
	}

	/**
	 * Método que busca y guarda en un atributo una lista con los datos generales de todas exposiciones temporales
	 * y vigentes para la sede seleccionada.
	 */
	public void buscarExposicionesTemporalesYVigentes(){
		exposicionesTemporalesYVigentes = sedeSeleccionada.buscarExposicionesTemporalesYVigentes();
	}

	/**
	 * Método que toma una exposición y la añade a la lista de exposiciones seleccionadas, y se continúa
	 * con el caso de uso pidiéndole a la pantalla que solicite el ingreso de la fecha y hora de la reserva.
	 * @param nombreExposicionSeleccionada el nombre de la exposición a añadir.
	 */
	public void exposicionSeleccionada(String nombreExposicionSeleccionada){
		exposicionesSeleccionadas
				.add(sedeSeleccionada.encontrarExposicionTemporalYVigentePorNombre(nombreExposicionSeleccionada));
		// Si la lista de exposiciones seleccionadas tiene al menos un elemento, se continúa con el caso de uso.
		if (exposicionesSeleccionadas.size() == 1)
			pantallaReservaVisita.solicitarFechaYHoraReserva();
	}

	/**
	 * Método que toma una exposición y la quita de la lista de exposiciones seleccionadas, y se continúa
	 * con el caso de uso pidiéndole a la pantalla que solicite el ingreso de la fecha y hora de la reserva.
	 * @param nombreExposicionSeleccionada el nombre de la exposición a quitar.
	 */
	public void exposicionDeseleccionada(String nombreExposicionSeleccionada){
		exposicionesSeleccionadas
				.remove(sedeSeleccionada.encontrarExposicionTemporalYVigentePorNombre(nombreExposicionSeleccionada));
		// Si la lista de exposiciones seleccionadas tiene al menos un elemento, se continúa con el caso de uso.
		if (exposicionesSeleccionadas.size() == 1)
			pantallaReservaVisita.solicitarFechaYHoraReserva();
		// Si no, se deshabilita la selección de la fecha y hora de reserva:
		else if (exposicionesSeleccionadas.size() < 1)
			pantallaReservaVisita.deshabilitarSeleccionFechaYHoraReserva();
	}

	/**
	 * Método que toma y guarda en un atributo la fecha y hora ingresados por el usuario.
	 * @param fechaYHoraIngresados la fecha y hora ingresados por el usuario.
	 */
	public void fechaYHoraReservaIngresados(LocalDateTime fechaYHoraIngresados){
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

	/**
	 * Método que calcula la duración estimada para la visita en una sede, a partir de las exposiciones seleccionadas.
	 */
	public void calcularDuracionEstimada(){
		duracionEstimadaExposicion = sedeSeleccionada.
				calcularDuracionEstimadaVisitaPorExposicion(exposicionesSeleccionadas);
	}

	/**
	 * Método que determina si la visita supera el límite de visitantes para la sede para la duración de la visita.
	 * @return verdadero si la visita supera el límite de visitantes para la sede para la duración de la visita,
	 * y falso en cualquier otro caso.
	 */
	public boolean superaLimiteVisitantes(){
		// Obtengo todas las reservas de todas las sedes para pasarlas por parámetro:
		List<ReservaVisita> reservasDeVisita = reservaVisitaServicio.listarReservasVisita();
		return sedeSeleccionada.superaLimiteVisitantesParaFechaYHora(
				cantidadVisitantes, fechaYHoraReserva, duracionEstimadaExposicion, reservasDeVisita);
	}

	/**
	 * Método que busca todos los guías disponibles según la fecha y hora de reserva ingresados.
	 */
	public void buscarGuiasDisponiblesPorHorarioReserva(){
		List<Empleado> empleados = empleadoServicio.listarEmpleados();
		List<AsignacionGuia> asignacionesDeGuia = asignacionGuiaServicio.listarAsignacionesGuia();
		guiasDisponibles = sedeSeleccionada.buscarGuiasDisponiblesPorHorarioDeReserva(
				fechaYHoraReserva, empleados, asignacionesDeGuia);
		calcularGuiasNecesarios();
		pantallaReservaVisita.presentarGuiasDisponibles(guiasDisponibles, cantGuiasNecesarios);
		pantallaReservaVisita.solicitarSeleccionGuiasDisponibles();
	}

	/**
	 * Método que calcula los guías necesarios para la visita según la capacidad de visitantes por guía de la sede.
	 */
	public void calcularGuiasNecesarios(){
		cantGuiasNecesarios = sedeSeleccionada.
				calcularGuiasNecesariosParaVisitantesIngresados(cantidadVisitantes);
	}

	/**
	 * Método que toma un guía seleccionado y lo añade a la lista de guías seleccionados.
	 * @param nombreGuia el nombre del guía seleccionado.
	 * @param apellidoGuia el apellido del guía seleccionado.
	 */
	public void guiaDisponibleSeleccionado(String nombreGuia, String apellidoGuia){
		guiasSeleccionados.add(empleadoServicio.encontrarEmpleadoPorNombreYApellido(nombreGuia, apellidoGuia));
		// Si la lista se llena con la cantidad de guías necesarios, se procede con el caso de uso.
		if (guiasSeleccionados.size() == cantGuiasNecesarios)
			pantallaReservaVisita.solicitarConfirmacionReserva();
	}

	/**
	 * Método que toma un guía deseleccionado y lo quita de la lista de guías seleccionados.
	 * @param nombreGuia el nombre del guía deseleccionado.
	 * @param apellidoGuia el apellido del guía deseleccionado.
	 */
	public void guiaDisponibleDeseleccionado(String nombreGuia, String apellidoGuia){
		// Método que toma un guía seleccionado y lo quita de la lista de guías seleccionados.
		guiasSeleccionados.remove(empleadoServicio.encontrarEmpleadoPorNombreYApellido(nombreGuia, apellidoGuia));
		// Si la lista se llena con la cantidad de guías necesarios, se procede con el caso de uso.
		if (guiasSeleccionados.size() == cantGuiasNecesarios)
			pantallaReservaVisita.solicitarConfirmacionReserva();
	}

	/**
	 * Método que toma la confirmación de la reserva para poder generarla.
	 */
	public void confirmacionReservaSeleccionada() {
		crearReserva();
		finCU();
	}

	/**
	 * Método que crea una nueva reserva a partir de los datos ingresados por el usuario.
	 */
	public void crearReserva() {
		generarNroReserva();
		getFechaYHoraActual();
		getEmpleadoEnSesion();
		sesionActual.setFechaHoraFin(fechaYHoraActual);
		sesionServicio.guardarSesion(sesionActual);
		buscarEstadoPendienteDeConfirmacion();
		// Se genera una nueva reserva de visita con todos los datos correspondientesy se la guarda en la base de datos.
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

	/**
	 * Método que genera un número único para la nueva reserva.
	 */
	public void generarNroReserva() {
		numeroReserva = obtenerUltimoNumeroReserva() + 1;
	}

	/**
	 * Método que recorre todas las reservas existentes y obtiene el número de la última de ellas.
	 * @return el número de la última reserva registrada.
	 */
	public int obtenerUltimoNumeroReserva() {
		int ultimoNumeroReserva = 0;
		List<ReservaVisita> reservasDeVisita = reservaVisitaServicio.listarReservasVisita();
		for (ReservaVisita reservaVisita:
			 reservasDeVisita) {
			if (reservaVisita.getNumeroReserva() > ultimoNumeroReserva)
				ultimoNumeroReserva = reservaVisita.getNumeroReserva();
		}
		return ultimoNumeroReserva;
	}

	/**
	 * Método que obtiene el empleado en la sesión actual.
	 */
	public void getEmpleadoEnSesion() {
		empleadoEnSesion = sesionActual.getEmpleadoEnSesion();
	}

	/**
	 * Método que obtiene la fecha y hora actual del sistema.
	 */
	public void getFechaYHoraActual() {
		fechaYHoraActual = LocalDateTime.now();
	}

	/**
	 * Método que busca el estado "Pendiente de Confirmación" de entre todos los estados existentes.
	 */
	public void buscarEstadoPendienteDeConfirmacion() {
		List<EstadoReserva> estadosDeReserva = estadoReservaServicio.listarEstadosReserva();
		for (EstadoReserva estadoReserva:
			 estadosDeReserva) {
			if (estadoReserva.esPendienteDeConfirmacion()) {
				estadoPendienteDeConfirmacion = estadoReserva;
				break;
			}
		}
	}

	/**
	 * Método que finaliza el caso de uso.
	 */
	public void finCU() {
		pantallaReservaVisita.informarReservaRegistrada();
	}
}//end ControladorReservaVisita