package com.ppai.aplicacion.negocio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:30 am
 */
public class Empleado {

	private int dni;
	private String cuit, nombre, apellido, mail;
	private Cargo cargo;
	private Sede sedeDondeTrabaja;
	private LocalDate fechaNacimiento, fechaIngreso;
	private String calleNombre;
	private int calleNumero;
	private String telefono, sexo;
	private int codigoValidacion;
	private List<HorarioEmpleado> horarioEmpleado;

	public Empleado(int dni, String cuit, String nombre, String apellido, String mail, Cargo cargo,
					Sede sedeDondeTrabaja, LocalDate fechaNacimiento, LocalDate fechaIngreso,
					String calleNombre, int calleNumero, String telefono, String sexo, int codigoValidacion,
					List<HorarioEmpleado> horarioEmpleado) {
		this.dni = dni;
		this.cuit = cuit;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.cargo = cargo;
		this.sedeDondeTrabaja = sedeDondeTrabaja;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngreso = fechaIngreso;
		this.calleNombre = calleNombre;
		this.calleNumero = calleNumero;
		this.telefono = telefono;
		this.sexo = sexo;
		this.codigoValidacion = codigoValidacion;
		this.horarioEmpleado = horarioEmpleado;
	}

	public int getDni() {
		return dni;
	}

	public String getCuit() {
		return cuit;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getMail() {
		return mail;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public Sede getSedeDondeTrabaja() {
		return sedeDondeTrabaja;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public String getCalleNombre() {
		return calleNombre;
	}

	public int getCalleNumero() {
		return calleNumero;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getSexo() {
		return sexo;
	}

	public int getCodigoValidacion() {
		return codigoValidacion;
	}

	public boolean esGuia(){
		return this.cargo.esGuia();
	}



	public void tieneAsignacionParaDiaYHora(){

	}

	public void trabajaDentroDeDiaYHorario(LocalDate fechaYHora){ //me cansé xd
		int dia = fechaYHora.getDayOfWeek().getValue();
//		LocalTime hora = LocalTime.of(fechaYHora.to)
//		for (HorarioEmpleado horarioEmpleado:
//			 horarioEmpleado) {
//			if (fechaYHora.isAfter() && fechaYHora.isBefore())
//		}

	}

	public boolean esTuSede(Sede sede) {
		return sede == sedeDondeTrabaja;
	}

	public List <DiaSemana> getDiasCuandoTrabaja() {
		List <DiaSemana> diasCuandoTrabaja = new ArrayList<>();
		for (HorarioEmpleado horarioEmpleado:
			 horarioEmpleado) {
			List <DiaSemana> diasSemana = horarioEmpleado.getDiaSemana();
			diasCuandoTrabaja.addAll(diasSemana);
		}
		return diasCuandoTrabaja;
	}
}//end Empleado