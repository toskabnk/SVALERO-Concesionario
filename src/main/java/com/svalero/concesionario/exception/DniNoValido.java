package com.svalero.concesionario.exception;

public class DniNoValido extends Exception{

    public DniNoValido(String msg){
        super(msg);
    }

    public DniNoValido(){
        super("ERROR: El DNI introducido no es valido");
    }
}
