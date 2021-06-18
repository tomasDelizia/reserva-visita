package com.ppai.aplicacion.interfaz;

import com.ppai.aplicacion.negocio.Exposicion;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

public class CheckBoxCellFactory implements Callback {
    @Override
    public TableCell call(Object param) {
        CheckBoxTableCell<Exposicion,Boolean> checkBoxCell = new CheckBoxTableCell();
        return checkBoxCell;
    }
}