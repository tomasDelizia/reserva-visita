package com.ppai.aplicacion.negocio.visita_programada.controlador;

import com.ppai.aplicacion.negocio.empleado.servicio.AsignacionGuiaServicio;
import com.ppai.aplicacion.negocio.empleado.servicio.EmpleadoServicio;
import com.ppai.aplicacion.negocio.sede.servicio.SedeServicio;
import com.ppai.aplicacion.negocio.usuario.servicio.SesionServicio;
import com.ppai.aplicacion.negocio.venta_entradas.servicio.TipoVisitaServicio;
import com.ppai.aplicacion.negocio.visita_programada.estado.PendienteDeConfirmacion;
import com.ppai.aplicacion.negocio.visita_programada.modelo.EstadoReservaVisita;
import com.ppai.aplicacion.negocio.visita_programada.servicio.EscuelaServicio;
import com.ppai.aplicacion.negocio.visita_programada.servicio.EstadoReservaServicio;
import com.ppai.aplicacion.negocio.visita_programada.servicio.ReservaVisitaServicio;
import com.ppai.aplicacion.presentacion.presentacion_visitas_programadas.PantallaReservaVisita;
import com.ppai.aplicacion.negocio.empleado.modelo.AsignacionGuia;
import com.ppai.aplicacion.negocio.empleado.modelo.Empleado;
import com.ppai.aplicacion.negocio.exposicion.modelo.Exposicion;
import com.ppai.aplicacion.negocio.sede.modelo.Sede;
import com.ppai.aplicacion.negocio.venta_entradas.modelo.TipoVisita;
import com.ppai.aplicacion.negocio.visita_programada.modelo.Escuela;
import com.ppai.aplicacion.negocio.visita_programada.modelo.EstadoReserva;
import com.ppai.aplicacion.negocio.visita_programada.modelo.ReservaVisita;
import com.ppai.aplicacion.negocio.visita_programada.estrategia.EstrategiaCalculoDuracionReserva;
import com.ppai.aplicacion.negocio.visita_programada.estrategia.EstrategiaVisitaPorExposicion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de control que se encarga de la comunicación entre la interfaz y las entidades para el CU Registrar
 * Reserva de Visita Guiada.
 */
@Controller
public class ControladorReservaVisita {
	private PantallaReservaVisita pantallaReservaVisita;
	private String[] escuelas, sedes, tiposVisita;
	private Escuela escuelaSeleccionada;
	private Sede sedeSeleccionada;
	private int cantGuiasNecesarios, cantidadVisitantes, numeroReserva;
	private String[][] exposicionesTemporalesYVigentes;
	private final List<Exposicion> exposicionesSeleccionadas;
	private LocalDateTime fechaYHoraReserva, fechaYHoraActual;
	private LocalTime duracionEstimadaExposicion;
	private final List<Empleado> guiasSeleccionados;
	private EstadoReserva estadoPendienteDeConfirmacion;
	private Empleado empleadoEnSesion;
	private EstrategiaCalculoDuracionReserva estrategiaCalculoDuracionReserva;
	private final EscuelaServicio escuelaServicio;
	private final SedeServicio sedeServicio;
	private final TipoVisitaServicio tipoVisitaServicio;
	private final ReservaVisitaServicio reservaVisitaServicio;
	private final EmpleadoServicio empleadoServicio;
	private final AsignacionGuiaServicio asignacionGuiaServicio;
	private final EstadoReservaServicio estadoReservaServicio;
	private final SesionServicio sesionServicio;
	private List<Empleado> guiasDisponibles;

	@Autowired
	public ControladorReservaVisita(EscuelaServicio escuelaServicio, SedeServicio sedeServicio,
									TipoVisitaServicio tipoVisitaServicio,
									ReservaVisitaServicio reservaVisitaServicio,
									EmpleadoServicio empleadoServicio,
									AsignacionGuiaServicio asignacionGuiaServicio,
									EstadoReservaServicio estadoReservaServicio,
									SesionServicio sesionServicio) {
		this.escuelaServicio = escuelaServicio;
		this.sedeServicio = sedeServicio;
		this.tipoVisitaServicio = tipoVisitaServicio;
		this.reservaVisitaServicio = reservaVisitaServicio;
		this.empleadoServicio = empleadoServicio;
		this.asignacionGuiaServicio = asignacionGuiaServicio;
		this.estadoReservaServicio = estadoReservaServicio;
		this.sesionServicio = sesionServicio;
		exposicionesSeleccionadas = new ArrayList<>();
		guiasSeleccionados = new ArrayList<>();
		guiasDisponibles = new ArrayList<>();
	}

	@Autowired
	public void setPantallaReservaVisita(PantallaReservaVisita pantallaReservaVisita) {
		this.pantallaReservaVisita = pantallaReservaVisita;
	}

	/**
	 * Método que se invoca desde la pantalla una vez iniciada la interfaz. Comienza buscando las escuelas, y luego
	 * le pide a la pantalla que las presente para su selección.
	 */
	public void opcionRegistrarReservaVisita() {
		buscarEscuelas();
		pantallaReservaVisita.presentarEscuelas(escuelas);
		pantallaReservaVisita.solicitarSeleccionEscuela();
	}

	/**
	 * Método que busca y guarda en el atributo escuelas el nombre de todas las escuelas existentes.
	 */
	public void buscarEscuelas() {
		escuelas = escuelaServicio.buscarEscuelas();
	}

	/**
	 *  Método que recibe por parámetro la escuela seleccionada, la busca y la guarda el objeto Escuela como atributo.
	 *  Luego le pide a la pantalla que solicite el ingreso de la cantidad de visitantes.
	 * @param escuelaSeleccionada el nombre de la escuela seleccionada a buscar.
	 */
	public void escuelaSeleccionada(String escuelaSeleccionada) {
		this.escuelaSeleccionada =	escuelaServicio.encontrarPorNombre(escuelaSeleccionada);
		pantallaReservaVisita.solicitarCantidadVisitantes();
	}

	/**
	 * Método que guarda en una atributo la cantidad de visitantes pasados por parámetro.
	 * @param cantidadVisitantes la cantidad de visitantes ingresada por el usuario.
	 */
	public void cantidadDeVisitantesIngresados(int cantidadVisitantes) {
		this.cantidadVisitantes = cantidadVisitantes;
		buscarSedes();
		pantallaReservaVisita.presentarSedes(sedes);
		pantallaReservaVisita.solicitarSeleccionSede();
	}

	/**
	 * Método que busca y guarda en una lista el nombre de todas las sedes existentes.
	 */
	public void buscarSedes() {
		sedes = sedeServicio.buscarSedes();
	}

	/**
	 * Método que guarda como atributo la sede seleccionada pasada por parámetro. Luego continúa buscando los tipos
	 * de visita y presentándolos para su selección.
	 * @param sedeSeleccionada la sede seleccionada por el usuario.
	 */
	public void sedeSeleccionada(String sedeSeleccionada) {
		this.sedeSeleccionada = sedeServicio.encontrarPorNombre(sedeSeleccionada);
		if (exposicionesSeleccionadas.size() > 0)
			exposicionesSeleccionadas.clear();
		if (guiasSeleccionados.size() > 0)
			guiasSeleccionados.clear();
		buscarTiposDeVisita();
		pantallaReservaVisita.presentarTiposVisita(tiposVisita);
		pantallaReservaVisita.solicitarSeleccionTipoVisita();
	}

	/**
	 * Método que busca el nombre de todos los tipos de visita existentes y los guarda en una lista como atributo.
	 */
	public void buscarTiposDeVisita() {
		tiposVisita = tipoVisitaServicio.buscarTiposVisita();
	}

	/**
	 * Método que toma el nombre el tipo de visita seleccionado, verifica que sea "Por Exposición", lo guarda y
	 * continúa pidiéndole a la pantalla que busque y muestre las exposiciones temporales y vigentes para su selección.
	 * @param tipoVisitaSeleccionado el nombre del tipo de visita seleccionado por el usuario.
	 */
	public void tipoVisitaSeleccionado(String tipoVisitaSeleccionado) {
		TipoVisita tipoVisitaIngresado = tipoVisitaServicio.encontrarPorNombre(tipoVisitaSeleccionado);
		crearEstrategia(tipoVisitaIngresado);
		if (tipoVisitaIngresado.esPorExposicion()) {
			buscarExposicionesTemporalesYVigentes();
			pantallaReservaVisita.presentarExposicionesTemporalesYVigentes(exposicionesTemporalesYVigentes);
			pantallaReservaVisita.solicitarSeleccionExposiciones();
		}
		// Si se selecciona el tipo de visita "Completa", se informa que seleccione "Por Exposición".
		else if (tipoVisitaIngresado.esCompleta())
			pantallaReservaVisita.informarSeleccionTipoVisitaPorExposicion();
	}

	private void crearEstrategia(TipoVisita tipoVisitaIngresado) {
		// Si se selecciona el tipo de visita "Por Exposición", se continúa con el caso de uso.
		if (tipoVisitaIngresado.esPorExposicion())
			estrategiaCalculoDuracionReserva = new EstrategiaVisitaPorExposicion();
	}

	/**
	 * Método para tomar la estrategia de cálculo de la duración de la reserva.
	 * @param estrategiaCalculoDuracionReserva
	 */
	public void setEstrategia(EstrategiaCalculoDuracionReserva estrategiaCalculoDuracionReserva) {
		this.estrategiaCalculoDuracionReserva = estrategiaCalculoDuracionReserva;
	}

	/**
	 * Método que busca y guarda en un atributo una lista con los datos generales de todas exposiciones temporales
	 * y vigentes para la sede seleccionada.
	 */
	public void buscarExposicionesTemporalesYVigentes() {
		exposicionesTemporalesYVigentes = sedeSeleccionada.buscarExposicionesTemporalesYVigentes();
	}

	/**
	 * Método que toma una exposición y la añade a la lista de exposiciones seleccionadas, y se continúa
	 * con el caso de uso pidiéndole a la pantalla que solicite el ingreso de la fecha y hora de la reserva.
	 * @param idExposicionSeleccionada el id de la exposición a añadir.
	 */
	public void exposicionSeleccionada(int idExposicionSeleccionada) {
		exposicionesSeleccionadas
				.add(sedeSeleccionada.encontrarExposicionTemporalYVigentePorId(idExposicionSeleccionada));
		// Con que se seleccione una exposición, se puede continuar con el caso de uso.
		if (exposicionesSeleccionadas.size() == 1)
			pantallaReservaVisita.solicitarFechaYHoraReserva();
	}

	/**
	 * Método que toma una exposición y la quita de la lista de exposiciones seleccionadas, y se continúa
	 * con el caso de uso pidiéndole a la pantalla que solicite el ingreso de la fecha y hora de la reserva.
	 * @param idExposicionDeseleccionada el id de la exposición a quitar.
	 */
	public void exposicionDeseleccionada(int idExposicionDeseleccionada) {
		exposicionesSeleccionadas
				.remove(sedeSeleccionada.encontrarExposicionTemporalYVigentePorId(idExposicionDeseleccionada));
		// Si la lista de exposiciones seleccionadas tiene al menos un elemento, se continúa con el caso de uso.
		if (exposicionesSeleccionadas.size() == 1)
			pantallaReservaVisita.solicitarFechaYHoraReserva();
		// Si no, se deshabilita la selección de la fecha y hora de reserva.
		else if (exposicionesSeleccionadas.size() < 1)
			pantallaReservaVisita.deshabilitarSeleccionFechaYHoraReserva();
	}

	/**
	 * Método que toma y guarda en un atributo la fecha y hora ingresados por el usuario.
	 * @param fechaYHoraIngresadas la fecha y hora ingresados por el usuario.
	 */
	public void fechaYHoraReservaIngresados(LocalDateTime fechaYHoraIngresadas) {
		fechaYHoraReserva = fechaYHoraIngresadas;
		calcularDuracionEstimada();
		pantallaReservaVisita.presentarDuracionEstimada(duracionEstimadaExposicion.toString());
		/* Se verifica que la cantidad de visitantes ingresados no sobrepase el límite de visitantes de la sede para
		   la duración de la visita en la fecha y hora ingresados. Si no lo supera, se continua con el caso de uso. */
		if (!superaLimiteVisitantes())
			buscarGuiasDisponiblesPorHorarioReserva();
		// Si sobrepasa, se informa un mensaje de error por pantalla.
		else
			pantallaReservaVisita.informarLimiteVisitantesSuperado();
	}

	/**
	 * Método que calcula la duración estimada para la visita, a partir de las exposiciones seleccionadas.
	 */
	public void calcularDuracionEstimada() {
		duracionEstimadaExposicion = estrategiaCalculoDuracionReserva
				.calcularDuracionEstimadaVisita(exposicionesSeleccionadas);
	}

	/**
	 * Método que determina si la visita supera el límite de visitantes para la sede para la duración de la visita.
	 * @return verdadero si la visita supera el límite de visitantes para la sede para la duración de la visita,
	 * y falso en cualquier otro caso.
	 */
	public boolean superaLimiteVisitantes() {
		// Se obtienen todas las reservas de todas las sedes para pasarlas por parámetro.
		List<ReservaVisita> reservasDeVisita = reservaVisitaServicio.listarReservasVisita();
		return sedeSeleccionada.superaLimiteVisitantesParaFechaYHora(
				cantidadVisitantes, fechaYHoraReserva, duracionEstimadaExposicion, reservasDeVisita);
	}

	/**
	 * Método que busca todos los guías disponibles según la fecha y hora de reserva ingresados.
	 */
	public void buscarGuiasDisponiblesPorHorarioReserva() {
		List<Empleado> empleados = empleadoServicio.listarEmpleados();
		List<AsignacionGuia> asignacionesDeGuia = asignacionGuiaServicio.listarAsignacionesGuia();
		guiasDisponibles = sedeSeleccionada.listarGuiasDisponiblesPorHorarioDeReserva(
				fechaYHoraReserva, empleados, asignacionesDeGuia);
		String[][] datosGuiasDisponibles = sedeSeleccionada.buscarGuiasDisponiblesPorHorarioDeReserva(
				fechaYHoraReserva, empleados, asignacionesDeGuia);
		calcularGuiasNecesarios();
		pantallaReservaVisita.presentarGuiasDisponibles(datosGuiasDisponibles, cantGuiasNecesarios);
		pantallaReservaVisita.solicitarSeleccionGuiasDisponibles();
	}

	/**
	 * Método que calcula los guías necesarios para la visita según la capacidad de visitantes por guía de la sede.
	 */
	public void calcularGuiasNecesarios() {
		cantGuiasNecesarios = sedeSeleccionada.
				calcularGuiasNecesariosParaVisitantesIngresados(cantidadVisitantes);
	}

	/**
	 * Método que toma un guía seleccionado y lo añade a la lista de guías seleccionados.
	 * @param idGuia el id del guía seleccionado.
	 */
	public void guiaDisponibleSeleccionado(int idGuia) {
		for (Empleado guia:
			 guiasDisponibles) {
			if (guia.getIdEmpleado() == idGuia)
				guiasSeleccionados.add(guia);
		}
		// Si la lista se llena con la cantidad de guías necesarios, se procede con el caso de uso.
		int cantGuiasSeleccionados = guiasSeleccionados.size();
		if (cantGuiasSeleccionados == cantGuiasNecesarios)
			pantallaReservaVisita.solicitarConfirmacionReserva();
		else if (cantGuiasSeleccionados > cantGuiasNecesarios) {
			pantallaReservaVisita.informarGuiasExcedidos();
			pantallaReservaVisita.ocultarConfirmacionReserva();
		}
	}

	/**
	 * Método que toma un guía deseleccionado y lo quita de la lista de guías seleccionados.
	 * @param idGuia el id del gutomasía seleccionado.
	 */
	public void guiaDisponibleDeseleccionado(int idGuia) {
		for (Empleado guia:
				guiasDisponibles) {
			if (guia.getIdEmpleado() == idGuia)
				guiasSeleccionados.remove(guia);
		}
		int cantGuiasSeleccionados = guiasSeleccionados.size();
		// Si la lista se llena con la cantidad de guías necesarios, se procede con el caso de uso.
		if (cantGuiasSeleccionados == cantGuiasNecesarios)
			pantallaReservaVisita.solicitarConfirmacionReserva();
		else
			pantallaReservaVisita.ocultarConfirmacionReserva();
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
		buscarEstadoPendienteDeConfirmacion();
		// Se genera una nueva reserva de visita con todos los datos obtenidos.
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
		// Se guarda la visita en la base de datos.
		reservaVisitaServicio.guardarReservaVisita(nuevaReserva);
	}

	/**
	 * Método que crea una nueva reserva a partir de los datos ingresados por el usuario, aplicando patrón State.
	 */
	public void crearReservaState() {
		generarNroReserva();
		getFechaYHoraActual();
		getEmpleadoEnSesion();
		EstadoReservaVisita estadoInicial = new PendienteDeConfirmacion();
		// Se genera una nueva reserva de visita con todos los datos obtenidos.
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
				guiasSeleccionados,
				estadoInicial
		);
		// Se guarda la visita en la base de datos.
		reservaVisitaServicio.guardarReservaVisita(nuevaReserva);
	}

	/**
	 * Método que genera un número único para la nueva reserva.
	 */
	public void generarNroReserva() {
		numeroReserva = getUltimoNroReserva() + 1;
	}

	/**
	 * Método que recorre todas las reservas existentes y obtiene el número de la última de ellas.
	 * @return el número de la última reserva registrada.
	 */
	public int getUltimoNroReserva() {
		return reservaVisitaServicio.getUltimoNroReserva();
	}

	/**
	 * Método que obtiene el empleado en la sesión actual.
	 */
	public void getEmpleadoEnSesion() {
		empleadoEnSesion = sesionServicio.getEmpleadoEnSesion();
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
		estadoPendienteDeConfirmacion = estadoReservaServicio.buscarEstadoPendienteDeConfirmacion();
	}

	/**
	 * Método que finaliza el caso de uso.
	 */
	public void finCU() {
		pantallaReservaVisita.informarReservaRegistrada();
	}
}//end ControladorReservaVisita