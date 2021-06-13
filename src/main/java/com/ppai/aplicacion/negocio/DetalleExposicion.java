package com.ppai.aplicacion.negocio;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DETALLES_DE_EXPOSICION", schema = "dbo", catalog = "MUSEO_PICTORICO")
public class DetalleExposicion {
    @Id
    @Column(name = "id_exposicion")
    private int idExposicion;

    @OneToOne
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra")
    private Obra obra;

    @Basic
    @Column(name = "lugar_asignado")
    private Integer lugarAsignado;


    @Override
    public String toString() {
        return "DetalleExposicion{" +
                "obra=" + obra +
                ", lugarAsignado=" + lugarAsignado +
                '}';
    }

    public int getIdExposicion() {
        return idExposicion;
    }

    public void setIdExposicion(int idExposicion) {
        this.idExposicion = idExposicion;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Integer getLugarAsignado() {
        return lugarAsignado;
    }

    public void setLugarAsignado(Integer lugarAsignado) {
        this.lugarAsignado = lugarAsignado;
    }


}
