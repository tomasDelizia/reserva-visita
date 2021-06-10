package com.ppai.aplicacion;

import com.ppai.aplicacion.negocio.Empleado;
import com.ppai.aplicacion.negocio.Escuela;
import com.ppai.aplicacion.negocio.TipoVisita;
import com.ppai.aplicacion.repo.EmpleadoRepo;
import com.ppai.aplicacion.repo.EscuelaRepo;
import com.ppai.aplicacion.repo.TipoVisitaRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PantallaNuevaReservaVisita implements Initializable {

	//@FXML
	//private ControladorNuevaReservaVisita controlador;

	@FXML
	public ComboBox<Escuela> cboEscuelas;

	@FXML
	public ComboBox<TipoVisita> cboTiposDeVisita;

	@FXML
	public ComboBox<Empleado> cboEmpleados;

	@Autowired
	private TipoVisitaRepo tipoVisitaRepo;

	@Autowired
	private EscuelaRepo escuelaRepo;

	@Autowired
	private EmpleadoRepo empleadoRepo;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		//controlador.setPantalla(this);
		opcionNuevaReservaVisita();
	}

	public void opcionNuevaReservaVisita(){ // Los objetos pantalla y gestor se crean cuando presionamos el botón nuevaReservaVisita
		//this.controlador.setPantalla(this);
		//controlador.opcionRegistrarReservaVisita();
		//List<Escuela> escuelas = controlador.opcionRegistrarReservaVisita(escuelaRepo);
		presentarEscuelas();
		presentarGuiasDisponibles();
	}

	public void habilitarPantalla(){

	}

	public void presentarEscuelasAlt(List<Escuela> listaEscuelas) {
		//cboEscuelas.setItems(FXCollections.observableArrayList(listaEscuelas));
	}

	public void presentarEscuelas() {
		cboEscuelas.setItems(FXCollections.observableArrayList(escuelaRepo.findAll()));
		//ObservableList<Escuela> observableListEscuelas = controlador.buscarEscuelas();
		//this.cboEscuelas.setItems(observableListEscuelas);
	}

	public void tomarSeleccionEscuela(){

	}

	public void presentarExposicionesTemporalesYVigentes(){

	}

	public void presentarGuiasDisponibles(){
		//cboEmpleados.setItems(FXCollections.observableArrayList(empleadoRepo.findAll()));
	}

	public void presentarSedes(){

	}

	public void solicitarCantidadVisitantes(){

	}

	public void solicitarConfirmacionReserva() {

	}

	public void solicitarFechaYHoraReserva(){

	}

	public void solicitarSeleccionEscuela(){

	}

	public void solicitarSeleccionExposiciones(){

	}

	public void solicitarSeleccionGuiasNecesarios(){

	}

	public void solicitarSeleccionSede(){

	}

	public void solicitarTipoVisita(){

	}

	public void tomarCantidadVisitantes(){

	}

	public void tomarConfirmacionReserva() {

	}

	public void tomarFechaYHoraReserva(){

	}

	public void tomarSede(){

	}


	public void tomarSeleccionExposiciones(){

	}

	public void tomarSeleccionGuiasDisponibles(){

	}

	public void tomarTipoVisita(){

	}


	public void presentarTiposDeVisita(){
		cboTiposDeVisita.setItems(FXCollections.observableArrayList(tipoVisitaRepo.findAll()));
	}


}//end com.ppai.reservavisita.PantallaNuevaReservaVisita