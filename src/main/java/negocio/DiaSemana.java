package negocio;


/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:32 am
 */
public class DiaSemana {

	private String nombre;

	public DiaSemana(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "DiaSemana{" +
				"nombre=" + nombre +
				'}';
	}
}//end DiaSemana