package pruebas;


import com.ppai.aplicacion.negocio.Escuela;
import com.ppai.aplicacion.repo.EscuelaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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