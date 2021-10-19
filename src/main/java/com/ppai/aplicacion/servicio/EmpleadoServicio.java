package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.negocio.Empleado;
import java.util.List;
import java.util.Optional;

public interface EmpleadoServicio {
    List<Empleado> listarEmpleados();

    Empleado encontrarEmpleadoPorId(int idEmpleado);
}
