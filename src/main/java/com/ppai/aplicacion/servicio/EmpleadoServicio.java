package com.ppai.aplicacion.servicio;

import com.ppai.aplicacion.negocio.Empleado;
import java.util.List;

public interface EmpleadoServicio {
    List<Empleado> listarEmpleados();

    Empleado encontrarEmpleadoPorNombreYApellido(String nombreEmpleado, String apellidoEmpleado);
}
