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
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PantallaNuevaReservaVisita implements Initializable {

	private ControladorNuevaReservaVisita controlador;

	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField txtContrasena;

	@FXML
	private Label lblBienvenida;

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
		// Método que permite al usuario cancelar el CU en cualquier momento al tocar el botón "Cancelar".
		Platform.exit();
	}

	public void tomarLogin(ActionEvent actionEvent) {
		// Método para tomar el el usuario y contraseña ingresados para luego ser verificados.
		String usuario = txtUsuario.getText();
		String contrasena = txtContrasena.getText();
		controlador.loginIngresado(usuario, contrasena);
	}

	public void informarLoginConExito() {
		// Método que informa un ingreso de sesión con éxito.
		if (lblUsuarioIncorrecto.isVisible())
			lblUsuarioIncorrecto.setVisible(false);
		lblBienvenida.setText("¡Bienvenido, usuario " + txtUsuario.getText() + "!");
		lblBienvenida.setVisible(true);
		opcionNuevaReservaVisita();
	}

	public void informarLoginFallido(){
		// Método que informa de un login fallido.
		lblUsuarioIncorrecto.setVisible(true);
	}

	// MÉTODOS DEL CASO DE USO 92: REGISTRAR RESERVA DE VISITA

	public void opcionNuevaReservaVisita(){
		// Método que inicia el caso de uso, una vez verificado el inicio de sesión.
		controlador.opcionRegistrarReservaVisita();
	}

	//public void habilitarPantalla(){}	// Método para habilitar la pantalla de Registar Reserva de Visita.

	public void presentarEscuelas(List<Escuela> listaEscuelas) {
		// Método que recibe una lista de escuelas y las muestra por pantalla.
		cboEscuelas.setItems(FXCollections.observableArrayList(listaEscuelas));
	}

	public void solicitarSeleccionEscuela(){
		// Método que habilita la selección de una escuela para el usuario.
		cboEscuelas.setDisable(false);
	}

	public void tomarSeleccionEscuela(ActionEvent actionEvent){
		// Método que toma la selección de una escuela.
		controlador.escuelaSeleccionada(cboEscuelas.getValue());
	}

	public void solicitarCantidadVisitantes(){
		// Método que habilita para el usuario el ingreso de la cantidad de visitantes.
		txtCantidadVisitantes.setDisable(false);
	}

	public void tomarCantidadVisitantes(ActionEvent actionEvent){
		// Método que toma la cantidad de visitantes y verifica que sea una cantidad correcta.
		int visitantes = Integer.parseInt(txtCantidadVisitantes.getText());
		lblCantidadValida.setVisible(visitantes < 1);
		// Si la cantidad es correcta, se procede con el caso de uso.
		if (visitantes > 0) {
			controlador.cantidadDeVisitantesIngresados(visitantes);
		}
	}

	public void presentarSedes(List<Sede> listaSedes){
		// Método que recibe por parámetro una lista de sedes y las muestra al usuario para su selección.
		cboSedes.setItems(FXCollections.observableArrayList(listaSedes));
	}

	public void solicitarSeleccionSede(){
		// Método que habilita la selección de una sede por parte del usuario.
		cboSedes.setDisable(false);
	}

	public void tomarSede(ActionEvent actionEvent){
		// Método que toma la selección de una sede por parte del usuario.
		Sede sede = cboSedes.getValue();
		controlador.sedeSeleccionada(sede);
	}

	public void solicitarSeleccionTipoVisita(){
		// Método que habilita la selección de un tipo de visita.
		radioBtnCompleta.setDisable(false);
		radioBtnPorExposicion.setDisable(false);
	}

	public void tomarTipoVisita(ActionEvent actionEvent){
		// Si se selecciona el tipo de visita Por Exposición, se continua con el caso de uso.
		if (radioBtnPorExposicion.isSelected()) {
			if (lblAvisoTipoDeVisita.isVisible())
				lblAvisoTipoDeVisita.setVisible(false);
			controlador.tipoVisitaSeleccionada(radioBtnPorExposicion.getText());
		}
		// Si se selecciona el tipo de visita Completa, se informa que seleccione Por Exposición.
		if (radioBtnCompleta.isSelected())
			lblAvisoTipoDeVisita.setVisible(true);
	}

	public void presentarExposicionesTemporalesYVigentes(List<Exposicion> listaExposiciones){
		// Método que recibe una lista de exposiciones y las muestra por pantalla.
		ObservableList<Exposicion> listaObservableExposiciones =
				FXCollections.observableArrayList(listaExposiciones);
		tablaExposiciones.setItems(listaObservableExposiciones);
		colExposicion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colPublicoDestino.setCellValueFactory(new PropertyValueFactory<>("publicoDestino"));
		colHoraApertura.setCellValueFactory(new PropertyValueFactory<>("horaApertura"));
		colHoraCierre.setCellValueFactory(new PropertyValueFactory<>("horaCierre"));
	}

	public void solicitarSeleccionExposiciones(){
		// Método que habilita la selección de las exposiciones.
		btnSeleccionarExposicion.setDisable(false);
		tablaExposiciones.setDisable(false);
	}

	public void tomarSeleccionExposicion(ActionEvent actionEvent){
		// Método que toma la exposición seleccionada, la guarda y luego la remueve de la tabla.
		Exposicion exposicionSeleccionada = tablaExposiciones.getSelectionModel().getSelectedItem();
		controlador.exposicionSeleccionada(exposicionSeleccionada);
		tablaExposiciones.getItems().removeAll(exposicionSeleccionada);
	}

	public void solicitarFechaYHoraReserva(){
		// Método que habilita el ingreso de una fecha y hora para la reserva.
		dateFechaVisita.setDisable(false);
		txtHoraVisita.setDisable(false);
		txtMinutoVisita.setDisable(false);
	}

	public void tomarFechaYHoraReserva(ActionEvent actionEvent){
		// Método que toma la fecha y hora ingresados y verifica que sean válidos.
		int horaVisita = Integer.parseInt(txtHoraVisita.getText());
		int minutoVisita = Integer.parseInt(txtMinutoVisita.getText());
		// Si no es una fecha y hora válida, se avisa con un mensaje.
		if (horaVisita > 23 || minutoVisita > 59 || horaVisita < 8 || minutoVisita < 0)
			lblErrorHora.setVisible(true);
		// Si es válida, se continúa con el caso de uso.
		else {
			if (lblErrorHora.isVisible())
				lblErrorHora.setVisible(false);
			LocalTime horaIngresada = LocalTime.of(horaVisita, minutoVisita);
			LocalDate fechaIngresada = dateFechaVisita.getValue();
			controlador.fechaYHoraReservaIngresados(LocalDateTime.of(fechaIngresada, horaIngresada));
		}
	}

	public void presentarDuracionEstimada(LocalTime duracionEstimada) {
		// Método que muestra la duración estimada de la exposición por pantalla.
		txtDuracionEstimada.setDisable(false);
		txtDuracionEstimada.setText(duracionEstimada.toString());
	}

	public void informarLimiteVisitantesSuperado() {
		// Método que informa al usuario que se superó el límite de visitantes de la sede
		// para ese fecha y hora, para la duración de la exposición.
		lblErrorLimiteSedeSuperado.setVisible(true);
	}

	public void presentarGuiasDisponibles(List<Empleado> guiasDisponibles, Integer cantidadAsistentes){
		// Método que presenta los guías disponibles por pantalla para su selección.
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
		// Método que habilita la selección de guías disponibles.
		btnSeleccionarGuia.setDisable(false);
		tablaGuias.setDisable(false);
	}

	public void tomarSeleccionGuiaDisponible(ActionEvent actionEvent){
		// Método que toma la selección de guías necesarios.
		// Actualizamos la cantidad de guías seleccionados hasta que no se llegue a la cantidad requerida.
		if (Integer.parseInt(lblCantidadGuias.getText()) > 0) {
			int cantidadGuias = Integer.parseInt(lblCantidadGuias.getText()) - 1;
			lblCantidadGuias.setText(Integer.toString(cantidadGuias));
		}
		// Se deshabilita la selección de guías cuando se llega a la cantidad requerida.
		if (Integer.parseInt(lblCantidadGuias.getText()) == 0) {
			btnSeleccionarGuia.setDisable(true);
		}
		Empleado guiaSeleccionado = tablaGuias.getSelectionModel().getSelectedItem();
		tablaGuias.getItems().removeAll(guiaSeleccionado);
		controlador.guiaDisponibleSeleccionado(guiaSeleccionado);
	}

	public void solicitarConfirmacionReserva() {
		// Método que habilita el botón de confirmación de la reserva.
		btnConfirmar.setDisable(false);
	}

	public void tomarConfirmacionReserva(ActionEvent actionEvent) {
		// Método que toma la selección de la confirmación por parte del usuario.
		btnConfirmar.setDisable(true);
		lblReservaRegistrada.setVisible(true);
		controlador.confirmacionReservaSeleccionada();
	}
}//end PantallaNuevaReservaVisita