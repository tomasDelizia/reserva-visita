package com.ppai.aplicacion.controlador;


import com.ppai.aplicacion.interfaz.PantallaNuevaReservaVisita;
import com.ppai.aplicacion.negocio.*;
import com.ppai.aplicacion.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class ControladorNuevaReservaVisita {

	private PantallaNuevaReservaVisita pantalla;
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

	@Autowired
	private EscuelaRepo escuelaRepo;

	@Autowired
	private SedeRepo sedeRepo;

	@Autowired
	private TipoVisitaRepo tipoVisitaRepo;

	@Autowired
	private ReservaVisitaRepo reservaVisitaRepo;

	@Autowired
	private EmpleadoRepo empleadoRepo;

	@Autowired
	private AsignacionGuiaRepo asignacionGuiaRepo;

	@Autowired
	private SesionRepo sesionRepo;

	@Autowired
	private EstadoReservaRepo estadoReservaRepo;

	@Autowired
	private UsuarioRepo usuarioRepo;


	@Autowired
	public void setPantalla(PantallaNuevaReservaVisita pantalla) {
		this.pantalla = pantalla;
	}

	public void loginIngresado(String nombreUsuario, String contrasena) {
		// Método que verifica que el usuario y contraseña pasados por parámetro sean válidos
		// y que además el usuario ingresado sea un Responsable de Visitas.
		// Primero, se obtienen todos los usuarios:
		List<Usuario> usuarios = usuarioRepo.findAll();
		for (Usuario usuario:
			 usuarios) {
			// Mientras haya usuarios, le pregunto a cada uno si coinciden con el usuario y contraseña, y
			// si además su empleado correspondiente es Responsable de visitas. Si coincide, se corta
			// la iteración y se crea un nuevo objeto Sesión.
			if (usuario.correspondeUsuarioYContrasena(nombreUsuario, contrasena)
				&& usuario.correspondeAResponsableDeVisitas()) {
				sesionActual = new Sesion(LocalDateTime.now(), usuario);
				break;
			}
		}
		// Si se encontró el usuario correspondiente, se informa un mensaje de éxito en la pantalla.
		if (sesionActual != null)
			pantalla.informarLoginConExito();
		// Si no, se informa un mensaje de ingreso fallido.
		else
			pantalla.informarLoginFallido();

	}

	// MÉTODOS DEL CASO DE USO 92: REGISTRAR RESERVA DE VISITA

	public void opcionRegistrarReservaVisita(){
		buscarEscuelas();
		pantalla.presentarEscuelas(escuelas);
		pantalla.solicitarSeleccionEscuela();
	}

	public void buscarEscuelas() {
		// Método que busca y guarda en una lista todas las escuelas existentes.
		escuelas = escuelaRepo.findAll();
	}

	public void escuelaSeleccionada(Escuela escuelaSeleccionada){
		// Método que recibe por parámetro la escuela seleccionada y la guarda como atributo.
		this.escuelaSeleccionada =	escuelaSeleccionada;
		pantalla.solicitarCantidadVisitantes();
	}

	public void cantidadDeVisitantesIngresados(int cantidadVisitantes){
		// Método que guarda en una atributo la cantidad de visitantes pasados por parámetro.
		this.cantidadVisitantes = cantidadVisitantes;
		buscarSedes();
		pantalla.presentarSedes(sedes);
		pantalla.solicitarSeleccionSede();
	}

	public void buscarSedes(){
		// Método que busca todas las sedes existentes y las guarda en un atributo.
		sedes = sedeRepo.findAll();
	}

	public void sedeSeleccionada(Sede sedeSeleccionada){
		// Método que guarda como atributo la sede seleccionada.
		this.sedeSeleccionada = sedeSeleccionada;
		buscarTiposDeVisita();
		pantalla.solicitarSeleccionTipoVisita();
	}

	public void buscarTiposDeVisita() {
		// Método que busca todos los tipos de visita existentes y los guarda como atributo.
		tiposDeVisita = tipoVisitaRepo.findAll();
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
		pantalla.presentarExposicionesTemporalesYVigentes(exposicionesTemporalesYVigentes);
		pantalla.solicitarSeleccionExposiciones();
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
			pantalla.solicitarFechaYHoraReserva();
	}

	public void fechaYHoraReservaIngresados(LocalDateTime fechaYHoraIngresados){
		// Método que toma y guarda en un atributo la fecha y hora ingresados por el usuario.
		fechaYHoraReserva = fechaYHoraIngresados;
		calcularDuracionEstimada();
		pantalla.presentarDuracionEstimada(duracionEstimadaExposicion);
		// Se verifica que la cantidad de visitantes ingresados no sobrepase el límite de visitantes
		// de la sede para la duración de la visita en la fecha y hora ingreados.
		// Si no lo supera, se continua con el caso de uso.
		if (!superaLimiteVisitantes())
			buscarGuiasDisponiblesPorHorarioReserva();
		// Si sobrepasa, se informa un mensaje de error por pantalla.
		else
			pantalla.informarLimiteVisitantesSuperado();
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
		List<ReservaVisita> reservasDeVisita = reservaVisitaRepo.findAll();
		return sedeSeleccionada.superaLimiteVisitantesParaFechaYHora(
				cantidadVisitantes, fechaYHoraReserva, duracionEstimadaExposicion, reservasDeVisita);
	}

	public void buscarGuiasDisponiblesPorHorarioReserva(){
		// Método que busca todos los guías disponibles según la fecha y hora de reserva ingresados.
		List<Empleado> empleados = empleadoRepo.findAll();
		List<AsignacionGuia> asignacionesDeGuia = asignacionGuiaRepo.findAll();
		guiasDisponibles = sedeSeleccionada.buscarGuiasDisponiblesPorHorarioDeReserva(
				fechaYHoraReserva, empleados, asignacionesDeGuia);
		calcularGuiasNecesarios();
		pantalla.presentarGuiasDisponibles(guiasDisponibles, cantGuiasNecesarios);
		pantalla.solicitarSeleccionGuiasDisponibles();
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
			pantalla.solicitarConfirmacionReserva();
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
		sesionRepo.save(sesionActual);
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
		reservaVisitaRepo.save(nuevaReserva);
	}

	public void generarNroReserva(){
		// Método que genera un número único para la nueva reserva.
		numeroReserva = obtenerUltimoNumeroReserva() + 1;
	}

	public int obtenerUltimoNumeroReserva(){
		// Método que recorre todas las reservas existentes y obtiene el número de la última de ellas.
		int ultimoNumeroReserva = 0;
		List<ReservaVisita> reservasDeVisita = reservaVisitaRepo.findAll();
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
		List<EstadoReserva> estadosDeReserva = estadoReservaRepo.findAll();
		for (EstadoReserva estadoReserva:
			 estadosDeReserva) {
			if (estadoReserva.esPendienteDeConfirmacion()) {
				estadoPendienteDeConfirmacion = estadoReserva;
				break;
			}
		}
	}

	public void finCU(){}
}//end ControladorNuevaReservaVisita