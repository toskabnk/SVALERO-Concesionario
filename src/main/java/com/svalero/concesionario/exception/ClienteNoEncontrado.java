package com.svalero.concesionario.exception;

public class ClienteNoEncontrado extends  Exception{
    public ClienteNoEncontrado(){
        super("ERROR: Cliente no encontrado");
    }
}
