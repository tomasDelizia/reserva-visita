package com.ppai.aplicacion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Playground implements CommandLineRunner {
//    private ReservaVisitaServicio reservaVisitaServicio;
//    private EscuelaServicio escuelaServicio;
//    private EmpleadoServicio empleadoServicio;
//    private SedeServicio sedeServicio;
//
//    @Autowired
//    public Playground(ReservaVisitaServicio reservaVisitaServicio,
//                      EscuelaServicio escuelaServicio,
//                      EmpleadoServicio empleadoServicio,
//                      SedeServicio sedeServicio) {
//        this.reservaVisitaServicio = reservaVisitaServicio;
//        this.escuelaServicio = escuelaServicio;
//        this.empleadoServicio = empleadoServicio;
//        this.sedeServicio = sedeServicio;
//    }


    public static void main(String[] args) {
        SpringApplication.run(Playground.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        PendienteDeConfirmacion pendiente = new PendienteDeConfirmacion();
//        ReservaVisita reserva = new ReservaVisita();
//        reserva.setNumeroReserva(100);
//        reserva.setCantidadAlumnos(20);
//        reserva.setFechaYHoraReserva(LocalDateTime.now());
//        reserva.setFechaYHoraCreacion(LocalDateTime.now());
//        reserva.setDuracionEstimada(LocalTime.now());
//        Escuela escuela = escuelaServicio.encontrarPorNombre("Colegio Escuti");
//        Empleado empleado = empleadoServicio.encontrarEmpleadoPorId(1);
//        Sede sede = sedeServicio.encontrarPorNombre("Sede Cordoba");
//        reserva.setSede(sede);
//        reserva.setEmpleadoCreo(empleado);
//        reserva.setEscuela(escuela);
//        pendiente.crearReservaVisita(reserva);
//        System.out.println(reserva);
//        System.out.println(reserva.getEstadoActual());
//        System.out.println(pendiente);
//        System.out.println(new PendienteDeConfirmacion());
//        reservaVisitaServicio.guardarReservaVisita(reserva);
        //System.out.println(estadoPendienteRepo.findAll());
    }
}
