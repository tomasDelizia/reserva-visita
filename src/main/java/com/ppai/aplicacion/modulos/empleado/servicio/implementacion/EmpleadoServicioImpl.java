package com.ppai.aplicacion.modulos.empleado.servicio.implementacion;

import com.ppai.aplicacion.modulos.empleado.modelo.Empleado;
import com.ppai.aplicacion.modulos.empleado.repositorio.EmpleadoRepositorio;
import com.ppai.aplicacion.modulos.empleado.servicio.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpleadoServicioImpl implements EmpleadoServicio {
    private final EmpleadoRepositorio empleadoRepositorio;

    @Autowired
    public EmpleadoServicioImpl(EmpleadoRepositorio empleadoRepositorio) {
        this.empleadoRepositorio = empleadoRepositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> listarEmpleados() {
        return empleadoRepositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado encontrarEmpleadoPorId(int idEmpleado) {
        return empleadoRepositorio.findById(idEmpleado).orElse(null);
    }
}
