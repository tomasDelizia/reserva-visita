package com.ppai.aplicacion.servicio.impl;

import com.ppai.aplicacion.negocio.Empleado;
import com.ppai.aplicacion.repo.EmpleadoRepo;
import com.ppai.aplicacion.servicio.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpleadoServicioImpl implements EmpleadoServicio {
    private final EmpleadoRepo empleadoRepo;

    @Autowired
    public EmpleadoServicioImpl(EmpleadoRepo empleadoRepo) {
        this.empleadoRepo = empleadoRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> listarEmpleados() {
        return empleadoRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado encontrarEmpleadoPorNombreYApellido(String nombreEmpleado, String apellidoEmpleado) {
        return empleadoRepo.findByNombreAndApellido(nombreEmpleado, apellidoEmpleado);
    }
}
