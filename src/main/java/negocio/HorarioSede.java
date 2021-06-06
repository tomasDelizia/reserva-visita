package negocio;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tomid
 * @version 1.0
 * @created 22-May-2021 9:42:35 am
 */
public class HorarioSede {

	private LocalTime horarioApertura;
	private LocalTime horarioCierre;
	private List<DiaSemana> diaSemana;

	public HorarioSede(LocalTime horarioApertura, LocalTime horarioCierre, List<DiaSemana> diaSemana) {
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
		this.diaSemana = diaSemana;
	}

	public LocalTime getHorarioApertura() {
		return horarioApertura;
	}

	public void setHorarioApertura(LocalTime horarioApertura) {
		this.horarioApertura = horarioApertura;
	}

	public LocalTime getHorarioCierre() {
		return horarioCierre;
	}

	public void setHorarioCierre(LocalTime horarioCierre) {
		this.horarioCierre = horarioCierre;
	}

	public List<DiaSemana> getDiaSemana() {
		return diaSemana; // despues el controlador hacemos el getNombre
	}

	public ArrayList<String> getNombresDiaSemana() {
		ArrayList<String> nombresDias = new ArrayList<String>();
		for (DiaSemana ds:
				diaSemana){
			nombresDias.add(ds.getNombre());
		}
		return nombresDias;
	}

	@Override
	public String toString() {
		return "HorarioSede{" +
				"horarioApertura=" + horarioApertura +
				", horarioCierre=" + horarioCierre +
				", diaSemana=" + diaSemana +
				'}';
	}

	public void addDiaSemana(DiaSemana dia) {
		diaSemana.add(dia);
	}

}//end HorarioSede