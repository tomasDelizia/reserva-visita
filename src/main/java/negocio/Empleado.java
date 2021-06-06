package negocio;
import java.time.LocalDate;
import java.util.List;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:30 am
 */
public class Empleado {

	private String apellido;
	private int codigoValidacion;
	private int cuit;
	private int dni;
	private LocalDate fechaIngreso;
	private LocalDate fechaNacimiento;
	private String mail;
	private String nombre;
	private String sexo;
	private int telefono;
	private Cargo cargo;
	private String calleNombre;
	private int calleNumero;
	private List<HorarioEmpleado> horarioEmpleado;
	private Sede sedeDondeTrabaja;

	public Empleado(String apellido, int codigoValidacion, int cuit, int dni, LocalDate fechaIngreso,
					LocalDate fechaNacimiento, String mail, String nombre, String sexo, int telefono,
					Cargo cargo, String calleNombre, int calleNumero, List<HorarioEmpleado> horarioEmpleado,
					Sede sedeDondeTrabaja) {
		this.apellido = apellido;
		this.codigoValidacion = codigoValidacion;
		this.cuit = cuit;
		this.dni = dni;
		this.fechaIngreso = fechaIngreso;
		this.fechaNacimiento = fechaNacimiento;
		this.mail = mail;
		this.nombre = nombre;
		this.sexo = sexo;
		this.telefono = telefono;
		this.cargo = cargo;
		this.calleNombre = calleNombre;
		this.calleNumero = calleNumero;
		this.horarioEmpleado = horarioEmpleado;
		this.sedeDondeTrabaja = sedeDondeTrabaja;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getCodigoValidacion() {
		return codigoValidacion;
	}

	public void setCodigoValidacion(int codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}

	public int getCuit() {
		return cuit;
	}

	public void setCuit(int cuit) {
		this.cuit = cuit;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getCalleNombre() {
		return calleNombre;
	}

	public void setCalleNombre(String calleNombre) {
		this.calleNombre = calleNombre;
	}

	public int getCalleNumero() {
		return calleNumero;
	}

	public void setCalleNumero(int calleNumero) {
		this.calleNumero = calleNumero;
	}

	public List<HorarioEmpleado> getHorarioEmpleado() {
		return horarioEmpleado;
	}

	public void addHorario(HorarioEmpleado he) {
		horarioEmpleado.add(he);
	}

	public Sede getSedeDondeTrabaja() {
		return sedeDondeTrabaja;
	}

	public void setSedeDondeTrabaja(Sede sedeDondeTrabaja) {
		this.sedeDondeTrabaja = sedeDondeTrabaja;
	}

	@Override
	public String toString() {
		return "Empleado{" +
				"apellido='" + apellido + '\'' +
				", codigoValidacion=" + codigoValidacion +
				", cuit=" + cuit +
				", dni=" + dni +
				", fechaIngreso=" + fechaIngreso +
				", fechaNacimiento=" + fechaNacimiento +
				", mail='" + mail + '\'' +
				", nombre='" + nombre + '\'' +
				", sexo='" + sexo + '\'' +
				", telefono=" + telefono +
				", cargo=" + cargo +
				", calleNombre='" + calleNombre + '\'' +
				", calleNumero=" + calleNumero +
				", horarioEmpleado=" + horarioEmpleado +
				", sedeDondeTrabaja=" + sedeDondeTrabaja +
				'}';
	}

	public boolean esGuia(){
		return this.cargo.esGuia();
	}

	public void tieneAsignacionParaDiaYHora(){

	}

	public void trabajaDentroDeDiaYHorario(){

	}
}//end Empleado