package io.openliberty.guides.models;

import org.bson.types.ObjectId;

import java.util.Date;

public class Seguimiento {
    private ObjectId oportunidadId;
    private Date fecha;
    private String estado;
    private String comentarios;

    public ObjectId getOportunidadId() {
        return oportunidadId;
    }

    public void setOportunidadId(ObjectId oportunidadId) {
        this.oportunidadId = oportunidadId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}