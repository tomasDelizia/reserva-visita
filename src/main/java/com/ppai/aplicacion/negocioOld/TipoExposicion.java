package com.ppai.aplicacion.negocioOld;


/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:39 am
 */
public class TipoExposicion {

	private String descripcion;
	private String nombre;

	public TipoExposicion(String descripcion, String nombre) {
		this.descripcion = descripcion;
		this.nombre = nombre;
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
		return "TipoExposicion{" +
				"descripcion=" + descripcion +
				", nombre=" + nombre +
				'}';
	}

	public void crear(){

	}

	public void esPermanente(){

	}

	public boolean esTemporal(){
		return this.nombre.equals("Temporal");
	}

	public void getTipoExposicion(){

	}
}//end TipoExposicion