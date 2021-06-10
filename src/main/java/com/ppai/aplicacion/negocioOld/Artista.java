package com.ppai.aplicacion.negocioOld;


/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 10:42:22 am
 */
public class Artista {

	private String antecedentes;
	private String apellido;
	private String mail;
	private String nombre;
	private String pseudonimo;
	private String sexo;
	private int telefono;

	public Artista(String antecedentes, String apellido, String mail, String nombre, String pseudonimo, String sexo, int telefono) {
		this.antecedentes = antecedentes;
		this.apellido = apellido;
		this.mail = mail;
		this.nombre = nombre;
		this.pseudonimo = pseudonimo;
		this.sexo = sexo;
		this.telefono = telefono;
	}

	public String getAntecedentes() {
		return antecedentes;
	}

	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPseudonimo() {
		return pseudonimo;
	}

	public void setPseudonimo(String pseudonimo) {
		this.pseudonimo = pseudonimo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Artista{" +
				"antecedentes=" + antecedentes +
				", apellido='" + apellido + '\'' +
				", mail='" + mail + '\'' +
				", nombre='" + nombre + '\'' +
				", pseudonimo='" + pseudonimo + '\'' +
				", sexo='" + sexo + '\'' +
				", telefono=" + telefono +
				'}';
	}
}//end Artista