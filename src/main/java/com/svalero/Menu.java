package com.svalero;

import com.svalero.concesionario.dao.Database;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.util.Scanner;

public class Menu {
    private Scanner teclado;
    private Database database;
    private Connection connection;

    public Menu(){
        teclado = new Scanner(System.in);
    }

    public void muestraMenu(){
        connect();


    }

    public void connect(){
        database = new Database();
        connection = database.getConnection();
    }

    public void close(){
        database.close();
    }

    //-----Requisitos-----
    //Parte de dar de alta
    //TODO: Dar de alta ventas nuevas
    //TODO: Dar de alta vehiculos nuevos

    //Parte de listado y vista en detalle
    //TODO: Ver las ventas con toda su informacion (Vehiculo vendido, extras, cliente...)
    //TODO: Ver los vehiculos

    //Parte de funcionalidad de busqueda
    //TODO: Buscar una venta en especifico
    //TODO: Buscar los vehiculos por marca, modelo...

    //Parte de dar de baja
    //TODO: Eliminar vehiculos que no se hayan usado en ninguna venta

    //-----Otras funcionalidades-----
    //TODO: Funcionalidad de login
    //TODO: Poder modificar un vehiculo
    //TODO: Zona privada para poder moficar los datos de usuario.
}
