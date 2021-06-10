package com.ppai.aplicacion.negocioOld;


/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 10:42:26 am
 */
public class TecnicaObra {

	private String descripcion;
	private String nombre;

public TecnicaObra(String descripcion, String nombre) {
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
		return "TecnicaObra{" +
				"descripcion='" + descripcion + '\'' +
				", nombre='" + nombre + '\'' +
				'}';
	}
}//end TecnicaObra