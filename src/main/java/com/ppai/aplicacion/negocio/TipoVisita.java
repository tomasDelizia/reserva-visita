package com.ppai.aplicacion.negocio;


/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:25 am
 */
public class TipoVisita {

	private String nombre;

	public TipoVisita(String nombre) {
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
		return "TipoVisita{" +
				"nombre=" + nombre +
				'}';
	}

	public boolean esCompleta() {
		return nombre.equals("Completa");
	}

	public boolean esPorExposicion() {
		return nombre.equals("Por Exposición");
	}

}//end TipoVisita