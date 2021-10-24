package com.ppai.aplicacion.negocio.sede.servicio.implementacion;

import com.ppai.aplicacion.negocio.sede.modelo.Sede;
import com.ppai.aplicacion.persistencia.repositorio.SedeRepositorio;
import com.ppai.aplicacion.negocio.sede.servicio.SedeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SedeServicioImpl implements SedeServicio {
    private final SedeRepositorio sedeRepositorio;

    @Autowired
    public SedeServicioImpl(SedeRepositorio sedeRepositorio) {
        this.sedeRepositorio = sedeRepositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sede> listarSedes() {
        return sedeRepositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Sede encontrarPorNombre(String nombreSede) {
        return sedeRepositorio.findByNombre(nombreSede);
    }

    @Override
    public String[] buscarSedes() {
        List<Sede> listaSedes = listarSedes();
        int cantSedes = listaSedes.size();
        String[] sedes = new String[cantSedes];
        for (int i = 0; i < cantSedes; i++) {
            sedes[i] = listaSedes.get(i).getNombre();
        }
        return sedes;
    }
}
