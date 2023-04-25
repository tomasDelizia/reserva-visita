package com.ppai.aplicacion.modulos.empleado.servicio;

import com.ppai.aplicacion.modulos.empleado.modelo.Empleado;
import java.util.List;

public interface EmpleadoServicio {
    List<Empleado> listarEmpleados();

    Empleado encontrarEmpleadoPorId(int idEmpleado);
}
