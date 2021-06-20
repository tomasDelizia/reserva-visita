package com.ppai.aplicacion.negocio;



import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @ManyToMany
    @JoinTable(
            name = "HORARIOS_DE_EMPLEADOS",
            joinColumns = @JoinColumn(name = "id_empleado"),
            inverseJoinColumns = @JoinColumn(name = "id_horario"))
    private final List<HorarioEmpleado> horarioEmpleado = new ArrayList<>();



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
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cargo=" + cargo +
                '}';
    }

    public boolean esGuia(){
        return this.cargo.esGuia();
    }

    public boolean esResponsableDeVisitas() {return this.cargo.esResponsableDeVisitas();}

    public boolean esTuSede(Sede unaSede) {
        return unaSede.getNombre().equals(sedeDondeTrabaja.getNombre());
    }

    public boolean trabajaDentroDeDiaYHorario(LocalDateTime fechaYHora) {
        // Método que nos dice si el empleado trabaja en el día y hora pasada por parámetro.
        for (HorarioEmpleado horario:
             horarioEmpleado) {
            // Si al menos uno de los horarios de trabajo está dentreo del día y la hora seleccionados,
            // el método devuelve verdadero.
            if (horario.estaDentroDeDiaYHorario(fechaYHora))
                return true;
        }
        return false;
    }

    public boolean esTuAsignacion(AsignacionGuia asignacionGuia) {
        return asignacionGuia.esTuGuia(this);
    }

    public boolean tieneAsignacionParaDiaYHora(LocalDateTime fechaYHora, List<AsignacionGuia> asignaciones){
        // Método que nos dice si el guía tiene asignaciones en el día y hora pasados por parámetro
        for (AsignacionGuia asignacionGuia:
                asignaciones) {
            if (this.esTuAsignacion(asignacionGuia) && asignacionGuia.esEnDiaYHora(fechaYHora))
                return true;
        }
        return false;
    }

}//end Empleado
