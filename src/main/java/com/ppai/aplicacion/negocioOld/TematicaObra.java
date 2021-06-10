package com.ppai.aplicacion.negocioOld;


/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 10:42:28 am
 */
public class TematicaObra {

	private String nombre;

	public TematicaObra(String nombre) {
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
		return "TematicaObra{" +
				"nombre='" + nombre + '\'' +
				'}';
	}
}//end TematicaObra