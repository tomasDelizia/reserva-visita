package com.ppai.aplicacion.interfaz;

import com.ppai.aplicacion.config.StageManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
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
    public void inicializarTabla(TableView<String[]> tabla, String[][] datos, String[] titulos) {
        ObservableList<String[]> listaObservableDatos = FXCollections.observableArrayList(datos);
        tabla.setItems(listaObservableDatos);
        for (int i = 0; i < titulos.length; i++) {
            TableColumn<String[], String> columna = new TableColumn<>(titulos[i]);
            final int numeroColumna = i;
            columna.setCellValueFactory(p -> new SimpleStringProperty((p.getValue()[numeroColumna])));
            tabla.getColumns().add(columna);
        }
    }
}//end PantallaBase
