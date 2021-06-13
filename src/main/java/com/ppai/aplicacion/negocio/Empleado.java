package com.ppai.aplicacion.negocio;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EMPLEADOS", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class Empleado {
    @Id
    @Column(name = "id_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleado;

    @Basic
    @Column(name = "dni")
    private int dni;

    @Basic
    @Column(name = "cuit")
    private String cuit;

    @Basic
    @Column(name = "nombre")
    private String nombre;

    @Basic
    @Column(name = "apellido")
    private String apellido;

    @Basic
    @Column(name = "mail")
    private String mail;

    @OneToOne
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    private Cargo cargo;

    @OneToOne
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede")
    private Sede sedeDondeTrabaja;

    @Basic
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Basic
    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Basic
    @Column(name = "calle_nombre")
    private String calleNombre;

    @Basic
    @Column(name = "calle_numero")
    private Integer calleNumero;

    @Basic
    @Column(name = "telefono")
    private String telefono;

    @Basic
    @Column(name = "id_sexo")
    @Enumerated(EnumType.ORDINAL)
    private Sexo sexo;

    public enum Sexo {MASCULINO, FEMENINO}

    @Basic
    @Column(name = "codigo_validacion")
    private String codigoValidacion;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="idEmpleado")
    private List<HorarioEmpleado> horarioEmpleado;


    public Empleado() {}

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cargo=" + cargo +
                ", sedeDondeTrabaja=" + sedeDondeTrabaja.getNombre() +
                ", sexo=" + sexo +
                ", horarioEmpleado=" + horarioEmpleado +
                '}';
    }

    public boolean esGuia(){
        return this.cargo.esGuia();
    }

//    public void tieneAsignacionParaDiaYHora(){
//
//    }
//
//    public void trabajaDentroDeDiaYHorario(LocalDate fechaYHora){ //me cansé xd
//        int dia = fechaYHora.getDayOfWeek().getValue();
////		LocalTime hora = LocalTime.of(fechaYHora.to)
////		for (HorarioEmpleado horarioEmpleado:
////			 horarioEmpleado) {
////			if (fechaYHora.isAfter() && fechaYHora.isBefore())
////		}
//
//    }
//
//    public boolean esTuSede(Sede sede) {
//        return sede == sedeDondeTrabaja;
//    }
//
//    public List<DiaSemana> getDiasCuandoTrabaja() {
//        List <DiaSemana> diasCuandoTrabaja = new ArrayList<>();
//        for (HorarioEmpleado horarioEmpleado:
//                horarioEmpleado) {
//            List <DiaSemana> diasSemana = horarioEmpleado.getDiaSemana();
//            diasCuandoTrabaja.addAll(diasSemana);
//        }
//        return diasCuandoTrabaja;
//    }
}//end Empleado
