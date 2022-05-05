package com.svalero.concesionario.domain;

public class Extras {
    String id_extra;
    String descripcion;
    String precio;

    public Extras(String id_extra, String descripcion, String precio) {
        this.id_extra = id_extra;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getId_extra() {
        return id_extra;
    }

    public void setId_extra(String id_extra) {
        this.id_extra = id_extra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
