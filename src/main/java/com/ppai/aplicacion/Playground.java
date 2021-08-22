package com.ppai.aplicacion;

import com.ppai.aplicacion.negocio.*;
import com.ppai.aplicacion.servicio.SedeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Playground implements CommandLineRunner {

    @Autowired
    private SedeServicio sedeServicio;

    public static void main(String[] args) {
        SpringApplication.run(Playground.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Sede unaSede = sedeServicio.encontrarPorNombre("Louvre");
        System.out.println(unaSede.buscarExposicionesTemporalesYVigentes());
    }
}
