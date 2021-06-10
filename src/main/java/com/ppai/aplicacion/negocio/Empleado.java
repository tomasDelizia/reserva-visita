package com.ppai.aplicacion.negocio;

import com.ppai.aplicacion.negocioOld.DiaSemana;
import com.ppai.aplicacion.negocioOld.HorarioEmpleado;
import com.ppai.aplicacion.negocioOld.Sede;

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

    @Enumerated(EnumType.ORDINAL)
    private Sexo sexo;

    @Basic
    @Column(name = "codigo_validacion")
    private String codigoValidacion;


    public Empleado() {}

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }


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


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }


    public String getCalleNombre() {
        return calleNombre;
    }

    public void setCalleNombre(String calleNombre) {
        this.calleNombre = calleNombre;
    }


    public Integer getCalleNumero() {
        return calleNumero;
    }

    public void setCalleNumero(Integer calleNumero) {
        this.calleNumero = calleNumero;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getCodigoValidacion() {
        return codigoValidacion;
    }

    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }


    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", sexo='" + sexo + '\'' +
                '}';
    }

    public enum Sexo {MASCULINO, FEMENINO}
//
//    public boolean esGuia(){
//        return this.cargo.esGuia();
//    }
//
//
//
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
}
