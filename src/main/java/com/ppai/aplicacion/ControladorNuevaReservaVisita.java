package com.ppai.aplicacion;


import com.ppai.aplicacion.negocio.Empleado;
import com.ppai.aplicacion.negocio.Escuela;
import com.ppai.aplicacion.repo.EscuelaRepo;
import com.ppai.aplicacion.repo.TipoVisitaRepo;
import com.ppai.aplicacion.negocioOld.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ppai.aplicacion.negocio.TipoVisita;

import java.net.URL;
import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:37 am
 */
@Component
public class ControladorNuevaReservaVisita {

	//public PantallaNuevaReservaVisita pantalla;

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


//	public ControladorNuevaReservaVisita(PantallaNuevaReservaVisita pantallaNuevaReservaVisita) {
//		this.pantalla = pantallaNuevaReservaVisita;
//	}

//	public void setPantalla(PantallaNuevaReservaVisita pantallaNuevaReservaVisita) {
//		this.pantalla = pantallaNuevaReservaVisita;
//	}
//
//	public void initialize() {
//		this.buscarEscuelas(escuelaRepo);
//		pantalla.presentarEscuelasAlt(this.escuelas);
//	}

	public void opcionRegistrarReservaVisita(){
		//this.buscarEscuelas(escuelaRepo);
		//pantalla.presentarEscuelas(escuelas);
	}

//	public void buscarEscuelas(EscuelaRepo escuelaRepo){
//		this.escuelas = escuelaRepo.findAll();
//	}

	public List<Escuela> getEscuelas() {
		return escuelas;
	}

	public void escuelaSeleccionada(){

	}

	public void cantidadVisitantesIngresados(){

	}

	public void buscarSedes(){

	}

	public void sedeSeleccionada(){

	}

	public void buscarTiposDeVisita() {
		//tiposDeVisita = tipoVisitaRepo.findAll();
	}

//	public void buscarTiposDeVisita(){
//		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=museo_pictorico;integratedSecurity=true";
//		try (Connection con = DriverManager.getConnection(connectionUrl);
//			 Statement stmt = con.createStatement();) {
//			String SQL = "SELECT * FROM TIPOS_DE_EXPOSICION";
//			ResultSet rs = stmt.executeQuery(SQL);
//			while (rs.next()) {
//				TipoVisita tipVis = new TipoVisita(rs.getString("nombre"));
//				tiposDeVisita.add(tipVis);
//			}
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public List<TipoVisita> getTiposDeVisita() {
		return tiposDeVisita;
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
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=museo_pictorico;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl);
			 Statement stmt = con.createStatement();) {
			String SQL = "SELECT * FROM ESTADOS_DE_RESERVA";
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				EstadoReserva estado = new EstadoReserva(rs.getString("nombre"), rs.getString("descripcion"));
				if (estado.esPendienteDeConfirmacion())
					estadoPendiente = estado;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public EstadoReserva getEstadoPendiente() {
		return estadoPendiente;
	}

	public void finCU(){

	}


}//end ControladorNuevaReservaVisita