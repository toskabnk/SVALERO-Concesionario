package com.svalero;

import com.svalero.concesionario.dao.*;
import com.svalero.concesionario.domain.*;
import com.svalero.concesionario.exception.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

import static com.svalero.concesionario.util.ValidaDni.validarDNI;

public class Menu {
    private Scanner teclado;
    private Database database;
    private Connection connection;
    private Usuario usuarioActual;

    public Menu(){
        teclado = new Scanner(System.in);
    }

    public void muestraMenu(){
        connect();
        login();

        System.out.println("Bienvenido "+usuarioActual.getNombre());
        if(usuarioActual.getRol().equals("USER")){
            menuUsuario();
        } else {
            menuAdmin();
        }
    }

    private void login() {
        System.out.println("Inicia sesion para continuar:");
        System.out.print("Nombre de usuario: ");
        String nombre = teclado.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String contraseña = teclado.nextLine();

        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        try{
            usuarioActual = usuarioDAO.getUsuario(nombre, contraseña).orElseThrow(UsuarioNoEncontrado::new);
        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
            System.exit(0);
        } catch (UsuarioNoEncontrado une){
            System.out.println(une.getMessage());
            System.exit(0);
        }
        System.out.println("\n Login correcto! \n");
    }

    private void menuAdmin() {
        String opcion;
        do{
            System.out.println("Consola de administracion del concesionario");
            System.out.println("Por favor, elige una opcion:");
            System.out.println("1. Añadir un vehiculo");
            System.out.println("2. Ver todos los vehiculos");
            System.out.println("3. Añadir una venta");
            System.out.println("4. Buscar una venta");
            System.out.println("5. Ver todas las ventas");
            System.out.println("6. Modificar un vehiculo");
            System.out.println("7. Buscar vehiculo");
            System.out.println("0. Salir");
            System.out.print("Opcion elegida: ");
            opcion = teclado.nextLine();
            System.out.println("");

            switch (opcion){
                case "1":
                    altaVehiculo();
                    break;
                case "2":
                    verVehiculos();
                    break;
                case "3":
                    altaVenta();
                    break;
                case "4":
                    buscaVenta();
                    break;
                case "5":
                    verVentasEnDetalle();
                    break;
                case "6":
                    modificaVehiculo();
                    break;
                case "7":
                    buscarVehiculo();
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

    private void menuUsuario() {
        String opcion;
        do{
            System.out.println("Consola de administracion del concesionario");
            System.out.println("Por favor, elige una opcion:");
            System.out.println("1. Ver mis compras");
            System.out.println("2. Ver mis compras en detalle");
            System.out.println("3. Buscar vehiculo por marca");
            System.out.println("4. Zona privada");
            System.out.println("0. Salir");
            System.out.print("Opcion elegida: ");
            opcion = teclado.nextLine();
            System.out.println("");

            switch (opcion){
                case "1":
                    verVentaCliente();
                    break;
                case "2":
                    verVentasEnDetalle();
                    break;
                case "3":
                    buscarVehiculo();
                    break;
                case "4":
                    zonaPrivadaCliente();
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

    private void zonaPrivadaCliente(){
        String opcion;
        do{
            System.out.println("Que quieres cambiar:");
            System.out.println("--Cambios de usuario--");
            System.out.println("1. Nombre");
            System.out.println("2. Telefono");
            System.out.println("3. Email");
            System.out.println("4. Direccion");
            System.out.println("5. Provincia");
            System.out.println("6. Codigo Postal");
            System.out.println("7. Contraseña");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = teclado.nextLine();

            switch (opcion){
                case "1":
                    modificaUsuario("nombre");
                    break;
                case "2":
                    modificaUsuario("telefono");
                    break;
                case "3":
                    modificaUsuario("email");
                    break;
                case "4":
                    modificaCliente("direccion");
                    break;
                case "5":
                    modificaCliente("provincia");
                    break;
                case "6":
                    modificaCliente("codigoPostal");
                    break;
                case "7":
                    modificaContraseniaUsuario("contraseña");
                    break;
                case "0":
                    menuUsuario();
                    break;
                default:
                    System.out.println("Opcion elegida incorrecta");


            }
        } while (!opcion.equals("0"));


    }

    private void modificaCliente(String campo){
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        Cliente cliente;

        try{
            cliente = clienteDAO.getCliente(usuarioActual).orElseThrow(ClienteNoEncontrado::new);
            if(campo.equals("direccion")){
                System.out.println("La direccion actual es: " + cliente.getDireccion());
            } else if (campo.equals("provincia")){
                System.out.println("La provincia actual es: " + cliente.getProvincia());
            } else if (campo.equals("codigoPostal")){
                System.out.println("El codigo postal actual es:" + cliente.getCodigoPostal());
            }
            System.out.println("Introduce el nuevo valor: ");
            String valor = teclado.nextLine();
            boolean correcto;
            correcto = clienteDAO.modificaCliente(campo, valor, usuarioActual.getIdUsuario());
            if(correcto) System.out.println("Modificado correctamente!");
        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos");
        } catch (ClienteNoEncontrado cne){
            System.out.println(cne.getMessage());
        }
    }

    private void modificaContraseniaUsuario(String contraseña){
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        Usuario usuario = usuarioActual;

        System.out.println("Introduce la contraseña actual");
        String actual = teclado.nextLine();

        try{
            usuario = usuarioDAO.getUsuario(usuarioActual.getNombreUsuario(), actual).orElseThrow(UsuarioNoEncontrado::new);
            System.out.println("Introduce la nueva contraseña");
            String nueva = teclado.nextLine();
            usuario.setContraseña(nueva);

            boolean correcto;
            correcto = usuarioDAO.modificaUsuario("contraseña", nueva, usuarioActual.getIdUsuario());
            if(correcto){
                System.out.println("Contraseña cambiada correctamente!");
            }
        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
            return;
        } catch (UsuarioNoEncontrado une){
            System.out.println("Contraseña incorrecta");
            return;
        }

    }

    private void modificaUsuario(String campo){
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);

        if(campo.equals("nombre")) {
            System.out.println("Nombre actual:" + usuarioActual.getNombre() + " " +
                    usuarioActual.getApellidos1() + " " +
                    usuarioActual.getApellidos2());
            System.out.println("Introduce el nombre primero:");
            String nombre = teclado.nextLine();
            System.out.println("Introduce tu primer apellido:");
            String apellido1 = teclado.nextLine();
            System.out.println("Introduce tu segundo apellido:");
            String apellido2 = teclado.nextLine();
            try {
                boolean correctoNombre, correctoApellidos1, correctoApellidos2;

                connection.setAutoCommit(false);

                correctoNombre = usuarioDAO.modificaUsuario("nombre", nombre, usuarioActual.getIdUsuario());
                correctoApellidos1 = usuarioDAO.modificaUsuario("apellido1", apellido1, usuarioActual.getIdUsuario());
                correctoApellidos2 = usuarioDAO.modificaUsuario("apellido2", apellido2, usuarioActual.getIdUsuario());

                connection.commit();
                connection.setAutoCommit(true);

                if (correctoNombre && correctoApellidos1 && correctoApellidos2) {
                    System.out.println("El nombre se ha modificado correctamente!");
                } else {
                    System.out.println("Algo ha pasado, no se ha podido modificar el nombre");
                }
            } catch (SQLException sqle) {
                System.out.println("Ha habido un error con la base de datos");
                sqle.printStackTrace();
            }
        } else {
            System.out.print("Valor anterior del " + campo + ": ");
            if(campo.equals("telefono")){
                System.out.println(usuarioActual.getTelefono());
            } else if(campo.equals("email")){
                System.out.println(usuarioActual.getEmail());
            }

            System.out.println("Introduce el nuevo valor:");
            String valor = teclado.nextLine();

            try{
                boolean correcto;
                correcto = usuarioDAO.modificaUsuario(campo, valor, usuarioActual.getIdUsuario());
                if(correcto) System.out.println("Se ha modificado correctamente el " + campo);
            } catch (SQLException sqle) {
                System.out.println("Ha habido un error con la base de datos");
                sqle.printStackTrace();
            }
        }
    }

    private void buscarVehiculo(){
        VehiculoDAO vehiculoDAO = new VehiculoDAO(connection);
        String marca;

        System.out.println("Selecciona la marca de la que buscar un vehiculo: ");
        try{
            ArrayList<String> marcas;
            ArrayList<Vehiculo> vehiculos;

            marcas = vehiculoDAO.getMarcas();

            int index = 1;
            for(String str : marcas){
                System.out.println(index + ". " + str);
                index++;
            }
            System.out.print("Opcion elegida: ");
            String opcion = teclado.nextLine();
            Integer indiceMarca = Integer.parseInt(opcion);
            marca = marcas.get(indiceMarca-1);

            vehiculos = vehiculoDAO.findByMarca(marca);
            index = 1;
            for(Vehiculo veh : vehiculos){
                System.out.println(index + ". " + veh.toString());
                index++;
            }


        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos");
            return;
        } catch (NumberFormatException nfe){
            System.out.println("ERROR: Lo que has introducido no es un numero");
            return;
        } catch (IndexOutOfBoundsException ioobe){
            System.out.println("ERROR: El numero elegido no es una opcion");
            return;
        }

    }

    private void modificaVehiculo(){
        VehiculoDAO vehiculoDAO = new VehiculoDAO(connection);
        Vehiculo vehiculo;

        try {
            ArrayList<Vehiculo> vehiculos;

            System.out.println("Selecciona el vehiculo a modificar: ");
            vehiculos = vehiculoDAO.findAll();

            int indice = 1;
            for(Vehiculo aux : vehiculos){
                System.out.println(indice + ". " + aux.toString());
                indice++;
            }
            System.out.print("Opcion elegida: ");
            String opcion = teclado.nextLine();
            Integer indiceVehiculo = Integer.parseInt(opcion);
            vehiculo = vehiculos.get(indiceVehiculo-1);
        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos");
            return;
        } catch (NumberFormatException nfe){
            System.out.println("ERROR: Lo que has introducido no es un numero");
            return;
        } catch (IndexOutOfBoundsException ioobe){
            System.out.println("ERROR: El numero elegido no es una opcion");
            return;
        }

        try {
            System.out.println("Introduce la marca (Actual: " + vehiculo.getMarca() + "):");
            vehiculo.setMarca(teclado.nextLine());
            compruebaVacio(vehiculo.getMarca());
            System.out.println("Introduce el modelo (Actual: " + vehiculo.getModelo() + "):");
            vehiculo.setModelo(teclado.nextLine());
            compruebaVacio(vehiculo.getModelo());
            System.out.println("Introduce el numero de plazas (Actual: " + vehiculo.getPlazas() + "):");
        } catch (IllegalArgumentException iae){
                System.out.println(iae.getMessage());
                return;
            }

        Integer plazas, precio;
        try {
            plazas = Integer.parseInt(teclado.nextLine());
            System.out.println("Introduce el precio base (Actual: " + vehiculo.getPrecioBase() +"):");
            precio = Integer.parseInt(teclado.nextLine());
            vehiculo.setPlazas(plazas);
            vehiculo.setPrecioBase(precio);

        } catch (NumberFormatException nfe){
            nfe.printStackTrace();
            System.out.println("Error: Vehiculo no modificado");
            return;
        }

        try{
            boolean correcto = vehiculoDAO.modificaVehiulo(vehiculo);
            if(correcto){
                System.out.println("\n El vehiculo se ha modificado correctamente! \n");
            } else {
                System.out.println("\n El vehiculo no se ha podido modificar \n");
            }

        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos");
            return;
        }

    }

    private void compruebaVacio (String campo) throws IllegalArgumentException {
        if(campo.isEmpty()){
            throw new IllegalArgumentException("El campo no puede estar vacio");
        }
    }

    private void verVentasEnDetalle(){
        Venta venta;
        Empleado empleado;
        Cliente cliente;
        Vehiculo vehiculo;

        try{
            Integer indiceVenta;
            ArrayList<Venta> ventas;
            VentaDAO ventaDAO = new VentaDAO(connection);
            ClienteDAO clienteDAO = new ClienteDAO(connection);

            if(usuarioActual.getRol().equals("USER")){
                cliente = clienteDAO.getCliente(usuarioActual).orElseThrow(ClienteNoEncontrado::new);
                ventas = ventaDAO.findVenta(cliente.getDni());
            } else {
                ventas = ventaDAO.findAll();
            }

            if(ventas.isEmpty()){
                System.out.println("No hay ninguna venta ¿Raro verdad? Alguien la tiene que haber liado, revisa la BD");
                return;
            } else {
                int index = 1;
                for (Venta aux : ventas) {
                    System.out.println(index + ". " + aux.toString());
                    index++;
                }

                System.out.print("Seleccione una venta para ver en mas detalle: ");
                String opcion = teclado.nextLine();
                indiceVenta = Integer.parseInt(opcion);
                venta = ventas.get(indiceVenta-1);
            }
        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos");
            return;
        } catch (NumberFormatException nfe){
            System.out.println("ERROR: Lo que has introducido no es un numero");
            return;
        } catch (IndexOutOfBoundsException ioobe){
            System.out.println("ERROR: El numero elegido no es una opcion");
            return;
        } catch (ClienteNoEncontrado cne){
            System.out.println(cne.getMessage());
            return;
        }

        try {
            ClienteDAO clienteDAO = new ClienteDAO(connection);
            EmpleadoDAO empleadoDAO = new EmpleadoDAO(connection);
            VehiculoDAO vehiculoDAO = new VehiculoDAO(connection);

            cliente = clienteDAO.getCliente(venta.getDni_cliente());
            empleado = empleadoDAO.getEmpleado(venta.getDni_empleado());
            vehiculo = vehiculoDAO.getVehiculo(venta.getReferencia());
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            return;
        }

        System.out.println("Detalles de la venta seleccionada: ");
        System.out.println(venta.toString());
        System.out.println("\n Empleado que hizo la venta: ");
        System.out.println(empleado.toString());
        System.out.println("\n El cliente que hizo la compra: ");
        System.out.println(cliente.toString());
        System.out.println("\n Vehiculo comprado por el cliente: ");
        System.out.println(vehiculo.toString());
    }

    private void verVentaCliente() {
        VentaDAO ventaDAO = new VentaDAO(connection);
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        ArrayList<Venta> ventas;

        try{
            Cliente cliente = clienteDAO.getCliente(usuarioActual).orElseThrow(ClienteNoEncontrado::new);
            ventas = ventaDAO.findVenta(cliente.getDni());
            if(ventas.isEmpty()){
                System.out.println("No tienes ninguna compra realizada en el concesionario");
            } else {
                for (Venta venta : ventas){
                    System.out.println(venta.toString());
                }
            }
        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos");
        } catch (ClienteNoEncontrado cne){
            System.out.println(cne.getMessage());
        }
    }

    private void buscaVenta() {
        VentaDAO ventaDAO = new VentaDAO(connection);
        ArrayList<Venta> ventas;

        System.out.print("Introduce el DNI del ciente para buscar la venta: ");
        String dni = teclado.nextLine().trim();

        try {
            validarDNI(dni);
            ventas = ventaDAO.findVenta(dni);
        } catch (DniNoValido dnv){
            System.out.println(dnv.getMessage());
            return;
        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos");
            return;
        }

        if(ventas.isEmpty()){
            System.out.println("No hay ninguna venta registrada con el DNI introducido.");
        } else {
            int index = 1;
            for (Venta venta : ventas){
                System.out.println(index + ".- " + venta.toString());
                index++;
            }
        }
        System.out.println("");
    }

    private void altaVenta() {
        VentaDAO ventaDAO = new VentaDAO(connection);
        EmpleadoDAO empleadoDAO = new EmpleadoDAO(connection);
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        VehiculoDAO vehiculoDAO = new VehiculoDAO(connection);

        Empleado empleado;
        Cliente cliente;
        Vehiculo vehiculo;

        System.out.println("Por favor, selecciona el empleado que ha hecho la venta:");
        try{
            ArrayList<Empleado> empleados;
            Integer indiceEmpleado;
            Integer auxEmpleado = 0;

            empleados = empleadoDAO.findAll();

            for(Empleado emp : empleados){
                System.out.println((auxEmpleado+1) + ". " + emp.toString());
                auxEmpleado++;
            }
            System.out.print("Opcion elegida: ");
            String opcion = teclado.nextLine();
            indiceEmpleado = Integer.parseInt(opcion);
            empleado = empleados.get(indiceEmpleado-1);
        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
            return;
        } catch (NumberFormatException nfe){
            System.out.println("ERROR: Lo que has introducido no es un numero");
            System.out.println("Venta no añadida");
            return;
        } catch (IndexOutOfBoundsException ioobe){
            System.out.println("ERROR: El numero elegido no es una opcion");
            return;
        }

        System.out.println("Por favor, selecciona al cliente:");
        try{
            ArrayList<Cliente> clientes;
            Integer auxCliente = 0;
            Integer indiceCliente;

            clientes = clienteDAO.findAll();

            for(Cliente cl : clientes){
                System.out.println((auxCliente+1) + ". " + cl.toString());
                auxCliente++;
            }
            System.out.print("Opcion elegida: ");
            String opcion = teclado.nextLine();
            indiceCliente = Integer.parseInt(opcion);
            cliente = clientes.get(indiceCliente-1);
        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
            return;
        } catch (NumberFormatException nfe){
            System.out.println("ERROR: Lo que has introducido no es un numero");
            System.out.println("Venta no añadida");
            return;
        } catch (IndexOutOfBoundsException ioobe){
            System.out.println("ERROR: El numero elegido no es una opcion");
            return;
        }

        System.out.println("Por favor, selecciona el modelo de vehiculo que se ha vendido:");
        try{
            ArrayList<Vehiculo> vehiculos;
            Integer auxVehiculo = 0;
            Integer indiceVehiculo;

            vehiculos = vehiculoDAO.findAll();

            for(Vehiculo cl : vehiculos){
                System.out.println((auxVehiculo+1) + ". " + cl.toString());
                auxVehiculo++;
            }
            System.out.print("Opcion elegida: ");
            String opcion = teclado.nextLine();
            indiceVehiculo = Integer.parseInt(opcion);
            vehiculo = vehiculos.get(indiceVehiculo-1);
        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
            return;
        } catch (NumberFormatException nfe){
            System.out.println("ERROR: Lo que has introducido no es un numero");
            System.out.println("Venta no añadida");
            return;
        } catch (IndexOutOfBoundsException ioobe){
            System.out.println("ERROR: El numero elegido no es una opcion");
            return;
        }
        System.out.println("Introduce la matricula asignada:");
        String matricula = teclado.nextLine();
        System.out.println("Introduce el color:");
        String color = teclado.nextLine();
        System.out.println("Introduce el precio total:");
        String precioTotal = teclado.nextLine();
        Integer precio;
        try{
            precio=Integer.parseInt(precioTotal);
        } catch (NumberFormatException nfe){
            System.out.println("ERROR: Lo que has introducido no es un numero");
            return;
        }

        Venta venta = new Venta(empleado.getDni(), cliente.getDni(), vehiculo.getReferencia(), matricula, color, precio);

        try{
            ventaDAO.altaVenta(venta);
        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
        }
        System.out.println("");
        System.out.println("Venta añadida!");
        System.out.println("");

    }

    private void verVehiculos() {
        VehiculoDAO vehiculoDAO = new VehiculoDAO(connection);

        System.out.println("Lista de vehiculos:");
        try{
            ArrayList<Vehiculo> vehiculos = vehiculoDAO.findAll();
            for(Vehiculo vehiculo : vehiculos){
                System.out.println(vehiculo.toString());
            }
        } catch (SQLException sqle){
            System.out.println("Ha habido un error con la base de datos.");
            sqle.printStackTrace();
        }
        System.out.println("");
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
        System.out.println("");
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

    //Parte de listado y vista en detalle

    //Parte de funcionalidad de busqueda

    //Parte de dar de baja
    //TODO: Eliminar vehiculos que no se hayan usado en ninguna venta

    //-----Otras funcionalidades-----
}
