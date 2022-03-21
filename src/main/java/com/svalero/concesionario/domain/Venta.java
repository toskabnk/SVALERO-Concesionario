package com.svalero.concesionario.domain;

public class Venta {
    Integer idVenta;
    String dni_empleado;
    String dni_cliente;
    String referencia;
    String matricula;
    String color;
    Integer precioTotal;

    public Venta(){
    }

    public Venta(String dni_empleado, String dni_cliente, String referencia, String matricula, String color, Integer precioTotal) {
        this.dni_empleado = dni_empleado;
        this.dni_cliente = dni_cliente;
        this.referencia = referencia;
        this.matricula = matricula;
        this.color = color;
        this.precioTotal = precioTotal;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public String getDni_empleado() {
        return dni_empleado;
    }

    public void setDni_empleado(String dni_empleado) {
        this.dni_empleado = dni_empleado;
    }

    public String getDni_cliente() {
        return dni_cliente;
    }

    public void setDni_cliente(String dni_cliente) {
        this.dni_cliente = dni_cliente;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", dni_empleado='" + dni_empleado + '\'' +
                ", dni_cliente='" + dni_cliente + '\'' +
                ", referencia='" + referencia + '\'' +
                ", matricula='" + matricula + '\'' +
                ", color='" + color + '\'' +
                ", precioTotal=" + precioTotal +
                '}';
    }
}
