package interfaz;

import controlador.ControladorNuevaReservaVisita;
import negocio.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Obra obra1;
        LocalDate ld = LocalDate.now();
        LocalDateTime ldt = LocalDateTime.now();
        LocalTime lt = LocalTime.now();
        DetalleExposicion deta;
        Archivo archivo;
        EstiloObra estilo;
        TecnicaObra tecnica;
        TematicaObra tematica;
        Artista artista;
        Scanner teclado = new Scanner(System.in);
        Exposicion expo1;
        TipoExposicion tipoExpo;
        Empleado e1;
        PublicoDestino publi;
        Escuela escuela;
        ControladorNuevaReservaVisita controlador = new ControladorNuevaReservaVisita();


        LocalDate fecha = LocalDate.of(2021, 5, 23);

        Escuela escuela1 = new Escuela("El Cole", "elcole@gmail.com","Calle 20",450,
                "1452368",  "4654654");
        Cargo cargo = new Cargo("cargo1", "messi");
        DiaSemana domingo = new DiaSemana("Domingo");
        List<DiaSemana> listaDias = new ArrayList<>();
        listaDias.add(domingo);

        HorarioEmpleado horarioEmp = new HorarioEmpleado(lt, lt, listaDias);
        HorarioSede horarioSede = new HorarioSede(lt, lt, listaDias);
        List<HorarioSede> horarioSedeArray = new ArrayList<>();
        horarioSedeArray.add(horarioSede);

        List<HorarioEmpleado> he = new ArrayList<>();
        he.add(horarioEmp);
        Sede sede1 = new Sede(150, 100, "Sede 1", horarioSedeArray,
                "calle 1", 150);
        Empleado emp1 = new Empleado("Messi", 150, 15201, 201, ld, ld,
                "liomessi@gmail.com", "Leo", "onvre", 123, cargo, "calle2",
                15, he, sede1);

        archivo = new Archivo("obra1", "C:/obra1xd");
        estilo = new EstiloObra("al olieo", "al olieao");
        tecnica = new TecnicaObra("tecnica1", "tecnica1");
        tematica = new TematicaObra("tematica1");
        artista = new Artista("un tipazo", "Fernandez", "alferdez@gmail.com", "Dylan",
                "el dylan", "perro", 12346789);
        tipoExpo = new TipoExposicion("tipo1", "tipo1");

        publi = new PublicoDestino("APTO PARA TODES","APT");

        obra1 = new Obra(80, 50, 123, "desc1", 20, 5,
                ld, ld, ld, "Mauro", 90, 150.60, archivo, estilo, tecnica, tematica,
                artista, emp1);
        List<PublicoDestino> publiArray = new ArrayList<>();
        publiArray.add(publi);

        deta = new DetalleExposicion(454, obra1);
        DetalleExposicion deta1 = new DetalleExposicion(454, obra1);
        List<DetalleExposicion> detaArray = new ArrayList<>();
        detaArray.add(deta); detaArray.add(deta1);

        expo1 = new Exposicion(fecha, null, ld, ld, lt, lt, "La mejor expo!!", tipoExpo,
                emp1, publiArray, detaArray);
        expo1.addDetalleExposicion(deta);
        expo1.addDetalleExposicion(deta1);
        expo1.addPublicoDestino(publi);

        System.out.println(expo1.toString());

        System.out.println(emp1.esGuia());

        System.out.println(escuela1.getEscuela());
        System.out.println(escuela1);

        System.out.println(ld.compareTo(ld));

        System.out.println(expo1.esVigente());

        System.out.println(expo1.getNombresPublicoDestino());

        System.out.println(expo1.calcularDuracionExposicion());

        controlador.buscarEscuelas();
        System.out.println(controlador.getEscuelas());

        LocalTime t0 = LocalTime.now();
        controlador.buscarEstadoPendienteDeConfirmacion();
        System.out.println(controlador.getEstadoPendiente());
        LocalTime t1 = LocalTime.now();
        System.out.println(t0);
        System.out.println(t1);

        LocalTime t2 = LocalTime.now();
        controlador.buscarEstadoPendienteDeConfirmacionAlt();
        System.out.println(controlador.getEstadoPendiente());
        LocalTime t3 = LocalTime.now();
        System.out.println(t2);
        System.out.println(t3);

        controlador.buscarTiposDeVisita();
        System.out.println(controlador.getTiposDeVisita());

    }


}