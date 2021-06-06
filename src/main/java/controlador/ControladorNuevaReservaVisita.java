package controlador;


import interfaz.PantallaNuevaReservaVisita;
import negocio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import persistencia.TipoDeVisita;
import repo.ITipoDeVisitaRepo;

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
@Controller
public class ControladorNuevaReservaVisita {

	private PantallaNuevaReservaVisita pantalla;
	private final List<Escuela> escuelas = new ArrayList<>();
	private Escuela escuelaSeleccionada;
	private List<Sede> sedes;
	private Sede sedeSeleccionada;
	private List<TipoDeVisita> tiposDeVisita = new ArrayList<>();
	private TipoVisita tipoVisitaSeleccionado;
	private int cantGuiasNecesarios, cantidadVisitantes;
	private List<Exposicion> exposicionesTemporalesYVigentes, exposicionesSeleccionadas;
	private LocalDateTime fechaYHoraReservaSeleccionada;
	private LocalTime duracionEstimadaExposicion;
	private List<Empleado> guiasDisponibles, guiasSeleccionados;
	private EstadoReserva estadoPendiente;

	@Autowired
	private ITipoDeVisitaRepo repoTiposDeVisita;

	public void addtipo3() {
		String tip3 = "Tipo 3 xd";
		TipoDeVisita tipo3 = new TipoDeVisita(tip3);
		repoTiposDeVisita.save(tipo3);
	}

	public void opcionRegistrarReservaVisita(){

	}


//	public void buscarEscuelas(){
//		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=museo_pictorico;integratedSecurity=true";
//		try (Connection con = DriverManager.getConnection(connectionUrl);
//			 Statement stmt = con.createStatement();) {
//			String SQL = "SELECT * FROM ESCUELAS";
//			ResultSet rs = stmt.executeQuery(SQL);
//			while (rs.next()) {
//				Escuela escuela = new Escuela(rs.getString("nombre"), rs.getString("mail"),
//						rs.getString("calle_nombre"), rs.getInt("calle_numero"),
//						rs.getString("telefono_celular"), rs.getString("telefono_fijo"));
//				escuelas.add(escuela);
//			}
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
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
		tiposDeVisita = repoTiposDeVisita.findAll();
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