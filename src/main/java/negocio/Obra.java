package negocio;

import java.time.LocalDate;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:41 am
 */
public class Obra {

	private int alto;
	private int ancho;
	private int codigoSensor;
	private String descripcion;
	private int duracionExtendida;
	private int duracionResumida;
	private LocalDate fechaCreacion;
	private LocalDate fechaPrimerIngreso;
	private LocalDate fechaRegistracion;
	private String nombreObra;
	private double peso;
	private double valuacion;
	private Archivo archivo;
	private EstiloObra estilo;
	private TecnicaObra tecnica;
	private TematicaObra tematica;
	private Artista artistaQuePinto;
	private Empleado empleadoCreo;

	public Obra(int alto, int ancho, int codigoSensor, String descripcion, int duracionExtendida,
				int duracionResumida, LocalDate fechaCreacion, LocalDate fechaPrimerIngreso,
				LocalDate fechaRegistracion, String nombreObra, double peso, double valuacion, Archivo archivo,
				EstiloObra estilo, TecnicaObra tecnica, TematicaObra tematica, Artista artistaQuePinto,
				Empleado empleadoCreo) {
		this.alto = alto;
		this.ancho = ancho;
		this.codigoSensor = codigoSensor;
		this.descripcion = descripcion;
		this.duracionExtendida = duracionExtendida;
		this.duracionResumida = duracionResumida;
		this.fechaCreacion = fechaCreacion;
		this.fechaPrimerIngreso = fechaPrimerIngreso;
		this.fechaRegistracion = fechaRegistracion;
		this.nombreObra = nombreObra;
		this.peso = peso;
		this.valuacion = valuacion;
		this.archivo = archivo;
		this.estilo = estilo;
		this.tecnica = tecnica;
		this.tematica = tematica;
		this.artistaQuePinto = artistaQuePinto;
		this.empleadoCreo = empleadoCreo;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getCodigoSensor() {
		return codigoSensor;
	}

	public void setCodigoSensor(int codigoSensor) {
		this.codigoSensor = codigoSensor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDuracionExtendida() {
		return duracionExtendida;
	}

	public void setDuracionExtendida(int duracionExtendida) {
		this.duracionExtendida = duracionExtendida;
	}

	public int getDuracionResumida() {
		return duracionResumida;
	}

	public void setDuracionResumida(int duracionResumida) {
		this.duracionResumida = duracionResumida;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaPrimerIngreso() {
		return fechaPrimerIngreso;
	}

	public void setFechaPrimerIngreso(LocalDate fechaPrimerIngreso) {
		this.fechaPrimerIngreso = fechaPrimerIngreso;
	}

	public LocalDate getFechaRegistracion() {
		return fechaRegistracion;
	}

	public void setFechaRegistracion(LocalDate fechaRegistracion) {
		this.fechaRegistracion = fechaRegistracion;
	}

	public String getNombreObra() {
		return nombreObra;
	}

	public void setNombreObra(String nombreObra) {
		this.nombreObra = nombreObra;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getValuacion() {
		return valuacion;
	}

	public void setValuacion(double valuacion) {
		this.valuacion = valuacion;
	}

	public Archivo getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	public EstiloObra getEstilo() {
		return estilo;
	}

	public void setEstilo(EstiloObra estilo) {
		this.estilo = estilo;
	}

	public TecnicaObra getTecnica() {
		return tecnica;
	}

	public void setTecnica(TecnicaObra tecnica) {
		this.tecnica = tecnica;
	}

	public TematicaObra getTematica() {
		return tematica;
	}

	public void setTematica(TematicaObra tematica) {
		this.tematica = tematica;
	}

	public Artista getArtistaQuePinto() {
		return artistaQuePinto;
	}

	public void setArtistaQuePinto(Artista artistaQuePinto) {
		this.artistaQuePinto = artistaQuePinto;
	}


	@Override
	public String toString() {
		return "Obra{" +
				"alto=" + alto +
				", ancho=" + ancho +
				", codigoSensor=" + codigoSensor +
				", descripcion='" + descripcion + '\'' +
				", duracionExtendida=" + duracionExtendida +
				", duracionResumida=" + duracionResumida +
				", fechaCreacion=" + fechaCreacion +
				", fechaPrimerIngreso=" + fechaPrimerIngreso +
				", fechaRegistracion=" + fechaRegistracion +
				", nombreObra='" + nombreObra + '\'' +
				", peso=" + peso +
				", valuacion=" + valuacion +
				", archivo=" + archivo +
				", estilo=" + estilo +
				", tecnica=" + tecnica +
				", tematica=" + tematica +
				", artistaQuePinto=" + artistaQuePinto +
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