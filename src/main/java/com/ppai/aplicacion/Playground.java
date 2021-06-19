package com.ppai.aplicacion;

import com.ppai.aplicacion.negocio.*;
import com.ppai.aplicacion.repo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javassist.LoaderClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Playground implements CommandLineRunner {

    @Autowired
    private SedeRepo sedeRepo;

    @Autowired
    private EmpleadoRepo empleadoRepo;

    @Autowired
    private ReservaVisitaRepo reservaVisitaRepo;

    @Autowired
    private AsignacionGuiaRepo asignacionGuiaRepo;

    public static void main(String[] args) {
        SpringApplication.run(Playground.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        List<Escuela> escuelas = escuelaRepo.findAll();


//        List<Sesion> sesiones = sesionRepo.findAll();
//
//
//        for (Escuela e:
//             escuelas) {
//            System.out.println(e + "\n");
//        }
//
//        for (Sede s:
//             sedes) {
//            System.out.println(s + "\n");
//        }
//
//        for (Sesion s:
//                sesiones) {
//            System.out.println(s + "\n");
//        }


//
//        for (Empleado e:
//             empleados) {
//            System.out.println(e + "\n");
//        }


//        for (ReservaVisita rv:
//             reservaVisitaList) {
//            System.out.println(rv + "\n");
//        }
//

//        for (AsignacionGuia ag:
//                asignacionGuias) {
//            System.out.println(ag + "\n");
//        }

//        LocalTime start = LocalTime.of(1, 20, 25, 1024);
//        LocalTime end = LocalTime.of(3, 22, 27, 1544);
//
//        System.out.println(Duration.between(start, end).toString());


//        List<Exposicion> exposicionesTempYVig = miSede.buscarExposicionesTemporalesYVigentes();
//        System.out.println(exposicionesTempYVig);
//        LocalTime duracion = miSede.calcularDuracionEstimadaVisitaPorExposicion(exposicionesTempYVig);
//        System.out.println(duracion);

//        List<Empleado> guiasDisponibles = miSede.
//                buscarGuiasDisponiblesPorHorarioDeReserva(LocalDateTime.now(), empleados, asignacionGuias);
//        System.out.println(guiasDisponibles);

        List<Sede> sedes = sedeRepo.findAll();
        List<ReservaVisita> reservas = reservaVisitaRepo.findAll();
        List<Empleado> empleados = empleadoRepo.findAll();
        List<AsignacionGuia> asignacionGuias = asignacionGuiaRepo.findAll();

        Sede miSede = sedes.get(5);
        System.out.println(miSede);
        //List<Empleado> guiasDisp = new ArrayList<>();
        Empleado miEmpleado = empleados.get(5);
        System.out.println(miEmpleado);
        System.out.println(miEmpleado.esTuSede(miSede));

    }
}
