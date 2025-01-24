package io.openliberty.guides.models;

import java.util.List;

import io.openliberty.guides.models.common.Cotizacion;
import io.openliberty.guides.models.common.Fechas;
import io.openliberty.guides.models.common.ItemFacturable;
import io.openliberty.guides.models.common.Propuesta;

public class Oportunidad {
    private String codigo;
    private String estado;
    private String vendedor;
    private Fechas fechas;
    private List<Propuesta> propuestas;
    private List<Cotizacion> cotizaciones;
    private List<ItemFacturable> itemsFacturables;
    private String workflow;
    private Seguimiento seguimiento;
    private String estadoColores;

    // Getters y setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Fechas getFechas() {
        return fechas;
    }

    public void setFechas(Fechas fechas) {
        this.fechas = fechas;
    }

    public List<Propuesta> getPropuestas() {
        return propuestas;
    }

    public void setPropuestas(List<Propuesta> propuestas) {
        this.propuestas = propuestas;
    }

    public List<Cotizacion> getCotizaciones() {
        return cotizaciones;
    }

    public void setCotizaciones(List<Cotizacion> cotizaciones) {
        this.cotizaciones = cotizaciones;
    }

    public List<ItemFacturable> getItemsFacturables() {
        return itemsFacturables;
    }

    public void setItemsFacturables(List<ItemFacturable> itemsFacturables) {
        this.itemsFacturables = itemsFacturables;
    }

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    public Seguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(Seguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

    public String getEstadoColores() {
        return estadoColores;
    }

    public void setEstadoColores(String estadoColores) {
        this.estadoColores = estadoColores;
    }
}
