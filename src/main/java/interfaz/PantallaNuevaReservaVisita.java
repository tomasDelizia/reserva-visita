package interfaz;


import controlador.ControladorNuevaReservaVisita;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import negocio.Escuela;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import java.awt.event.ActionEvent;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:36 am
 */
@Component
public class PantallaNuevaReservaVisita {
	private final ControladorNuevaReservaVisita controlador = new ControladorNuevaReservaVisita();

	@FXML
	public ComboBox<Escuela> cboEscuelas;


	public PantallaNuevaReservaVisita(){

	}

	public void habilitarPantalla(){

	}

	public void opcionNuevaReservaVisita(){ // Los objetos pantalla y gestor se crean cuando presionamos el botón nuevaReservaVisita

	}

	public void presentarEscuelas(ActionEvent actionEvent) {
		controlador.buscarEscuelas();
		ObservableList<Escuela> ol = FXCollections.observableArrayList(controlador.getEscuelas());
		cboEscuelas.setItems(ol);
	}


	public void tomarSeleccionEscuela(ActionEvent actionEvent){
		ObservableList<Escuela> observableList;

	}

	public void presentarExposicionesTemporalesYVigentes(){

	}

	public void presentarGuiasDisponibles(){

	}

	public void presentarSedes(){

	}

	public void presentarTiposVisita(){

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


}//end interfaz.PantallaNuevaReservaVisita