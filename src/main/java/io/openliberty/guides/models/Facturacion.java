package io.openliberty.guides.models;

import org.bson.types.ObjectId;

import java.util.List;

public class Facturacion {
    private ObjectId oportunidadId;
    private List<Factura> facturas;
    private int horasConsumidas;

    public ObjectId getOportunidadId() {
        return oportunidadId;
    }

    public void setOportunidadId(ObjectId oportunidadId) {
        this.oportunidadId = oportunidadId;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public int getHorasConsumidas() {
        return horasConsumidas;
    }

    public void setHorasConsumidas(int horasConsumidas) {
        this.horasConsumidas = horasConsumidas;
    }

    public static class Factura {
        private String numero;
        private String archivoPdf;
        private String estado;
        private java.util.Date fechaFacturacion;

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getArchivoPdf() {
            return archivoPdf;
        }

        public void setArchivoPdf(String archivoPdf) {
            this.archivoPdf = archivoPdf;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public java.util.Date getFechaFacturacion() {
            return fechaFacturacion;
        }

        public void setFechaFacturacion(java.util.Date fechaFacturacion) {
            this.fechaFacturacion = fechaFacturacion;
        }
    }
}