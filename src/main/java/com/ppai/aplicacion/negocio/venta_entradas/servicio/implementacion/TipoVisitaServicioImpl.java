package com.ppai.aplicacion.negocio.venta_entradas.servicio.implementacion;

import com.ppai.aplicacion.negocio.venta_entradas.modelo.TipoVisita;
import com.ppai.aplicacion.persistencia.repositorio.TipoVisitaRepositorio;
import com.ppai.aplicacion.negocio.venta_entradas.servicio.TipoVisitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoVisitaServicioImpl implements TipoVisitaServicio {
    private final TipoVisitaRepositorio tipoVisitaRepositorio;

    @Autowired
    public TipoVisitaServicioImpl(TipoVisitaRepositorio tipoVisitaRepositorio) {
        this.tipoVisitaRepositorio = tipoVisitaRepositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoVisita> listarTiposVisita() {
        return tipoVisitaRepositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public TipoVisita encontrarPorNombre(String nombreTipoVisita) {
        return tipoVisitaRepositorio.findByNombre(nombreTipoVisita);
    }

    @Override
    public String[] buscarTiposVisita() {
        List<TipoVisita> listaTiposVisita = listarTiposVisita();
        int cantTiposVisita = listaTiposVisita.size();
        String[] tiposVisita = new String[cantTiposVisita];
        for (int i = 0; i < cantTiposVisita; i++) {
            tiposVisita[i] = listaTiposVisita.get(i).getNombre();
        }
        return tiposVisita;
    }
}
