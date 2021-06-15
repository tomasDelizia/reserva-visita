package com.ppai.aplicacion.controlador;


import com.ppai.aplicacion.interfaz.PantallaNuevaReservaVisita;
import com.ppai.aplicacion.negocio.*;
import com.ppai.aplicacion.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.Duration;
import java.time.LocalDateTime;
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
	private int cantGuiasNecesarios, cantidadVisitantes;
	private List<Exposicion> exposicionesTemporalesYVigentes, exposicionesSeleccionadas;
	private LocalDateTime fechaYHoraReservaSeleccionada;
	private Duration duracionEstimadaExposicion;
	private List<Empleado> guiasDisponibles, guiasSeleccionados;
	private EstadoReserva estadoPendiente;

	@Autowired
	private EscuelaRepo escuelaRepo;

	@Autowired
	private SedeRepo sedeRepo;

	@Autowired
	private TipoVisitaRepo tipoVisitaRepo;




	@Autowired
	public void setPantalla(PantallaNuevaReservaVisita pantalla) {
		this.pantalla = pantalla;
	}

	public void opcionRegistrarReservaVisita(){
		buscarEscuelas();
		pantalla.presentarEscuelas(escuelas);
	}

	public void buscarEscuelas() {
		escuelas = escuelaRepo.findAll();
	}

	public void escuelaSeleccionada(Escuela escuelaSeleccionada){
		this.escuelaSeleccionada =	escuelaSeleccionada;
		pantalla.solicitarCantidadVisitantes();
	}

	public void cantidadVisitantesIngresados(int cantidadVisitantes){
		this.cantidadVisitantes = cantidadVisitantes;
	}

	public void buscarSedes(){
		sedes = sedeRepo.findAll();
	}

	public void sedeSeleccionada(Sede sedeSeleccionada){
		this.sedeSeleccionada = sedeSeleccionada;
	}

	public void buscarTiposDeVisita() {
		tiposDeVisita = tipoVisitaRepo.findAll();
	}

	public void tipoVisitaSeleccionada(){

	}

	public void buscarExposicionesTemporalesYVigentes(){

	}

	public void exposicionesSeleccionadas(){

	}

	public void fechaYHoraReservaIngresados(){

	}

	public void calcularDuracionEstimada(){

	}

	public void superaLimiteVisitantes(){

	}

	public void buscarGuiasDisponiblesPorHorarioReserva(){

	}

	public void calcularGuiasNecesarios(){

	}

	public void guiasDisponiblesSeleccionados(){

	}

	public void confirmacionReservaSeleccionada() {

	}

	public void crearReserva(){

	}

	public void generarNroReserva(){

	}

	public void obtenerUltimoNumeroReserva(){

	}

	public void getEmpleadoEnSesion() {

	}

	public LocalDateTime getFechaYHoraActual(){
		return LocalDateTime.now();
	}

	public void buscarEstadoPendienteDeConfirmacion(){
	}

	public void finCU(){

	}



}//end ControladorNuevaReservaVisita