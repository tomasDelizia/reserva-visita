package com.ppai.aplicacion.modulos.inicio.presentacion;

import com.ppai.aplicacion.modulos.inicio.controlador.ControladorPrincipal;
import com.ppai.aplicacion.modulos.visita_programada.presentacion.PantallaReservaVisita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PantallaPrincipal extends Pantalla {
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