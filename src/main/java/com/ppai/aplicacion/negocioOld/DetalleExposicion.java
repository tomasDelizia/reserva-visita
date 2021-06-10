package com.ppai.aplicacion.negocioOld;


/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:44:08 am
 */
public class DetalleExposicion {

	private int lugarAsignado;
	private Obra obra;

	public DetalleExposicion(int lugarAsignado, Obra obra) {
		this.lugarAsignado = lugarAsignado;
		this.obra = obra;
	}

	public int getLugarAsignado() {
		return lugarAsignado;
	}

	public void setLugarAsignado(int lugarAsignado) {
		this.lugarAsignado = lugarAsignado;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public void getDuracionExtendida(){

	}

	public void getDuracionResumida(){

	}

	@Override
	public String toString() {
		return "DetalleExposicion{" +
				"lugarAsignado=" + lugarAsignado + "\n" +
				", obra=" + obra +
				'}';
	}
}//end DetalleExposicion