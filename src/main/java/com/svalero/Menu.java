package com.svalero;

import com.svalero.concesionario.dao.Database;
import com.svalero.concesionario.dao.VehiculoDAO;
import com.svalero.concesionario.domain.Vehiculo;
import com.svalero.concesionario.exception.YaExisteVehiculo;

import java.sql.Connection;
import java.sql.SQLException;
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
        String opcion;
        do{
            System.out.println("Consola de administracion del concesionario");
            System.out.println("Por favor, elige una opcion:");
            System.out.println("1. Añadir un vehiculo");
            System.out.println("0. Salir");
            opcion = teclado.nextLine();

            switch (opcion){
                case "1":
                    altaVehiculo();
                    break;
                case "0":
                    close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion elegida incorrecta");
                    break;
            }
        } while (!opcion.equals("0"));
    }

    private void altaVehiculo(){
        VehiculoDAO vehiculoDAO = new VehiculoDAO(connection);

        System.out.println("Introduce la marca:");
        String marca = teclado.nextLine();
        System.out.println("Introduce el modelo:");
        String modelo = teclado.nextLine();
        System.out.println("Introduce el numero de plazas:");
        Integer plazas, precio;
        try {
            plazas = Integer.parseInt(teclado.nextLine());
            System.out.println("Introduce el precio base:");
            precio = Integer.parseInt(teclado.nextLine());

        } catch (NumberFormatException nfe){
            nfe.printStackTrace();
            System.out.println("Error: Vehiculo no introducido");
            return;
        }

        Vehiculo vehiculo = new Vehiculo(marca.trim(), modelo.trim(), plazas, precio);
        try{
            vehiculoDAO.altaVehiculo(vehiculo);
            System.out.println("El vehiculo se ha añadido correctamente.");
        } catch (YaExisteVehiculo yev){
            System.out.println(yev.getMessage());
        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
        }
    }

    public void connect(){
        database = new Database();
        connection = database.getConnection();
    }

    public void close(){
        database.close();
        System.out.println("Base de datos desconectada.");
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
