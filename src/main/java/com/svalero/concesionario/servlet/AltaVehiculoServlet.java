package com.svalero.concesionario.servlet;

import com.svalero.concesionario.dao.Database;
import com.svalero.concesionario.dao.VehiculoDAO;
import com.svalero.concesionario.domain.Vehiculo;
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
import java.sql.SQLException;

@WebServlet("/altaVehiculo")
public class AltaVehiculoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession sesion = req.getSession();
        String nombreUsuario = (String) sesion.getAttribute("nombre");
        String contrasenia = (String) sesion.getAttribute("password");

        String marca = req.getParameter("marca");
        String modelo = req.getParameter("modelo");
        Integer plazas = Integer.parseInt(req.getParameter("plazas"));
        Integer precio = Integer.parseInt(req.getParameter("precio"));
        String referencia = req.getParameter("referencia");

        if(nombreUsuario == null || contrasenia == null || !ValidaSesion.validar(nombreUsuario, contrasenia)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        } else {
            Database database = new Database();
            VehiculoDAO vehiculoDAO = new VehiculoDAO(database.getConnection());
            try {
                System.out.println(referencia);
                if(!referencia.equals("null")){
                    Vehiculo vehiculo = new Vehiculo(referencia.trim(), marca.trim(), modelo.trim(), plazas, precio);
                    boolean correcto = vehiculoDAO.modificaVehiulo(vehiculo);
                    if (correcto) {
                        out.println("<div class='alert alert-success' role='alert'>El vehiculo se ha modificado correctamente</div>");
                    } else {
                        out.println("<div class='alert alert-danger' role='alert'>No se ha podido modificar el vehiculo</div>");
                    }
                } else {
                    Vehiculo vehiculo = new Vehiculo(marca.trim(), modelo.trim(), plazas, precio);
                    vehiculoDAO.altaVehiculo(vehiculo);
                    out.println("<div class='alert alert-success' role='alert'>El vehiculo se ha añadido correctamente</div>");
                }
            } catch (YaExisteVehiculo yev) {
                database.close();
                out.println("<div class='alert alert-danger' role='alert'>El vehiculo ya existe</div>");
            } catch (SQLException sqle) {
                database.close();
                out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error</div>");
            }
        }

    }
}
