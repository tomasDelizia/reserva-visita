package negocio;


/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:31 am
 */
public class Cargo {

	private String nombre;
	private String descripcion;

	public Cargo(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Cargo{" +
				"nombre='" + nombre + '\'' +
				", descripcion='" + descripcion + '\'' +
				'}';
	}

	public boolean esGuia() {
		return this.nombre.equals("Guía");
	}
}//end Cargo