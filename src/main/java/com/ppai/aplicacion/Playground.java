package com.ppai.aplicacion;

import com.ppai.aplicacion.negocio.*;
import com.ppai.aplicacion.repo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Playground implements CommandLineRunner {

    @Autowired
    private SedeRepo sedeRepo;

    @Autowired
    private EscuelaRepo escuelaRepo;

    @Autowired
    private SesionRepo sesionRepo;

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
        List<Sede> sedes = sedeRepo.findAll();
//        List<Empleado> empleados = empleadoRepo.findAll();
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

//        List<ReservaVisita> reservaVisitaList = reservaVisitaRepo.findAll();
//        for (ReservaVisita rv:
//             reservaVisitaList) {
//            System.out.println(rv + "\n");
//        }
//
//        List<AsignacionGuia> asignacionGuias = asignacionGuiaRepo.findAll();
//        for (AsignacionGuia ag:
//                asignacionGuias) {
//            System.out.println(ag + "\n");
//        }

        LocalTime start = LocalTime.of(1, 20, 25, 1024);
        LocalTime end = LocalTime.of(3, 22, 27, 1544);

        System.out.println(Duration.between(start, end).toString());

        System.out.println(100/3);

        Sede miSede = sedes.get(1);
        List<Exposicion> exposicionesTempYVig = miSede.buscarExposicionesTemporalesYVigentes();
        System.out.println(miSede);
        List<Exposicion> exposiciones = miSede.getExposicion();
        for (Exposicion e:
             exposiciones) {
            System.out.println(e.toString() + e.esVigente() + e.esTemporal() + e.esVigenteYTemporal());
            System.out.println(e.getFechaFin());
            System.out.println(LocalDate.now());
            System.out.println(e.getFechaFin().compareTo(LocalDate.now()));
            System.out.println(LocalDate.now().compareTo(e.getFechaFin()));
        }
        System.out.println(exposicionesTempYVig);
        System.out.println(FXCollections.observableArrayList(exposicionesTempYVig));

    }
}
