package com.svalero.concesionario.domain;

public class Complementos {
    String id_extra;
    String id_venta;

    public Complementos(String id_extra, String id_venta) {
        this.id_extra = id_extra;
        this.id_venta = id_venta;
    }

    public Complementos() {
    }

    public String getId_extra() {
        return id_extra;
    }

    public void setId_extra(String id_extra) {
        this.id_extra = id_extra;
    }

    public String getId_venta() {
        return id_venta;
    }

    public void setId_venta(String id_venta) {
        this.id_venta = id_venta;
    }
}
