package com.ppai.aplicacion.interfaz;

import com.ppai.aplicacion.controlador.ControladorReservaVisita;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Clase de interfaz que se encarga de la lógica de presentación para el CU Registrar Reserva de Visita Guiada.
 */
@Controller
public class PantallaReservaVisita extends PantallaBase {
	private ControladorReservaVisita controladorReservaVisita;

	@FXML
	private ComboBox<String> cboEscuelas, cboSedes, cboTiposVisita;

	@FXML
	private TextField txtCantidadVisitantes, txtHoraVisita, txtMinutoVisita, txtDuracionEstimada;

	@FXML
	private Label lblCantidadValida, lblAvisoTipoDeVisita, lblErrorHora, lblCantidadGuias, lblErrorLimiteSedeSuperado,
			lblReservaRegistrada, lblErrorGuiasExcedidos;

	@FXML
	private TableView<String[]> tablaDatosExposiciones, tablaDatosGuias;

	@FXML
	private Button btnConfirmar, btnCancelar;

	@FXML
	private DatePicker dateFechaVisita;

	@FXML
	private TimeSpinner timeSpinnerHoraVisita;



	@Autowired
	public void setControladorReservaVisita(ControladorReservaVisita controladorReservaVisita) {
		this.controladorReservaVisita = controladorReservaVisita;
	}

	/**
	 * Método que permite al usuario cancelar el CU en cualquier momento al tocar el botón "Cancelar".
	 */
	public void cancelar() {
		stageManager.switchScene(FxmlView.MAIN);
		stageManager.maximize();
	}

	/**
	 * Método que inicia el caso de uso, una vez autenticado el usuario.
	 */
	public void opcionNuevaReservaVisita(){
		habilitarPantalla();
		controladorReservaVisita.opcionRegistrarReservaVisita();
	}

	/**
	 * Método para habilitar la pantalla de Registar Reserva de Visita.
	 */
	public void habilitarPantalla(){
		stageManager.switchScene(FxmlView.RESERVA_VISITA);
	}

	/**
	 * Método que recibe una lista de escuelas y las muestra por pantalla.
	 * @param escuelas la lista con los nombres de las escuelas a mostrar.
	 */
	public void presentarEscuelas(String[] escuelas) {
		cargarComboBox(cboEscuelas, escuelas);
	}

	/**
	 * Método que habilita la selección de una escuela para el usuario.
	 */
	public void solicitarSeleccionEscuela(){
		cboEscuelas.setDisable(false);
	}

	/**
	 * Método que toma la selección de una escuela.
	 */
	public void tomarSeleccionEscuela(){
		controladorReservaVisita.escuelaSeleccionada(cboEscuelas.getValue());
	}

	/**
	 * Método que habilita para el usuario el ingreso de la cantidad de visitantes.
	 */
	public void solicitarCantidadVisitantes(){
		if (txtCantidadVisitantes.isDisabled())
			txtCantidadVisitantes.setDisable(false);
	}

	/**
	 * Método que toma la cantidad de visitantes y verifica que sea una cantidad correcta.
	 */
	public void tomarCantidadVisitantes(){
		try {
			int cantidadVisitantes = Integer.parseInt(txtCantidadVisitantes.getText());
			lblCantidadValida.setVisible(cantidadVisitantes < 1);
			// Si la cantidad es correcta, se procede con el caso de uso.
			if (cantidadVisitantes > 0)
				controladorReservaVisita.cantidadDeVisitantesIngresados(cantidadVisitantes);
		} catch (NumberFormatException e) {
			txtCantidadVisitantes.setText("");
		}
	}

	/**
	 * Método que recibe por parámetro una lista de sedes y las muestra al usuario para su selección.
	 * @param sedes la lista con los nombres de las sedes a mostrar.
	 */
	public void presentarSedes(String[] sedes){
		cargarComboBox(cboSedes, sedes);
	}

	/**
	 * Método que habilita la selección de una sede por parte del usuario.
	 */
	public void solicitarSeleccionSede(){
		if (cboSedes.isDisabled())
			cboSedes.setDisable(false);
	}

	/**
	 * Método que toma la selección de una sede por parte del usuario.
	 */
	public void tomarSede(){
		controladorReservaVisita.sedeSeleccionada(cboSedes.getValue());
	}

	/**
	 * Método que recibe por parámetro una lista de tipos de visita y las muestra al usuario para su selección.
	 * @param tiposVisita la lista con los tipos de visita a mostrar.
	 */
	public void presentarTiposVisita(String[] tiposVisita) {
		cargarComboBox(cboTiposVisita, tiposVisita);
	}

	/**
	 * Método que habilita la selección de un tipo de visita.
	 */
	public void solicitarSeleccionTipoVisita(){
		if (cboTiposVisita.isDisabled())
			cboTiposVisita.setDisable(false);
	}

	/**
	 * Método que toma el tipo de visita seleccionado y se lo pasa al objeto controlador.
	 */
	public void tomarTipoVisita(){
		if (lblAvisoTipoDeVisita.isVisible())
			lblAvisoTipoDeVisita.setVisible(false);
		controladorReservaVisita.tipoVisitaSeleccionado(cboTiposVisita.getValue());
	}

	/**
	 * Método que informa que se cambie el tipo de visita seleccionado a "Por Exposición".
	 */
	public void informarSeleccionTipoVisitaPorExposicion() {
		lblAvisoTipoDeVisita.setVisible(true);
	}

	/**
	 * Método que recibe una lista de exposiciones y las muestra por pantalla.
	 * @param exposiciones las exposiciones con sus datos generales a mostrar para la selección del usuario.
	 */
	public void presentarExposicionesTemporalesYVigentes(String[][] exposiciones) {
		String[] titulosColumnas = {"Id", "Exposición", "Públicos Destino", "Hora apertura", "Hora cierre"};
		cargarTabla(tablaDatosExposiciones, exposiciones, titulosColumnas);
		tomarSeleccionExposicion();
	}

	/**
	 * Método que habilita la selección de las exposiciones.
	 */
	public void solicitarSeleccionExposiciones(){
		if (tablaDatosExposiciones.isDisabled())
			tablaDatosExposiciones.setDisable(false);
	}

	/**
	 * Método que toma la selección de una exposición por parte del usuario y se la pasa al controlador.
	 */
	public void tomarSeleccionExposicion() {
		TableColumn<String[], CheckBox> seleccion = new TableColumn<>("Selección");
		seleccion.prefWidthProperty().bind(tablaDatosExposiciones.widthProperty()
				.divide(tablaDatosExposiciones.getColumns().size()));
		seleccion.setResizable(false);
		seleccion.setCellValueFactory(
				exposicionBooleanCellDataFeatures -> {
					String[] datosExpo = exposicionBooleanCellDataFeatures.getValue();
					CheckBox checkBox = new CheckBox();
					checkBox.selectedProperty().setValue(false);
					checkBox.selectedProperty().addListener((observableValue, valorAnterior, valorNuevo) -> {
						// Toma la selección de una exposición.
						if (valorNuevo)
							controladorReservaVisita.exposicionSeleccionada(Integer.parseInt(datosExpo[0]));
						// Toma la deselección de una exposición.
						if (!valorNuevo)
							controladorReservaVisita.exposicionDeseleccionada(Integer.parseInt(datosExpo[0]));
					});
					return new SimpleObjectProperty<>(checkBox);
				});
		tablaDatosExposiciones.getColumns().add(seleccion);
	}

	/**
	 * Método que habilita el ingreso de una fecha y hora para la reserva.
	 */
	public void solicitarFechaYHoraReserva() {
		dateFechaVisita.setDisable(false);
		txtHoraVisita.setDisable(false);
		txtMinutoVisita.setDisable(false);
	}

	/**
	 * Método que deshabilita el ingreso de una fecha y hora para la reserva.
	 */
	public void deshabilitarSeleccionFechaYHoraReserva() {
		dateFechaVisita.setDisable(true);
		txtHoraVisita.setDisable(true);
		txtMinutoVisita.setDisable(true);
	}

	/**
	 * Método que toma la fecha y hora ingresados y verifica que sean válidos.
	 */
	public void tomarFechaYHoraReserva() {
		int horaVisita = Integer.parseInt(txtHoraVisita.getText());
		int minutoVisita = Integer.parseInt(txtMinutoVisita.getText());
		LocalDate fechaIngresada = dateFechaVisita.getValue();
		// Si no es una fecha y hora válida, se avisa con un mensaje.
		if (horaVisita > 23 || minutoVisita > 59 || horaVisita < 8 || minutoVisita < 0 ||
				fechaIngresada.isBefore(LocalDate.now()))
			informarErrorFechaYHora();
		// Si es válida, se continúa con el caso de uso.
		else {
			if (lblErrorHora.isVisible())
				lblErrorHora.setVisible(false);
			LocalTime horaIngresada = LocalTime.of(horaVisita, minutoVisita);
			controladorReservaVisita.fechaYHoraReservaIngresados(LocalDateTime.of(fechaIngresada, horaIngresada));
		}
	}

	/**
	 * Método que informa error de un ingreso de fecha y hora no válidos.
	 */
	public void informarErrorFechaYHora() {
		if (!lblErrorHora.isVisible())
			lblErrorHora.setVisible(true);
	}

	/**
	 * Método que muestra la duración estimada de la exposición por pantalla.
	 * @param duracionEstimada la duración estima de la exposición a mostrar.
	 */
	public void presentarDuracionEstimada(String duracionEstimada) {
		txtDuracionEstimada.setDisable(false);
		txtDuracionEstimada.setText(duracionEstimada);
	}

	/**
	 * Método que informa al usuario que se superó el límite de visitantes de la sede para ese fecha y hora,
	 * para la duración de la exposición.
	 */
	public void informarLimiteVisitantesSuperado() {
		lblErrorLimiteSedeSuperado.setVisible(true);
	}

	/**
	 * Método que presenta los guías disponibles por pantalla para su selección.
	 * @param datosGuiasDisponibles la lista con los nombres y apellidos de los guías disponibles para seleccionar.
	 * @param cantidadGuiasNecesarios la cantidad de guías necesarios a seleccionar.
	 */
	public void presentarGuiasDisponibles(String[][] datosGuiasDisponibles, int cantidadGuiasNecesarios){
		if (lblErrorLimiteSedeSuperado.isVisible())
			lblErrorLimiteSedeSuperado.setVisible(false);
		tablaDatosGuias.getItems().clear();
		// Inicializamos columnas.
		String[] titulos = {"Id", "Nombre", "Apellido"};
		cargarTabla(tablaDatosGuias, datosGuiasDisponibles, titulos);
		tomarSeleccionGuiaDisponible();
		lblCantidadGuias.setText(String.valueOf(cantidadGuiasNecesarios));
	}

	/**
	 * Método que habilita la selección de guías disponibles.
	 */
	public void solicitarSeleccionGuiasDisponibles(){
		tablaDatosGuias.setDisable(false);
	}

	/**
	 * Método que toma la selección o deselección de un guía por parte del usuario y se lo pasa al controlador.
	 */
	public void tomarSeleccionGuiaDisponible(){
		TableColumn<String[], CheckBox> seleccion = new TableColumn<>("Selección");
		seleccion.setCellValueFactory(
				guiaBooleanCellDataFeatures -> {
					String[] datosGuia = guiaBooleanCellDataFeatures.getValue();
					CheckBox checkBox = new CheckBox();
					checkBox.selectedProperty().setValue(false);
					checkBox.selectedProperty().addListener((observableValue, valorAnterior, valorNuevo) -> {
						int cantGuiasASeleccionar = Integer.parseInt(lblCantidadGuias.getText());
						// Toma la selección de un guía.
						if (valorNuevo) {
							// Actualizamos la cantidad hasta que no se llegue a la cantidad requerida.
							if (cantGuiasASeleccionar > 0) {
								ocultarGuiasExcedidos();
								ocultarConfirmacionReserva();
								cantGuiasASeleccionar --;
								lblCantidadGuias.setText(Integer.toString(cantGuiasASeleccionar));
								controladorReservaVisita
										.guiaDisponibleSeleccionado(Integer.parseInt(datosGuia[0]));
							}
							// Se informa un error cuando se seleccionan más guías de los requeridos.
							else if (cantGuiasASeleccionar == 0) {
								informarGuiasExcedidos();
								ocultarConfirmacionReserva();
							}
						}

						// Toma la deselección de un guía.
						if (!valorNuevo) {
							// Actualizamos la cantidad hasta que no se llegue a la cantidad requerida.
							if (cantGuiasASeleccionar >= 0) {
								ocultarConfirmacionReserva();
								cantGuiasASeleccionar ++;
								lblCantidadGuias.setText(Integer.toString(cantGuiasASeleccionar));
								controladorReservaVisita
										.guiaDisponibleDeseleccionado(Integer.parseInt(datosGuia[0]));
							}
						}
					});
					return new SimpleObjectProperty<>(checkBox);
				});
		tablaDatosGuias.getColumns().add(seleccion);
	}

	/**
	 * Método que habilita el botón de confirmación de la reserva.
	 */
	public void solicitarConfirmacionReserva() {
		if (btnConfirmar.isDisabled())
			btnConfirmar.setDisable(false);
	}

	/**
	 * Método que oculta el botón de confirmación de la reserva.
	 */
	public void ocultarConfirmacionReserva() {
		if (!btnConfirmar.isDisabled())
			btnConfirmar.setDisable(true);
	}

	/**
	 * Método que informa que se seleccionaron más guías de los necesarios.
	 */
	public void informarGuiasExcedidos() {
		if (!lblErrorGuiasExcedidos.isVisible())
			lblErrorGuiasExcedidos.setVisible(true);
	}

	/**
	 * Método que quita el mensaje que informa que se seleccionaron más guías de los necesarios.
	 */
	public void ocultarGuiasExcedidos() {
		if (lblErrorGuiasExcedidos.isVisible())
			lblErrorGuiasExcedidos.setVisible(false);
	}

	/**
	 * Método que toma la selección de la confirmación por parte del usuario.
	 */
	public void tomarConfirmacionReserva() {
		controladorReservaVisita.confirmacionReservaSeleccionada();
	}

	/**
	 * Método que informa que la reserva ha sido registrada con éxito, finalizando el caso de uso.
	 */
	public void informarReservaRegistrada() {
		btnConfirmar.setDisable(true);
		btnCancelar.setDisable(true);
		lblReservaRegistrada.setVisible(true);
	}
}//end PantallaReservaVisita