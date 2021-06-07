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

	public int getValue() {
		int valor = 0;
		switch (nombre) {
			case "Lunes":
				valor = 1;
				break;
			case "Martes":
				valor = 2;
				break;
			case "Miércoles":
				valor = 3;
				break;
			case "Jueves":
				valor = 4;
				break;
			case "Viernes":
				valor = 5;
				break;
			case "Sábado":
				valor = 6;
				break;
			case "Domingo":
				valor = 7;
				break;
		}
		return valor;
	}
}//end DiaSemana