package com.ppai.aplicacion.negocio.empleado.servicio;

import com.ppai.aplicacion.negocio.empleado.modelo.Empleado;
import java.util.List;

public interface EmpleadoServicio {
    List<Empleado> listarEmpleados();

    Empleado encontrarEmpleadoPorId(int idEmpleado);
}
