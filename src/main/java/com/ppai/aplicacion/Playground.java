package com.ppai.aplicacion;

import com.ppai.aplicacion.negocio.Empleado;
import com.ppai.aplicacion.negocio.Escuela;
import com.ppai.aplicacion.negocio.Sede;
import com.ppai.aplicacion.negocio.Sesion;
import com.ppai.aplicacion.repo.EmpleadoRepo;
import com.ppai.aplicacion.repo.EscuelaRepo;
import com.ppai.aplicacion.repo.SedeRepo;
import com.ppai.aplicacion.repo.SesionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    public static void main(String[] args) {
        SpringApplication.run(Playground.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        List<Escuela> escuelas = escuelaRepo.findAll();
//        List<Sede> sedes = sedeRepo.findAll();
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

        List<Empleado> empleados = empleadoRepo.findAll();

        for (Empleado e:
             empleados) {
            System.out.println(e + "\n");
        }
    }
}
