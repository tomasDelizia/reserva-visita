package com.ppai.aplicacion.interfaz;

import com.ppai.aplicacion.config.StageManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class PantallaBase implements Initializable {
    protected StageManager stageManager;

    @Lazy
    @Autowired
    public void setStageManager(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    /**
     * Método para inicializar una tabla con sus columnas y datos.
     * @param tabla la tabla a inicializar.
     * @param datos los datos de la tabla a mostrar.
     * @param titulos un arreglo con los nombres de las columnas.
     */
    public void cargarTabla(TableView<String[]> tabla, String[][] datos, String[] titulos) {
        // Se cargan las columnas.
        tabla.getColumns().clear();
        cargarColumnas(tabla, titulos);
        // Se obtienen los datos a cargar en la tabla.
        ObservableList<String[]> listaObservableDatos = FXCollections.observableArrayList(datos);
        tabla.getItems().clear();
        tabla.getItems().addAll(listaObservableDatos);
    }

    /**
     * Método para cargar las columnas de una tabla
     * @param tabla la tabla a inicializar.
     * @param titulos los títulos de las columnas.
     */
    public void cargarColumnas(TableView<String[]> tabla, String[] titulos) {
        // Mientras haya títulos, se cargan a la tabla.
        for (int i = 0; i < titulos.length; i++) {
            TableColumn<String[], String> columna = new TableColumn<>(titulos[i]);
            final int numeroColumna = i;
            columna.setCellValueFactory(p -> new SimpleStringProperty((p.getValue()[numeroColumna])));
            columna.prefWidthProperty().bind(tabla.widthProperty().divide(titulos.length));
            columna.setResizable(false);
            tabla.getColumns().add(columna);
        }
        // Seteo de la primera columna ("Id") como no visible.
        tabla.getColumns().get(0).setVisible(false);
    }

    /**
     * Método para cargar un ComboBox con una lista de datos en formato texto.
     * @param combo el ComboBox a cargar.
     * @param lista la lista de datos en formato de arreglo de cadenas de texto.
     */
    public void cargarComboBox(ComboBox<String> combo, String[] lista) {
        // Si el combo ya está cargado, no se hace nada.
        if (!combo.getItems().isEmpty())
            return;
        combo.setItems(FXCollections.observableArrayList(lista));
    }
}//end PantallaBase
