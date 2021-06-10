package com.ppai.aplicacion.negocioOld;

import com.ppai.aplicacion.negocio.Empleado;

import java.time.Duration;
import java.time.LocalDate;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:41 am
 */
public class Obra {

	private String nombreObra;
	private double alto, ancho, peso, valuacion;
	private Duration duracionExtendida, duracionResumida;
	private LocalDate fechaCreacion, fechaPrimerIngreso, fechaRegistracion;
	private String descripcion;
	private int codigoSensor;
	private Empleado empleadoCreo;

	public Obra(String nombreObra, double alto, double ancho, double peso, double valuacion,
				Duration duracionExtendida, Duration duracionResumida, LocalDate fechaCreacion,
				LocalDate fechaPrimerIngreso, LocalDate fechaRegistracion, String descripcion,
				int codigoSensor, Empleado empleadoCreo) {
		this.nombreObra = nombreObra;
		this.alto = alto;
		this.ancho = ancho;
		this.peso = peso;
		this.valuacion = valuacion;
		this.duracionExtendida = duracionExtendida;
		this.duracionResumida = duracionResumida;
		this.fechaCreacion = fechaCreacion;
		this.fechaPrimerIngreso = fechaPrimerIngreso;
		this.fechaRegistracion = fechaRegistracion;
		this.descripcion = descripcion;
		this.codigoSensor = codigoSensor;
		this.empleadoCreo = empleadoCreo;
	}

	public Duration getDuracionExtendida() {
		return duracionExtendida;
	}

	public Duration getDuracionResumida() {
		return duracionResumida;
	}

	@Override
	public String toString() {
		return "Obra{" +
				"nombreObra='" + nombreObra + '\'' +
				", alto=" + alto +
				", ancho=" + ancho +
				", peso=" + peso +
				", valuacion=" + valuacion +
				", duracionExtendida=" + duracionExtendida +
				", duracionResumida=" + duracionResumida +
				", fechaCreacion=" + fechaCreacion +
				", fechaPrimerIngreso=" + fechaPrimerIngreso +
				", fechaRegistracion=" + fechaRegistracion +
				", descripcion='" + descripcion + '\'' +
				", codigoSensor=" + codigoSensor +
				", empleadoCreo=" + empleadoCreo +
				'}';
	}

	public void newCambioEstado(){

	}

	public void estaDiponibleParaExposicionEnPeriodo(){

	}

	public void estaEnDeposito(){

	}

	public void estaEnExposicion(){

	}

	public void estaParaRestauracion(){

	}

	public void registrarBaja(){

	}

	public void registrarPendienteAsignacionDeposito(){

	}


}//end Obra