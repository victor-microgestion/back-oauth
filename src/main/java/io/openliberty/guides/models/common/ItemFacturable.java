package io.openliberty.guides.models.common;

public class ItemFacturable {
    private String descripcion;
    private int horasPresupuestadas;
    private String categoriaProyecto;

    // Getters y setters
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getHorasPresupuestadas() {
        return horasPresupuestadas;
    }

    public void setHorasPresupuestadas(int horasPresupuestadas) {
        this.horasPresupuestadas = horasPresupuestadas;
    }

    public String getCategoriaProyecto() {
        return categoriaProyecto;
    }

    public void setCategoriaProyecto(String categoriaProyecto) {
        this.categoriaProyecto = categoriaProyecto;
    }
}