package com.svalero.concesionario.servlet;

import com.svalero.concesionario.dao.Database;
import com.svalero.concesionario.dao.VehiculoDAO;
import com.svalero.concesionario.domain.Vehiculo;
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

@WebServlet("/buscaVehiculo")
public class BuscaVehiculoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession sesion = req.getSession();
        String nombreUsuario = (String) sesion.getAttribute("nombre");
        String contrasenia = (String) sesion.getAttribute("password");

        String marca = req.getParameter("marca");
        String modelo = req.getParameter("modelo");

        if (nombreUsuario == null || contrasenia == null || !ValidaSesion.validar(nombreUsuario, contrasenia)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        } else {
            Database database = new Database();
            Connection connection = database.getConnection();
            VehiculoDAO vehiculoDAO = new VehiculoDAO(connection);
            ArrayList<Vehiculo> vehiculos;
            if(marca.equals("Todos")){
                try {
                    vehiculos=vehiculoDAO.findByModelo(modelo);
                    if(vehiculos.isEmpty()){
                        out.println("<div class=\"alert alert-warning\" role=\"alert\">No se han encontrado vehiculos con ese modelo</div>");
                    } else {
                        out.println("<ul class='list-group'>");
                        for (Vehiculo vehiculo : vehiculos) {
                            out.println("<li class='list-group-item'>" + vehiculo.toString() + "</a></li>");
                        }
                        out.println("</ul>");
                    }
                }  catch (SQLException sqle) {
                    out.println("<div class='alert alert-danger' role='alert'>Ha habido un error con la base de datos</div>");
                } finally {
                    database.close();
                }
            } else {
                try {
                    vehiculos=vehiculoDAO.findByMarcaModelo(marca,modelo);
                    if(vehiculos.isEmpty()){
                        out.println("<div class=\"alert alert-warning\" role=\"alert\">No se han encontrado vehiculos con esa marca y modelo</div>");
                    } else {
                        out.println("<ul class='list-group'>");
                        for (Vehiculo vehiculo : vehiculos) {
                            out.println("<li class='list-group-item'>" + vehiculo.toString() + "</a></li>");
                        }
                        out.println("</ul>");
                    }
                }  catch (SQLException sqle) {
                    out.println("<div class='alert alert-danger' role='alert'>Ha habido un error con la base de datos</div>");
                } finally {
                    database.close();
                }
            }
        }
    }
}
