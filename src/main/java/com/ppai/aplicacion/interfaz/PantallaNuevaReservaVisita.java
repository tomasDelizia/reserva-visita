package com.ppai.aplicacion.interfaz;

import com.ppai.aplicacion.controlador.ControladorNuevaReservaVisita;
import com.ppai.aplicacion.negocio.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Selection;
import javax.security.auth.callback.Callback;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PantallaNuevaReservaVisita implements Initializable {


	private ControladorNuevaReservaVisita controlador;

	@FXML
	private TextArea cajaPruebas;

	@FXML
	private ComboBox<Escuela> cboEscuelas;

	@FXML
	private TextField txtCantidadVisitantes;

	@FXML
	private Label lblCantidadValida;

	@FXML
	private ComboBox<Sede> cboSedes;

	@FXML
	private RadioButton radioBtnCompleta;

	@FXML
	private RadioButton radioBtnPorExposicion;

	@FXML
	private Label lblAvisoTipoDeVisita;

	@FXML
	private TableView<Exposicion> tablaExposiciones;

	@FXML
	private TableColumn<Exposicion, String> colExposicion;

	@FXML
	private TableColumn<Exposicion, List<PublicoDestino>> colPublicoDestino;

	@FXML
	private TableColumn<Exposicion, LocalTime> colHoraApertura;

	@FXML
	private TableColumn<Exposicion, LocalTime> colHoraCierre;

	@FXML
	private DatePicker dateFechaVisita;

	@FXML
	private Button btnSeleccionarExposicion;


	@Autowired
	public void setControlador(ControladorNuevaReservaVisita controlador) {
		this.controlador = controlador;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		opcionNuevaReservaVisita();
	}

	public void cancelar(ActionEvent actionEvent) {
		Platform.exit();
	}

	public void opcionNuevaReservaVisita(){
		habilitarPantalla();
		controlador.opcionRegistrarReservaVisita();
	}

	public void habilitarPantalla(){}


	public void presentarEscuelas(List<Escuela> listaEscuelas) {
		cboEscuelas.setItems(FXCollections.observableArrayList(listaEscuelas));
	}

	public void solicitarSeleccionEscuela(){}

	public void tomarSeleccionEscuela(ActionEvent actionEvent){
		controlador.escuelaSeleccionada(cboEscuelas.getValue());
	}

	public void solicitarCantidadVisitantes(){
		txtCantidadVisitantes.setDisable(false);
	}

	public void tomarCantidadVisitantes(ActionEvent actionEvent){
		int visitantes = Integer.parseInt(txtCantidadVisitantes.getText());
		lblCantidadValida.setVisible(visitantes < 1);
		if (visitantes > 0) {
			controlador.cantidadDeVisitantesIngresados(visitantes);
		}
	}

	public void presentarSedes(List<Sede> listaSedes){
		cboSedes.setItems(FXCollections.observableArrayList(listaSedes));
	}

	public void solicitarSeleccionSede(){
		cboSedes.setDisable(false);
	}

	public void tomarSede(ActionEvent actionEvent){
		Sede sede = cboSedes.getValue();
		controlador.sedeSeleccionada(sede);
	}

	public void presentarTiposDeVisita(){

	}

	public void solicitarSeleccionTipoVisita(){
		radioBtnCompleta.setDisable(false);
		radioBtnPorExposicion.setDisable(false);
	}

	public void tomarTipoVisita(ActionEvent actionEvent){
		if (radioBtnPorExposicion.isSelected())
			lblAvisoTipoDeVisita.setVisible(false);
			controlador.tipoVisitaSeleccionada(radioBtnPorExposicion.getText());
		if (radioBtnCompleta.isSelected())
			lblAvisoTipoDeVisita.setVisible(true);
	}

	public void presentarExposicionesTemporalesYVigentes(List<Exposicion> listaExposiciones){
		ObservableList<Exposicion> listaObservableExposiciones =
				FXCollections.observableArrayList(listaExposiciones);
		tablaExposiciones.setItems(listaObservableExposiciones);
		colExposicion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colPublicoDestino.setCellValueFactory(new PropertyValueFactory<>("publicoDestino"));
		colHoraApertura.setCellValueFactory(new PropertyValueFactory<>("horaApertura"));
		colHoraCierre.setCellValueFactory(new PropertyValueFactory<>("horaCierre"));
	}

	public void solicitarSeleccionExposiciones(){
		btnSeleccionarExposicion.setDisable(false);
		tablaExposiciones.setDisable(false);
	}

	public void tomarSeleccionExposiciones(ActionEvent actionEvent){
//		TableView.TableViewSelectionModel<Exposicion> selectionModel = tablaExposiciones.getSelectionModel();
//		selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
//		List<Exposicion> exposicionSeleccionada = selectionModel.getSelectedItems();
		controlador.addExposicionSeleccionada(tablaExposiciones.getSelectionModel().getSelectedItem());
		tablaExposiciones.getItems().removeAll(tablaExposiciones.getSelectionModel().getSelectedItem());
	}

	public void solicitarFechaYHoraReserva(){
		dateFechaVisita.setDisable(false);
	}

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

}//end PantallaNuevaReservaVisita