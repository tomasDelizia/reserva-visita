package negocio;


import java.math.BigInteger;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:18 am
 */
public class Escuela {

	private String nombre, mail, calleNombre, telefonoCelular, telefonoFijo;
	private int calleNumero;

	public Escuela(String nombre, String mail, String calleNombre, int calleNumero, String telefonoCelular, String telefonoFijo) {
		this.nombre = nombre;
		this.mail = mail;
		this.calleNombre = calleNombre;
		this.calleNumero = calleNumero;
		this.telefonoCelular = telefonoCelular;
		this.telefonoFijo = telefonoFijo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getMail() {
		return mail;
	}

	public String getCalleNombre() {
		return calleNombre;
	}

	public int getCalleNumero() {
		return calleNumero;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public Escuela getEscuela() {return this;}

	@Override
	public String toString() {
		return "Escuela{" +
				"nombre='" + nombre + '\'' +
				", mail='" + mail + '\'' +
				", calleNombre='" + calleNombre + '\'' +
				", calleNumero=" + calleNumero + '\'' +
				", telefonoCelular='" + telefonoCelular + '\'' +
				", telefonoFijo='" + telefonoFijo +
				"}\n";
	}
}//end Escuela