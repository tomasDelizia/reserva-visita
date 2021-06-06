package negocio;


/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 10:42:23 am
 */
public class Archivo {

	private String descripcion;
	private String ubicacion;

	public Archivo(String descripcion, String ubicacion) {
		this.descripcion = descripcion;
		this.ubicacion = ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "Archivo{" +
				"descripcion='" + descripcion + '\'' +
				", ubicacion='" + ubicacion + '\'' +
				'}';
	}
}//end Archivo