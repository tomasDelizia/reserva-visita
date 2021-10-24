package com.ppai.aplicacion.negocio.empleado.modelo;

import com.ppai.aplicacion.negocio.enums.Sexo;
import com.ppai.aplicacion.negocio.sede.modelo.Sede;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa las entidades persistentes Empleados.
 */
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

    @Basic
    @Column(name = "codigo_validacion")
    private String codigoValidacion;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "HORARIOS_DE_EMPLEADOS",
            joinColumns = @JoinColumn(name = "id_empleado"),
            inverseJoinColumns = @JoinColumn(name = "id_horario"))
    private final List<HorarioEmpleado> horarioEmpleado = new ArrayList<>();

    /**
     * Método que devuelve el id del empleado.
     * @return el id del empleado.
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * Método que devuelve el nombre del empleado.
     * @return el nombre del empleado como cadena de texto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que devuelve el apellido del empleado.
     * @return el apellido del empleado como cadena de texto.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Método que dice si el cargo del empleado corresponde a un "Guía".
     * @return verdadero si el empleado es "Guía", y falso en cualquier otro caso.
     */
    public boolean esGuia(){
        return this.cargo.esGuia();
    }

    /**
     * Método que dice si el cargo del empleado corresponde a un "Responsable de Visitas".
     * @return verdadero si el empleado es "Responsable de Visitas", y falso en cualquier otro caso.
     */
    public boolean esResponsableDeVisitas() {return this.cargo.esResponsableDeVisitas();}

    /**
     * Método que dice si la sede pasada por parámetro corresponde a la sede donde trabaja el empleado.
     * @param unaSede la sede a saber si es donde trabaja el empleado.
     * @return verdadero si la sede pasada es donde trabaja el empleado, y falso en cualquier otro caso.
     */
    public boolean esTuSede(Sede unaSede) {
        return unaSede.getNombre().equals(sedeDondeTrabaja.getNombre());
    }

    /**
     * Método que nos dice si el empleado trabaja en el día y hora pasada por parámetro.
     * @param fechaYHora la fecha y la hora cuando se desea saber si el empleado trabaja.
     * @return verdadero si al menos uno de los horarios de trabajo está dentro del día y la hora seleccionados,
     * y falso en cualquier otro caso.
     */
    public boolean trabajaDentroDeDiaYHorario(LocalDateTime fechaYHora) {
        for (HorarioEmpleado horario:
             horarioEmpleado) {
            if (horario.estaDentroDeDiaYHorario(fechaYHora))
                return true;
        }
        return false;
    }

    /**
     * Método que dice si una asignación de guía pasada por parámetro corresponde al guía.
     * @param asignacionGuia la asignación de la que se desea saber si corresponde al guía.
     * @return verdadero si la asignación corresponde al guía, y falso en cualquier otro caso.
     */
    public boolean esTuAsignacion(AsignacionGuia asignacionGuia) {
        return asignacionGuia.esTuGuia(this);
    }

    /**
     * Método que nos dice si el guía tiene asignaciones en el día y hora pasados por parámetro.
     * @param fechaYHora la fecha y hora en que se desea saber si el guía tiene asignaciones.
     * @param asignaciones la lista de todas las asignaciones existentes.
     * @return verdadero si el guía tiene asignaciones para esa fecha y hora, y falso en cualquier otro caso.
     */
    public boolean tieneAsignacionParaDiaYHora(LocalDateTime fechaYHora, List<AsignacionGuia> asignaciones){
        for (AsignacionGuia asignacionGuia:
                asignaciones) {
            if (this.esTuAsignacion(asignacionGuia) && asignacionGuia.esEnDiaYHora(fechaYHora))
                return true;
        }
        return false;
    }
}//end Empleado
