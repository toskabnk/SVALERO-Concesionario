package com.svalero.concesionario.servlet;

import com.svalero.concesionario.dao.*;
import com.svalero.concesionario.domain.Cliente;
import com.svalero.concesionario.domain.Empleado;
import com.svalero.concesionario.domain.Vehiculo;
import com.svalero.concesionario.domain.Venta;
import com.svalero.concesionario.exception.DniNoValido;
import com.svalero.concesionario.exception.YaExisteVehiculo;
import com.svalero.concesionario.util.ValidaSesion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.svalero.concesionario.util.ValidaDni.validarDNI;

@WebServlet("/buscaVenta")
public class BuscaVentaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession sesion = req.getSession();
        String nombreUsuario = (String) sesion.getAttribute("nombre");
        String contrasenia = (String) sesion.getAttribute("password");

        String dni = req.getParameter("dni");
        Empleado empleado;
        Cliente cliente;
        Vehiculo vehiculo;

        if (nombreUsuario == null || contrasenia == null || !ValidaSesion.validar(nombreUsuario, contrasenia)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        } else {
            Database database = new Database();
            Connection connection = database.getConnection();
            VentaDAO ventaDAO = new VentaDAO(connection);
            ArrayList<Venta> ventas;
            Integer id = 1;

            try {
                validarDNI(dni);
                ventas = ventaDAO.findVenta(dni);
                if(ventas.isEmpty()){
                    out.println("<div class=\"alert alert-warning\" role=\"alert\">No se han encontrado ventas con ese DNI</div>");
                } else {
                    out.println("<div class='alert alert-success' role='alert'><div id=\"accordion\">");
                    for(Venta aux : ventas){
                        out.print("<div class=\"card\"><div class=\"card-header\" id=\"heading" + id + "\"><h5 class=\"mb-0\"><button class=\"btn btn-link collapsed\" data-toggle=\"collapse\" data-target=\"#collapse" + id + "\" aria-expanded=\"false\" aria-controls=\"collapse" + id + "\">" + aux.toString() + "</button></h5></div>");

                        ClienteDAO clienteDAO = new ClienteDAO(connection);
                        EmpleadoDAO empleadoDAO = new EmpleadoDAO(connection);
                        VehiculoDAO vehiculoDAO = new VehiculoDAO(connection);

                        cliente = clienteDAO.getCliente(aux.getDni_cliente());
                        empleado = empleadoDAO.getEmpleado(aux.getDni_empleado());
                        vehiculo = vehiculoDAO.getVehiculo(aux.getReferencia());

                        out.print("<div id=\"collapse" + id + "\" class=\"collapse\" aria-labelledby=\"heading" + id + "\" data-parent=\"#accordion\"> <div class=\"card-body\">");
                        out.println("<ul class=\"list-group list-group-flush\">");
                        out.print("<li class=\"list-group-item\">");
                        out.println(cliente.toString());
                        out.println("</li>");
                        out.print("<li class=\"list-group-item\">");
                        out.println(empleado.toString());
                        out.println("</li>");
                        out.print("<li class=\"list-group-item\">");
                        out.println(vehiculo.toString());
                        out.println("</li>");
                        out.println("</ul>");
                        out.println("</div></div></div>");
                        id++;
                    }
                    out.print("</div>");
                }
            } catch (DniNoValido dnv) {
                out.println("<div class='alert alert-danger' role='alert'>El DNI introducido no es un DNI valido</div>");
            } catch (SQLException sqle) {
                out.println("<div class='alert alert-danger' role='alert'>Ha habido un error con la base de datos</div>");
            } finally {
                database.close();
            }
        }
    }
}
