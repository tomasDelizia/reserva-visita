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
		List<Usuario> usuarios = usuarioRepo.findAll();
		for (Usuario usuario:
			 usuarios) {
			if (usuario.correspondeUsuarioYContrasena(nombreUsuario, contrasena)
				&& usuario.getEmpleado().esResponsableDeVisitas()) {
				sesionActual = new Sesion(LocalDateTime.now(), usuario);
				break;
			}
		}
		if (sesionActual != null)
			pantalla.informarLoginConExito();
		else
			pantalla.informarLoginFallido();

	}

	public void opcionRegistrarReservaVisita(){
		buscarEscuelas();
		pantalla.presentarEscuelas(escuelas);
		pantalla.solicitarSeleccionEscuela();
	}

	public void buscarEscuelas() {
		escuelas = escuelaRepo.findAll();
	}

	public void escuelaSeleccionada(Escuela escuelaSeleccionada){
		this.escuelaSeleccionada =	escuelaSeleccionada;
		pantalla.solicitarCantidadVisitantes();
	}

	public void cantidadDeVisitantesIngresados(int cantidadVisitantes){
		this.cantidadVisitantes = cantidadVisitantes;
		buscarSedes();
		pantalla.presentarSedes(sedes);
		pantalla.solicitarSeleccionSede();
	}

	public void buscarSedes(){
		sedes = sedeRepo.findAll();
	}

	public void sedeSeleccionada(Sede sedeSeleccionada){
		this.sedeSeleccionada = sedeSeleccionada;
		buscarTiposDeVisita();
		pantalla.solicitarSeleccionTipoVisita();
	}

	public void buscarTiposDeVisita() {
		tiposDeVisita = tipoVisitaRepo.findAll();
	}

	public void tipoVisitaSeleccionada(String nombreTipoVisita){
		TipoVisita tipoVisita = new TipoVisita(nombreTipoVisita);
		for (TipoVisita tipoDeVisita:
			 tiposDeVisita) {
			if (tipoDeVisita.esTipoDeVisita(nombreTipoVisita))
				tipoVisitaSeleccionado = tipoDeVisita;
		}
		buscarExposicionesTemporalesYVigentes();
		pantalla.presentarExposicionesTemporalesYVigentes(exposicionesTemporalesYVigentes);
		pantalla.solicitarSeleccionExposiciones();
	}

	public void buscarExposicionesTemporalesYVigentes(){
		exposicionesTemporalesYVigentes = sedeSeleccionada.buscarExposicionesTemporalesYVigentes();
	}

	public void exposicionSeleccionada(Exposicion exposicionSeleccionada){
		exposicionesSeleccionadas.add(exposicionSeleccionada);
		if (exposicionesSeleccionadas.size() == 1)
			pantalla.solicitarFechaYHoraReserva();
	}

	public void fechaYHoraReservaIngresados(LocalDateTime fechaYHoraIngresados){
		fechaYHoraReserva = fechaYHoraIngresados;
		calcularDuracionEstimada();
		pantalla.presentarDuracionEstimada(duracionEstimadaExposicion);
		if (!superaLimiteVisitantes())
			buscarGuiasDisponiblesPorHorarioReserva();
		else
			pantalla.informarLimiteVisitantesSuperado();
	}

	public void calcularDuracionEstimada(){
		duracionEstimadaExposicion = sedeSeleccionada.
				calcularDuracionEstimadaVisitaPorExposicion(exposicionesSeleccionadas);
	}

	public boolean superaLimiteVisitantes(){
		List<ReservaVisita> reservasDeVisita = reservaVisitaRepo.findAll();
		return sedeSeleccionada.superaLimiteVisitantesParaFechaYHora(
				cantidadVisitantes, fechaYHoraReserva, duracionEstimadaExposicion, reservasDeVisita);
	}

	public void buscarGuiasDisponiblesPorHorarioReserva(){
		List<Empleado> empleados = empleadoRepo.findAll();
		List<AsignacionGuia> asignacionesDeGuia = asignacionGuiaRepo.findAll();
		guiasDisponibles = sedeSeleccionada.buscarGuiasDisponiblesPorHorarioDeReserva(
				fechaYHoraReserva, empleados, asignacionesDeGuia);
		calcularGuiasNecesarios();
		pantalla.presentarGuiasDisponibles(guiasDisponibles, cantGuiasNecesarios);
		pantalla.solicitarSeleccionGuiasDisponibles();
	}

	public void calcularGuiasNecesarios(){
		cantGuiasNecesarios = sedeSeleccionada.
				calcularGuiasNecesariosParaVisitantesIngresados(cantidadVisitantes);
	}

	public void guiaDisponibleSeleccionado(Empleado empleadoSeleccionado){
		guiasSeleccionados.add(empleadoSeleccionado);
		if (guiasSeleccionados.size() == cantGuiasNecesarios)
			pantalla.solicitarConfirmacionReserva();
	}

	public void confirmacionReservaSeleccionada() {
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
				estadoPendienteDeConfirmacion
				//guiasSeleccionados
				);
		reservaVisitaRepo.save(nuevaReserva);
		pantalla.mostrarReserva(nuevaReserva);
	}

	public void generarNroReserva(){
		numeroReserva = obtenerUltimoNumeroReserva() + 1;
	}

	public int obtenerUltimoNumeroReserva(){
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
		empleadoEnSesion = sesionActual.getEmpleadoEnSesion();
	}

	public void getFechaYHoraActual(){
		fechaYHoraActual = LocalDateTime.now();
	}

	public void buscarEstadoPendienteDeConfirmacion(){
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