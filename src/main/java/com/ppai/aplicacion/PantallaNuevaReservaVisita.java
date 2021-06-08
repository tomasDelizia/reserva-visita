package com.ppai.aplicacion;

import com.ppai.aplicacion.persistencia.Escuela;
import com.ppai.aplicacion.persistencia.TipoDeVisita;
import com.ppai.aplicacion.repo.EscuelaRepo;
import com.ppai.aplicacion.repo.TipoDeVisitaRepo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class PantallaNuevaReservaVisita implements Initializable {
	//public ControladorNuevaReservaVisita controlador;

	@FXML
	public ComboBox<Escuela> cboEscuelas;

	@FXML
	public ComboBox<TipoDeVisita> cboTiposDeVisita;

	@Autowired
	private TipoDeVisitaRepo tipoDeVisitaRepo;

	@Autowired
	private EscuelaRepo escuelaRepo;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		presentarEscuelas();
		presentarTiposDeVisita();
	}

	public void presentarTiposDeVisita(){
		cboTiposDeVisita.setItems(FXCollections.observableArrayList(tipoDeVisitaRepo.findAll()));
	}

	public void opcionNuevaReservaVisita(){ // Los objetos pantalla y gestor se crean cuando presionamos el botón nuevaReservaVisita

	}

	public void habilitarPantalla(){

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


}//end com.ppai.reservavisita.PantallaNuevaReservaVisita