package com.svalero.concesionario.exception;

public class YaExisteVehiculo extends Exception {

    public YaExisteVehiculo(String msg){
        super(msg);
    }

    public YaExisteVehiculo() {
        super("ERROR: Ya existe ese vehiculo.");
    }
}
