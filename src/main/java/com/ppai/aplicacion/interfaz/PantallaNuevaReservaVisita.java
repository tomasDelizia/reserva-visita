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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PantallaNuevaReservaVisita implements Initializable {

	@FXML
	private TextArea cajaPruebas;

	private ControladorNuevaReservaVisita controlador;

	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField txtContrasena;

	@FXML
	private Label lblBenvenida;

	@FXML
	private Label lblUsuarioIncorrecto;

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
	private Button btnSeleccionarExposicion;

	@FXML
	private DatePicker dateFechaVisita;

	@FXML
	private TextField txtHoraVisita;

	@FXML
	private TextField txtMinutoVisita;

	@FXML
	private Label lblErrorHora;

	@FXML
	private TextField txtDuracionEstimada;

	@FXML
	private TableView<Empleado> tablaGuias;

	@FXML
	private TableColumn<Empleado, String> colNombreGuia;

	@FXML
	private TableColumn<Empleado, String> colApellidoGuia;

	@FXML
	private Label lblCantidadGuias;

	@FXML
	private Button btnSeleccionarGuia;

	@FXML
	private Button btnConfirmar;

	@FXML
	private Label lblErrorLimiteSedeSuperado;

	@FXML
	private Label lblReservaRegistrada;


	@Autowired
	public void setControlador(ControladorNuevaReservaVisita controlador) {
		this.controlador = controlador;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {}

	public void cancelar(ActionEvent actionEvent) {
		Platform.exit();
	}

	public void tomarLogin(ActionEvent actionEvent) {
		String usuario = txtUsuario.getText();
		String contrasena = txtContrasena.getText();
		controlador.loginIngresado(usuario, contrasena);
	}

	public void informarLoginConExito() {
		if (lblUsuarioIncorrecto.isVisible())
			lblUsuarioIncorrecto.setVisible(false);
		lblBenvenida.setVisible(true);
		opcionNuevaReservaVisita();
	}

	public void informarLoginFallido(){
		lblUsuarioIncorrecto.setVisible(true);
	}

	public void opcionNuevaReservaVisita(){
		habilitarPantalla();
		controlador.opcionRegistrarReservaVisita();
	}

	public void habilitarPantalla(){
		cboEscuelas.setDisable(false);
	}

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

	public void tomarSeleccionExposicion(ActionEvent actionEvent){
		controlador.exposicionSeleccionada(tablaExposiciones.getSelectionModel().getSelectedItem());
		tablaExposiciones.getItems().removeAll(tablaExposiciones.getSelectionModel().getSelectedItem());
	}

	public void solicitarFechaYHoraReserva(){
		dateFechaVisita.setDisable(false);
		txtHoraVisita.setDisable(false);
		txtMinutoVisita.setDisable(false);
	}

	public void tomarFechaYHoraReserva(ActionEvent actionEvent){
		int horaVisita = Integer.parseInt(txtHoraVisita.getText());
		int minutoVisita = Integer.parseInt(txtMinutoVisita.getText());
		if (horaVisita > 23 || minutoVisita > 59 || horaVisita < 8 || minutoVisita < 0)
			lblErrorHora.setVisible(true);
		else {
			if (lblErrorHora.isVisible())
				lblErrorHora.setVisible(false);
			LocalTime horaIngresada = LocalTime.of(horaVisita, minutoVisita);
			LocalDate fechaIngresada = dateFechaVisita.getValue();
			controlador.fechaYHoraReservaIngresados(LocalDateTime.of(fechaIngresada, horaIngresada));
		}
	}

	public void presentarDuracionEstimada(LocalTime duracionEstimada) {
		txtDuracionEstimada.setDisable(false);
		txtDuracionEstimada.setText(duracionEstimada.toString());
	}

	public void informarLimiteVisitantesSuperado() {
		lblErrorLimiteSedeSuperado.setVisible(true);
	}

	public void presentarGuiasDisponibles(List<Empleado> guiasDisponibles, Integer cantidadAsistentes){
		if (lblErrorLimiteSedeSuperado.isVisible())
			lblErrorLimiteSedeSuperado.setVisible(false);
		ObservableList<Empleado> listaObservableGuiasDisponibles =
				FXCollections.observableArrayList(guiasDisponibles);
		tablaGuias.setItems(listaObservableGuiasDisponibles);
		colNombreGuia.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colApellidoGuia.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		lblCantidadGuias.setText(cantidadAsistentes.toString());
	}

	public void solicitarSeleccionGuiasDisponibles(){
		btnSeleccionarGuia.setDisable(false);
		tablaGuias.setDisable(false);
	}

	public void tomarSeleccionGuiaDisponible(ActionEvent actionEvent){
		if (Integer.parseInt(lblCantidadGuias.getText()) > 0) {
			int cantidadGuias = Integer.parseInt(lblCantidadGuias.getText()) - 1;
			lblCantidadGuias.setText(Integer.toString(cantidadGuias));
		}
		if (Integer.parseInt(lblCantidadGuias.getText()) == 0) {
			btnSeleccionarGuia.setDisable(true);
		}
		Empleado guiaSeleccionado = tablaGuias.getSelectionModel().getSelectedItem();
		tablaGuias.getItems().removeAll(guiaSeleccionado);
		controlador.guiaDisponibleSeleccionado(guiaSeleccionado);
	}

	public void solicitarConfirmacionReserva() {
		btnConfirmar.setDisable(false);
	}

	public void tomarConfirmacionReserva(ActionEvent actionEvent) {
		btnConfirmar.setDisable(true);
		lblReservaRegistrada.setVisible(true);
		controlador.confirmacionReservaSeleccionada();
	}

	public void mostrarReserva(ReservaVisita nuevaReserva) {
		cajaPruebas.setText(nuevaReserva.toString());
	}
}//end PantallaNuevaReservaVisita