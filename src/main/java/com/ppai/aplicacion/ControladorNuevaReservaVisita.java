package com.ppai.aplicacion;


import com.ppai.aplicacion.repo.TipoDeVisitaRepo;
import com.ppai.aplicacion.negocio.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ppai.aplicacion.persistencia.TipoDeVisita;

import java.sql.*;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:37 am
 */
@Component
public class ControladorNuevaReservaVisita {

	private final List<Escuela> escuelas = new ArrayList<>();
	private Escuela escuelaSeleccionada;
	private List<Sede> sedes;
	private Sede sedeSeleccionada;
	private List<TipoDeVisita> tiposDeVisita;
	private TipoDeVisita tipoVisitaSeleccionado;
	private int cantGuiasNecesarios, cantidadVisitantes;
	private List<Exposicion> exposicionesTemporalesYVigentes, exposicionesSeleccionadas;
	private LocalDateTime fechaYHoraReservaSeleccionada;
	private LocalTime duracionEstimadaExposicion;
	private List<Empleado> guiasDisponibles, guiasSeleccionados;
	private EstadoReserva estadoPendiente;

	@Autowired
	private TipoDeVisitaRepo tipoDeVisitaRepo;

	public void buscarTiposDeVisita() {
		tiposDeVisita = tipoDeVisitaRepo.findAll();
	}

	public void opcionRegistrarReservaVisita(){

	}

	public ObservableList<Escuela> buscarEscuelas(){
		ObservableList<Escuela> ol = FXCollections.observableArrayList();

		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=museo_pictorico;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl);
			 Statement stmt = con.createStatement();) {
			String SQL = "SELECT * FROM ESCUELAS";
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				Escuela escuela = new Escuela(rs.getString("nombre"), rs.getString("mail"),
						rs.getString("calle_nombre"), rs.getInt("calle_numero"),
						rs.getString("telefono_celular"), rs.getString("telefono_fijo"));
				ol.add(escuela);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return ol;
	}

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

	public List<TipoDeVisita> getTiposDeVisita() {
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

	public void getEmpleadoEnSesion(){

	}

	public LocalDateTime obtenerFechaHoraActual(){
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

	public void buscarEstadoPendienteDeConfirmacionAlt(){	// Eliminar a la bosta
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=museo_pictorico;integratedSecurity=true";
		try (Connection con = DriverManager.getConnection(connectionUrl);
			 Statement stmt = con.createStatement();) {
			String SQL = "SELECT * FROM ESTADOS_DE_RESERVA WHERE nombre = N'Pendiente de Confirmación'";
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