package com.ppai.aplicacion.interfaz;


import com.ppai.aplicacion.controlador.ControladorReservaVisita;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


/**
 * Clase de interfaz que se encarga de la lógica de presentación para el CU Registrar Reserva de Visita Guiada.
 */
@Component
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
	private TableView<ExposicionVisible> tablaExposiciones;

	@FXML
	private TableColumn<ExposicionVisible, String> colExposicion, colPublicoDestino, colHoraApertura, colHoraCierre;

	@FXML
	private TableColumn<ExposicionVisible, CheckBox> colSeleccionExposicion;

	@FXML
	private Button btnConfirmar, btnCancelar;

	@FXML
	private DatePicker dateFechaVisita;

	@FXML
	private TableView<GuiaVisible> tablaGuias;

	@FXML
	private TableColumn<GuiaVisible, String> colNombreGuia, colApellidoGuia;

	@FXML
	private TableColumn<GuiaVisible, CheckBox> colSeleccionGuia;


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
	 * @param listaEscuelas la lista con los nombres de las escuelas a mostrar.
	 */
	public void presentarEscuelas(List<String> listaEscuelas) {
		cboEscuelas.setItems(FXCollections.observableArrayList(listaEscuelas));
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
		txtCantidadVisitantes.setDisable(false);
	}

	/**
	 * Método que toma la cantidad de visitantes y verifica que sea una cantidad correcta.
	 */
	public void tomarCantidadVisitantes(){
		int visitantes = Integer.parseInt(txtCantidadVisitantes.getText());
		lblCantidadValida.setVisible(visitantes < 1);
		// Si la cantidad es correcta, se procede con el caso de uso.
		if (visitantes > 0) {
			controladorReservaVisita.cantidadDeVisitantesIngresados(visitantes);
		}
	}

	/**
	 * Método que recibe por parámetro una lista de sedes y las muestra al usuario para su selección.
	 * @param listaSedes la lista con los nombres de las sedes a mostrar.
	 */
	public void presentarSedes(List<String> listaSedes){
		if (cboSedes.isDisabled())
			cboSedes.setItems(FXCollections.observableArrayList(listaSedes));
	}

	/**
	 * Método que habilita la selección de una sede por parte del usuario.
	 */
	public void solicitarSeleccionSede(){
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
	 * @param listaTiposVisita la lista con los tipos de visita a mostrar.
	 */
	public void presentarTiposVisita(List<String> listaTiposVisita) {
		if (cboTiposVisita.isDisabled())
			cboTiposVisita.setItems(FXCollections.observableArrayList(listaTiposVisita));
	}

	/**
	 * Método que habilita la selección de un tipo de visita.
	 */
	public void solicitarSeleccionTipoVisita(){
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
	 * @param listaExposiciones las exposiciones con sus datos generales a mostrar para la selección del usuario.
	 */
	public void presentarExposicionesTemporalesYVigentesNuevo(List<ExposicionVisible> listaExposiciones) {
		ObservableList<ExposicionVisible> listaObservableExposiciones =
				FXCollections.observableArrayList(listaExposiciones);
		tablaExposiciones.setItems(listaObservableExposiciones);
		colExposicion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colPublicoDestino.setCellValueFactory(new PropertyValueFactory<>("publicoDestino"));
		colHoraApertura.setCellValueFactory(new PropertyValueFactory<>("horaApertura"));
		colHoraCierre.setCellValueFactory(new PropertyValueFactory<>("horaCierre"));
		colSeleccionExposicion.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<ExposicionVisible, CheckBox>, ObservableValue<CheckBox>>() {
					@Override
					public ObservableValue<CheckBox> call(
							TableColumn.CellDataFeatures<ExposicionVisible, CheckBox> exposicionBooleanCellDataFeatures) {
						ExposicionVisible expo = exposicionBooleanCellDataFeatures.getValue();
						CheckBox checkBox = new CheckBox();
						checkBox.selectedProperty().setValue(false);
						checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
							// Reemplaza al método tomarSeleccionExposicion() y tomarDeseleccionExposicion():
							@Override
							public void changed(ObservableValue<? extends Boolean> observableValue,
												Boolean valorAnterior, Boolean valorNuevo) {
								// Toma la selección de una exposición:
								if (valorNuevo)
									controladorReservaVisita.exposicionSeleccionada(expo.getNombre());
								// Toma la deselección de una exposición:
								if (!valorNuevo)
									controladorReservaVisita.exposicionDeseleccionada(expo.getNombre());
							}
						});
						return new SimpleObjectProperty<CheckBox>(checkBox);
					}
				});
	}

	/**
	 * Método que habilita la selección de las exposiciones.
	 */
	public void solicitarSeleccionExposiciones(){
		tablaExposiciones.setDisable(false);
	}

	public void tomarSeleccionExposicion(){
		// Método que toma la exposición seleccionada, la guarda y luego la remueve de la tabla.
//		Exposicion exposicionSeleccionada = tablaExposiciones.getSelectionModel().getSelectedItem();
//		controladorReservaVisita.exposicionSeleccionada(exposicionSeleccionada);
//		tablaExposiciones.getItems().removeAll(exposicionSeleccionada);
	}

	/**
	 * Método que habilita el ingreso de una fecha y hora para la reserva.
	 */
	public void solicitarFechaYHoraReserva(){
		dateFechaVisita.setDisable(false);
		txtHoraVisita.setDisable(false);
		txtMinutoVisita.setDisable(false);
	}

	/**
	 * Método que deshabilita el ingreso de una fecha y hora para la reserva.
	 */
	public void deshabilitarSeleccionFechaYHoraReserva(){
		dateFechaVisita.setDisable(true);
		txtHoraVisita.setDisable(true);
		txtMinutoVisita.setDisable(true);
	}

	/**
	 * Método que toma la fecha y hora ingresados y verifica que sean válidos.
	 */
	public void tomarFechaYHoraReserva(){
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
	public void presentarDuracionEstimada(LocalTime duracionEstimada) {
		txtDuracionEstimada.setDisable(false);
		txtDuracionEstimada.setText(duracionEstimada.toString());
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
	 * @param guiasDisponibles la lista con los nombres y apellidos de los guías disponibles para seleccionar.
	 * @param cantidadGuiasNecesarios la cantidad de guías necesarios a seleccionar.
	 */
	public void presentarGuiasDisponibles(List<GuiaVisible> guiasDisponibles, Integer cantidadGuiasNecesarios){
		if (lblErrorLimiteSedeSuperado.isVisible())
			lblErrorLimiteSedeSuperado.setVisible(false);
		ObservableList<GuiaVisible> listaObservableGuiasDisponibles =
				FXCollections.observableArrayList(guiasDisponibles);
		tablaGuias.setItems(listaObservableGuiasDisponibles);
		colNombreGuia.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colApellidoGuia.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		lblCantidadGuias.setText(cantidadGuiasNecesarios.toString());
		colSeleccionGuia.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<GuiaVisible, CheckBox>, ObservableValue<CheckBox>>() {
					@Override
					public ObservableValue<CheckBox> call(
							TableColumn.CellDataFeatures<GuiaVisible, CheckBox> guiaBooleanCellDataFeatures) {
						GuiaVisible guia = guiaBooleanCellDataFeatures.getValue();
						CheckBox checkBox = new CheckBox();
						checkBox.selectedProperty().setValue(false);
						checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
							// Reemplaza al método tomarSeleccionGuia() y tomarDeseleccionGuia():
							@Override
							public void changed(ObservableValue<? extends Boolean> observableValue,
												Boolean valorAnterior, Boolean valorNuevo) {
								// Toma la selección de un guía:
								if (valorNuevo) {
									// Actualizamos la cantidad hasta que no se llegue a la cantidad requerida.
									if (Integer.parseInt(lblCantidadGuias.getText()) > 0) {
										if (!lblErrorGuiasExcedidos.isVisible())
											lblErrorGuiasExcedidos.setVisible(false);
										if (!btnConfirmar.isDisabled())
											btnConfirmar.setDisable(true);
										int cantidadGuias = Integer.parseInt(lblCantidadGuias.getText()) - 1;
										lblCantidadGuias.setText(Integer.toString(cantidadGuias));
										controladorReservaVisita
												.guiaDisponibleSeleccionado(guia.getNombre(), guia.getApellido());
									}
									// Se informa un error cuando se seleccionan más guías de los requeridos:
									else if (Integer.parseInt(lblCantidadGuias.getText()) == 0) {
										informarGuiasExcedidos();
										int cantidadGuias = Integer.parseInt(lblCantidadGuias.getText()) - 1;
										lblCantidadGuias.setText(Integer.toString(cantidadGuias));
									}
								}
								// Toma la deselección de un guía:
								if (!valorNuevo) {
									// Actualizamos la cantidad hasta que no se llegue a la cantidad requerida.
									if (Integer.parseInt(lblCantidadGuias.getText()) >= 0) {
										int cantidadGuias = Integer.parseInt(lblCantidadGuias.getText()) + 1;
										lblCantidadGuias.setText(Integer.toString(cantidadGuias));
										controladorReservaVisita
												.guiaDisponibleDeseleccionado(guia.getNombre(), guia.getApellido());
									}
									else {
										int cantidadGuias = Integer.parseInt(lblCantidadGuias.getText()) + 1;
										lblCantidadGuias.setText(Integer.toString(cantidadGuias));
										if (cantidadGuias == 0) {
											lblErrorGuiasExcedidos.setVisible(false);
											btnConfirmar.setDisable(false);
										}
									}
								}
							}
						});
						return new SimpleObjectProperty<CheckBox>(checkBox);
					}
				});
	}

	/**
	 * Método que informa que se seleccionaron más guías de los necesarios.
	 */
	public void informarGuiasExcedidos() {
		if (!lblErrorGuiasExcedidos.isVisible()) {
			lblErrorGuiasExcedidos.setVisible(true);
			btnConfirmar.setDisable(true);
		}
	}

	/**
	 * Método que habilita la selección de guías disponibles.
	 */
	public void solicitarSeleccionGuiasDisponibles(){
		tablaGuias.setDisable(false);
	}

	public void tomarSeleccionGuiaDisponible(){
		// Método que toma la selección de guías necesarios.
		// Actualizamos la cantidad de guías seleccionados hasta que no se llegue a la cantidad requerida.
//		if (Integer.parseInt(lblCantidadGuias.getText()) > 0) {
//			int cantidadGuias = Integer.parseInt(lblCantidadGuias.getText()) - 1;
//			lblCantidadGuias.setText(Integer.toString(cantidadGuias));
//		}
//		// Se deshabilita la selección de guías cuando se llega a la cantidad requerida.
//		if (Integer.parseInt(lblCantidadGuias.getText()) == 0) {
//			btnSeleccionarGuia.setDisable(true);
//		Empleado guiaSeleccionado = tablaGuias.getSelectionModel().getSelectedItem();
//		tablaGuias.getItems().removeAll(guiaSeleccionado);
//		controladorReservaVisita.guiaDisponibleSeleccionado(guiaSeleccionado);
	}

	/**
	 * Método que habilita el botón de confirmación de la reserva.
	 */
	public void solicitarConfirmacionReserva() {
		if (lblErrorGuiasExcedidos.isVisible())
			lblErrorGuiasExcedidos.setVisible(false);
		if (btnConfirmar.isDisabled())
			btnConfirmar.setDisable(false);
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