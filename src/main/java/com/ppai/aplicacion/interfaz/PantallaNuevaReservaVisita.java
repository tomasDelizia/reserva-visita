package com.ppai.aplicacion.interfaz;

import com.ppai.aplicacion.controlador.ControladorNuevaReservaVisita;
import com.ppai.aplicacion.negocio.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PantallaNuevaReservaVisita implements Initializable {

	private ControladorNuevaReservaVisita controlador;


	@FXML
	private ComboBox<Escuela> cboEscuelas;

	@FXML
	private ComboBox<TipoVisita> cboTiposDeVisita;

	@FXML
	public ComboBox<Sede> cboSedes;

	@FXML
	public Label lblVisitantes;


	@Autowired
	public void setControlador(ControladorNuevaReservaVisita controlador) {
		this.controlador = controlador;
	}


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		opcionNuevaReservaVisita();
	}

	public void opcionNuevaReservaVisita(){
		habilitarPantalla();
		controlador.opcionRegistrarReservaVisita();
	}

	public void habilitarPantalla(){}

	public void presentarEscuelas(ActionEvent actionEvent) {}

	public void presentarEscuelas(List<Escuela> listaEscuelas) {
		cboEscuelas.setItems(FXCollections.observableArrayList(listaEscuelas));
	}

	public void solicitarSeleccionEscuela(){}

	public void tomarSeleccionEscuela(){
		controlador.escuelaSeleccionada(cboEscuelas.getValue());
	}

	public void solicitarCantidadVisitantes(){}

	public void tomarCantidadVisitantes(){
		int visitantes = Integer.parseInt(lblVisitantes.getText());
		controlador.cantidadVisitantesIngresados(visitantes);
	}

	public void presentarSedes(){
	}

	public void presentarSedes(List<Sede> listaSedes){
		cboSedes.setItems(FXCollections.observableArrayList(listaSedes));
	}

	public void solicitarSeleccionSede(){}

	public void tomarSede(){
		Sede sede = cboSedes.getValue();
		controlador.sedeSeleccionada(sede);
	}

	public void presentarTiposDeVisita(){

	}

	public void solicitarTipoVisita(){}

	public void tomarTipoVisita(){

	}

	public void presentarExposicionesTemporalesYVigentes(){

	}

	public void solicitarSeleccionExposiciones(){}

	public void tomarSeleccionExposiciones(){

	}

	public void solicitarFechaYHoraReserva(){}

	public void tomarFechaYHoraReserva(){

	}

	public void presentarGuiasDisponibles(){

	}

	public void tomarSeleccionGuiasDisponibles(){

	}

	public void solicitarSeleccionGuiasDisponibles(){

	}

	public void solicitarConfirmacionReserva() {

	}

	public void tomarConfirmacionReserva() {

	}

}//end com.ppai.reservavisita.PantallaNuevaReservaVisita