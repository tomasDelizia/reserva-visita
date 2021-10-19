package com.ppai.aplicacion.servicio.implementacion;

import com.ppai.aplicacion.negocio.Escuela;
import com.ppai.aplicacion.repo.EscuelaRepositorio;
import com.ppai.aplicacion.servicio.EscuelaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EscuelaServicioImpl implements EscuelaServicio {
    public final EscuelaRepositorio escuelaRepositorio;

    @Autowired
    public EscuelaServicioImpl(EscuelaRepositorio escuelaRepositorio) {
        this.escuelaRepositorio = escuelaRepositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Escuela> listarEscuelas() {
        return escuelaRepositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Escuela encontrarPorNombre(String nombreEscuela) {
        return escuelaRepositorio.findByNombre(nombreEscuela);
    }

    @Override
    public String[] buscarEscuelas() {
        List<Escuela> listaEscuelas = listarEscuelas();
        int cantEscuelas = listaEscuelas.size();
        String[] escuelas = new String[cantEscuelas];
        for (int i = 0; i < cantEscuelas; i++) {
            escuelas[i] = listaEscuelas.get(i).getNombre();
        }
        return escuelas;
    }
}
