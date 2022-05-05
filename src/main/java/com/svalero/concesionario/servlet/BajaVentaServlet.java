package com.svalero.concesionario.servlet;

import com.svalero.concesionario.dao.ComplementosDAO;
import com.svalero.concesionario.dao.Database;
import com.svalero.concesionario.dao.VentaDAO;
import com.svalero.concesionario.domain.Complementos;
import com.svalero.concesionario.util.ValidaSesion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/bajaVenta")
public class BajaVentaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession sesion = req.getSession();
        String nombreUsuario = (String) sesion.getAttribute("nombre");
        String contrasenia = (String) sesion.getAttribute("password");

        String id = req.getParameter("id");
        System.out.println("Parametro eliminar conseguido: " + id);

        if(nombreUsuario == null || contrasenia == null || !ValidaSesion.validar(nombreUsuario, contrasenia)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        } else {
            Database database = new Database();
            Connection connection = database.getConnection();
            boolean correctoVenta;
            boolean correctoComplementos;

            try {
                VentaDAO ventaDAO = new VentaDAO(connection);
                ComplementosDAO complementosDAO = new ComplementosDAO(connection);

                ArrayList<Complementos> complementos = complementosDAO.findComplemntoVenta(id);

                connection.setAutoCommit(false);

                if(!complementos.isEmpty()){
                    correctoComplementos = complementosDAO.borraComplementos(id);
                    if(correctoComplementos){
                        out.println("<div class='alert alert-success' role='alert'>Complementos eliminados correctamente!</div>");
                    } else {
                        out.println("<div class='alert alert-danger' role='alert'>No hay complementos que eliminar</div>");
                    }
                }

                correctoVenta = ventaDAO.borraVenta(Integer.parseInt(id));
                if(correctoVenta){
                    out.println("<div class='alert alert-success' role='alert'>Venta eliminada correctamente!</div>");
                    connection.commit();
                } else {
                    out.println("<div class='alert alert-danger' role='alert'>No se ha podido eliminar la venta</div>");
                    connection.rollback();
                }
                connection.setAutoCommit(true);
            } catch (SQLException sqle) {
                out.println("<div class='alert alert-danger' role='alert'>Ha habido un error con la base de datos</div>");
                System.out.println(sqle);
            } finally {
                database.close();
            }
        }
    }
}
