package com.ppai.aplicacion;

import com.ppai.aplicacion.negocio.Empleado;
import com.ppai.aplicacion.negocio.Escuela;
import com.ppai.aplicacion.negocio.TipoVisita;
import com.ppai.aplicacion.repo.EmpleadoRepo;
import com.ppai.aplicacion.repo.EscuelaRepo;
import com.ppai.aplicacion.repo.TipoVisitaRepo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class TestPantallaNuevaReservaVisita implements Initializable {


	private TestControladorNuevaReservaVisita controlador;

	@FXML
	public ComboBox<Escuela> cboEscuelas;


	@Autowired
	public void setControlador(TestControladorNuevaReservaVisita controlador) {
		this.controlador = controlador;
	}

	public TestControladorNuevaReservaVisita getControlador() {
		return controlador;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		opcionNuevaReservaVisita();
		habilitarPantalla();
	}


	public void opcionNuevaReservaVisita(){ // Los objetos pantalla y gestor se crean cuando presionamos el botón nuevaReservaVisita
		controlador.opcionRegistrarReserva();
	}


	public void habilitarPantalla(){}


	public void presentarEscuelas() {}


	public void presentarEscuelas(List<Escuela> listaEscuelas) {
		cboEscuelas.setItems(FXCollections.observableArrayList(listaEscuelas));
	}


	public void tomarSeleccionEscuela(){

	}



}//end com.ppai.reservavisita.PantallaNuevaReservaVisita