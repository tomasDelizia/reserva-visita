package com.ppai.aplicacion.negocio;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SEDES", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class Sede {
    @Id
    @Column(name = "id_sede")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSede;

    @Basic
    @Column(name = "nombre")
    private String nombre;

    @Basic
    @Column(name = "cantidad_maxima_visitantes")
    private int cantidadMaximaVisitantes;

    @Basic
    @Column(name = "cantidad_maxima_por_guia")
    private int cantidadMaximaPorGuia;

    @Basic
    @Column(name = "calle_nombre")
    private String calleNombre;

    @Basic
    @Column(name = "calle_numero")
    private Integer calleNumero;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "EXPOSICIONES_X_SEDES",
            joinColumns = @JoinColumn(name = "id_sede"),
            inverseJoinColumns = @JoinColumn(name = "id_exposicion"))
    private List<Exposicion> exposicion;

//    private List<HorarioSede> horarioSede;

    @Override
    public String toString() {
        return "Sede{" +
                "nombre='" + nombre + '\'' +
                ", cantidadMaximaVisitantes=" + cantidadMaximaVisitantes +
                ", cantidadMaximaPorGuia=" + cantidadMaximaPorGuia +
                ", exposicion=" + exposicion +
                '}';
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadMaximaVisitantes() {
        return cantidadMaximaVisitantes;
    }

    public void setCantidadMaximaVisitantes(int cantidadMaximaVisitantes) {
        this.cantidadMaximaVisitantes = cantidadMaximaVisitantes;
    }

    public int getCantidadMaximaPorGuia() {
        return cantidadMaximaPorGuia;
    }

    public void setCantidadMaximaPorGuia(int cantidadMaximaPorGuia) {
        this.cantidadMaximaPorGuia = cantidadMaximaPorGuia;
    }

    public List<Exposicion> buscarExposicionesTemporalesYVigentes(){
        // Método para encontrar las exposiciones temporales y vigentes de una sede
        // Se inicializa una lista para almacenar las exposiciones deseadas
        List<Exposicion> exposicionesTempYVig = new ArrayList<>();
        // Recorro todas las exposiciones de una sede
        for (Exposicion expo:
                exposicion) {
            // A cada exposición le pregunto si es temporal y vigente. Si lo es, la agrego a la lista
            if (expo.esVigenteYTemporal())
                exposicionesTempYVig.add(expo);
        }
        // Retorno la lista con las exposiciones resultantes de la iteración
        return exposicionesTempYVig;
    }
//
//    public List<DiaSemana> getDiasAtencionSede() {
//        List<DiaSemana> dias = new ArrayList<>();
//        for (HorarioSede horario:
//                horarioSede) {
//            dias.addAll(horario.getDiaSemana());
//        }
//        return dias;
//    }
//
//    public void buscarGuiasDisponiblesPorHorarioReserva(){
//
//    }
//
//    public Duration calcularDuracionEstimadaVisita(List<com.ppai.aplicacion.negocioOld.Exposicion> exposiciones, TipoVisita tipoVisita){
//        Duration duracionVisita = Duration.parse("00:00:00");
//        for (com.ppai.aplicacion.negocioOld.Exposicion expo:
//                exposiciones) {
//            //Duration duracionExpo = expo.calcularDuracionExposicion(tipoVisita);
//            //duracionVisita.plus(duracionExpo);
//        }
//        return duracionVisita;
//    }
//
//    public boolean esTuReserva(ReservaVisita reservaVisita) {
//        // Método que dice si la reserva pasada por parámetro es de esta reserva
//        return reservaVisita.esTuSede(this);
//    }
//
//    public int getCantidadVisitantesParaFechaYHora(LocalDateTime fechaYHora, List<ReservaVisita> reservasVisitas) {
//        int cantVisitantes = 0;
//        for (ReservaVisita reservaVisita:
//                reservasVisitas) {
//            if (reservaVisita.esTuSede(this) && reservaVisita.esEnDiaYHora(fechaYHora))
//                cantVisitantes += reservaVisita.getCantidadAlumnos();
//        }
//        return cantVisitantes;
//    }
//
//    public boolean superaLimiteVisitantesParaFechaYHora(int cantidadVisitantesReserva, LocalDateTime fechaYHora, List<ReservaVisita> reservaVisitas){
//        return cantidadVisitantesReserva + getCantidadVisitantesParaFechaYHora(fechaYHora, reservaVisitas) > capacidadMaxVisitantes;
//    }

//	public List<Empleado> buscarGuiasDisponiblesPorHorarioDeReserva(LocalDateTime fechaYHora, List<Empleado> empleados) {
//
//		for (Empleado empleado:
//			 empleados) {
//			if (empleado.esTuSede(this) && empleado.esGuia())
//
//		}
//	}

}//end Sede
