package com.ppai.aplicacion;


import com.ppai.aplicacion.negocio.Empleado;
import com.ppai.aplicacion.negocio.Escuela;
import com.ppai.aplicacion.negocio.TipoVisita;
import com.ppai.aplicacion.negocioOld.EstadoReserva;
import com.ppai.aplicacion.negocioOld.Exposicion;
import com.ppai.aplicacion.negocioOld.Sede;
import com.ppai.aplicacion.repo.EscuelaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Supplier;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:37 am
 */
@Component
public class TestControladorNuevaReservaVisita {

	private TestPantallaNuevaReservaVisita pantalla;

	private List<Escuela> escuelas;

	private Escuela escuelaSeleccionada;

	@Autowired
	public EscuelaRepo escuelaRepo;


	@Autowired
	public void setPantalla(TestPantallaNuevaReservaVisita pantalla) {
		this.pantalla = pantalla;
	}

	public void opcionRegistrarReserva() {
		buscarEscuelas();
		pantalla.presentarEscuelas(this.escuelas);
	}

	public void buscarEscuelas() {
		this.escuelas = escuelaRepo.findAll();
	}


}//end ControladorNuevaReservaVisita