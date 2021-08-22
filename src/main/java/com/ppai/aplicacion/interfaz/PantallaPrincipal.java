package com.ppai.aplicacion.interfaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PantallaPrincipal extends PantallaBase {
    private PantallaReservaVisita pantallaReservaVisita;


    @Autowired
    public void setPantallaReservaVisita(PantallaReservaVisita pantallaReservaVisita) {
        this.pantallaReservaVisita = pantallaReservaVisita;
    }

    public void habilitarPantalla() {
        stageManager.switchScene(FxmlView.MAIN);
        stageManager.maximize();
    }

    public void opcionNuevaReservaVisita() {
        pantallaReservaVisita.opcionNuevaReservaVisita();
    }

    public void salir() {
        stageManager.switchScene(FxmlView.LOGIN);
    }
}//end PantallaPrincipal