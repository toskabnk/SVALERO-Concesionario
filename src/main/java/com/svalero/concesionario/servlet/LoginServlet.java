package com.svalero.concesionario.servlet;

import com.svalero.concesionario.dao.Database;
import com.svalero.concesionario.dao.UsuarioDAO;
import com.svalero.concesionario.domain.Usuario;
import com.svalero.concesionario.exception.UsuarioNoEncontrado;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {

    public LoginServlet(){
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET");
        String nombreUsuario = req.getParameter("nombre");
        String contrasenia = req.getParameter("password");
        if(nombreUsuario == null || contrasenia == null || !validar(nombreUsuario, contrasenia)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        } else {
            HttpSession sesion = req.getSession();
            sesion.setAttribute("nombre", nombreUsuario);
            sesion.setAttribute("password", contrasenia);
            RequestDispatcher dispatcher = req.getRequestDispatcher("main.jsp");
            dispatcher.forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST");
        doGet(request, response);
    }

    boolean validar(String nombre, String password) {
        Database database = new Database();
        UsuarioDAO usuarioDAO = new UsuarioDAO(database.getConnection());
        Usuario usuarioActual = null;
        try {
            usuarioActual = usuarioDAO.getUsuario(nombre, password).orElseThrow(UsuarioNoEncontrado::new);
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
            return false;
        } catch (UsuarioNoEncontrado une) {
            System.out.println(une.getMessage());
            return false;
        }
        return true;
    }
}
