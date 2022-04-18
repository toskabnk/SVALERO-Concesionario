package com.svalero.concesionario.servlet;

import com.svalero.concesionario.dao.Database;
import com.svalero.concesionario.dao.UsuarioDAO;
import com.svalero.concesionario.domain.Usuario;
import com.svalero.concesionario.exception.UsuarioNoEncontrado;
import com.svalero.concesionario.util.ValidaSesion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public LoginServlet(){
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sesion = req.getSession();
        String nombreUsuario = req.getParameter("nombre");
        String contrasenia = req.getParameter("password");

        if(nombreUsuario == null || contrasenia == null || !ValidaSesion.validar(nombreUsuario, contrasenia)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        } else {
            Usuario usuario = null;
            Database database = new Database();
            Connection connection = database.getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            try {
                usuario = usuarioDAO.getUsuario(nombreUsuario, contrasenia).orElseThrow(UsuarioNoEncontrado::new);
            } catch (SQLException sqle) {
                System.out.println("Ha habido un error con la base de datos");
            } catch (UsuarioNoEncontrado une) {
                System.out.println(une.getMessage());
            } finally {
                database.close();
            }
            sesion.setAttribute("nombre", nombreUsuario);
            sesion.setAttribute("password", contrasenia);
            sesion.setAttribute("role", usuario.getRol());
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST");
        doGet(request, response);
    }
}
