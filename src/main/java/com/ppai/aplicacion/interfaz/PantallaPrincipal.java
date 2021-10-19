package com.ppai.aplicacion.interfaz;

import com.ppai.aplicacion.controlador.ControladorPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PantallaPrincipal extends PantallaBase {
    private PantallaReservaVisita pantallaReservaVisita;
    private ControladorPrincipal controladorPrincipal;


    @Autowired
    public void setPantallaReservaVisita(PantallaReservaVisita pantallaReservaVisita) {
        this.pantallaReservaVisita = pantallaReservaVisita;
    }

    @Autowired
    public void setControladorPrincipal(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
    }

    public void habilitarPantalla() {
        stageManager.switchScene(FxmlView.MAIN);
        stageManager.maximize();
    }

    public void opcionNuevaReservaVisita() {
        pantallaReservaVisita.opcionNuevaReservaVisita();
    }

    public void salir() {
        controladorPrincipal.cerrarSesion();
        stageManager.switchScene(FxmlView.LOGIN);
    }
}//end PantallaPrincipal