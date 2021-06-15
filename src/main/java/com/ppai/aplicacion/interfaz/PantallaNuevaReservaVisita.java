package com.ppai.aplicacion.interfaz;

import com.ppai.aplicacion.controlador.ControladorNuevaReservaVisita;
import com.ppai.aplicacion.negocio.Empleado;
import com.ppai.aplicacion.negocio.Escuela;
import com.ppai.aplicacion.negocio.Exposicion;
import com.ppai.aplicacion.negocio.TipoVisita;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PantallaNuevaReservaVisita implements Initializable {

	@FXML
	private CheckBox chkBox;
	
	
	private ControladorNuevaReservaVisita controlador;

	@FXML
	public TextArea txtExpo;

	@FXML
	public ComboBox<Escuela> cboEscuelas;

	@FXML
	public ComboBox<TipoVisita> cboTiposDeVisita;

	@FXML
	public ComboBox<Empleado> cboEmpleados;

	@FXML
	public ComboBox<Exposicion> cboExpo;

	@Autowired
	public void setControlador(ControladorNuevaReservaVisita controlador) {
		this.controlador = controlador;
	}

	public ControladorNuevaReservaVisita getControlador() {
		return controlador;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		opcionNuevaReservaVisita();
	}

	public void opcionNuevaReservaVisita(){ // Los objetos pantalla y gestor se crean cuando presionamos el botón nuevaReservaVisita
		habilitarPantalla();
		controlador.opcionRegistrarReservaVisita();
	}

	public void habilitarPantalla(){

	}

	public void presentarEscuelas(ActionEvent actionEvent) {

	}

	public void presentarEscuelas(List<Escuela> listaEscuelas) {
		cboEscuelas.setItems(FXCollections.observableArrayList(listaEscuelas));
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


	public void presentarTiposDeVisita(){
	}

	// probando empleados

    public void mostrarEmpleados(ActionEvent actionEvent) {
    }

    public void mostrarEmpleados(List<Empleado> listaEmpleados) {
		cboEmpleados.setItems(FXCollections.observableArrayList(listaEmpleados));
	}


	public void mostrarExpo(MouseEvent actionEvent) {
	}

	public void mostrarExpo(List<Exposicion> listaExposiciones) {
		txtExpo.setText(listaExposiciones.toString());
	}

	public void tomarSeleccionChkBox(ActionEvent actionEvent) {
	}
}//end com.ppai.reservavisita.PantallaNuevaReservaVisita