package interfazOld;


import com.ppai.aplicacion.negocio.Escuela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:36 am
 */
@Component
public class PantallaNuevaReservaVisitaOld implements Initializable {

	@FXML
	public ComboBox<Escuela> cboEscuelas;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		//presentarEscuelas();
	}

	public void opcionNuevaReservaVisita(){ // Los objetos pantalla y gestor se crean cuando presionamos el botón nuevaReservaVisita

	}

	public void habilitarPantalla(){

	}

	public void presentarEscuelas() {
		//ObservableList<Escuela> observableListEscuelas = controlador.buscarEscuelas();
		//this.cboEscuelas.setItems(observableListEscuelas);
	}

	public void tomarSeleccionEscuela(ActionEvent actionEvent){

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


}//end com.ppai.reservavisita.PantallaNuevaReservaVisita