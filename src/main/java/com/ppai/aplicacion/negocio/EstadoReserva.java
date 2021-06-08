package com.ppai.aplicacion.negocio;


/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:45 am
 */
public class EstadoReserva {

	private String nombre, descripcion;

	public EstadoReserva(String nombre, String descripcion) {
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
		return "EstadoReserva{" +
				"nombre='" + nombre + '\'' +
				", descripcion='" + descripcion + '\'' +
				'}';
	}

	public boolean esPendienteDeConfirmacion(){
		return nombre.equals("Pendiente de Confirmación");
	}

}//end EstadoReserva