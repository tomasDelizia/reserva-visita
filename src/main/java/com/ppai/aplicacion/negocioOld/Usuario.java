package com.ppai.aplicacion.negocioOld;


import com.ppai.aplicacion.negocio.Empleado;

import java.time.LocalDate;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 7:58:58 pm
 */
public class Usuario {

	private LocalDate caducidad;
	private String  contrasena;
	private String nombre;
	private Empleado empleado;

	public Usuario(LocalDate caducidad, String contrasena, String nombre, Empleado empleado) {
		this.caducidad = caducidad;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.empleado = empleado;
	}

	public LocalDate getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(LocalDate caducidad) {
		this.caducidad = caducidad;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"caducidad=" + caducidad +
				", contrasena='" + contrasena + '\'' +
				", nombre='" + nombre + '\'' +
				", empleado=" + empleado +
				'}';
	}
}//end Usuario