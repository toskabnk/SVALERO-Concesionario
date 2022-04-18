package com.svalero.concesionario.domain;

import com.svalero.concesionario.util.GeneradorID;

public class Vehiculo {
    String referencia;
    String marca;
    String modelo;
    Integer plazas;
    Integer precioBase;

    public Vehiculo(){

    }
    public Vehiculo(String marca, String modelo, Integer plazas, Integer precioBase) {
        referencia = GeneradorID.generarCodigoAleatorio();
        this.marca = marca;
        this.modelo = modelo;
        this.plazas = plazas;
        this.precioBase = precioBase;
    }

    public Vehiculo(String referencia, String marca, String modelo, Integer plazas, Integer precioBase) {
        this.referencia = referencia;
        this.marca = marca;
        this.modelo = modelo;
        this.plazas = plazas;
        this.precioBase = precioBase;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getPlazas() {
        return plazas;
    }

    public void setPlazas(Integer plazas) {
        this.plazas = plazas;
    }

    public Integer getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Integer precioBase) {
        this.precioBase = precioBase;
    }

    @Override
    public String toString() {
        return "Marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", plazas=" + plazas +
                ", precioBase=" + precioBase +
                "&euro;";
    }
}
