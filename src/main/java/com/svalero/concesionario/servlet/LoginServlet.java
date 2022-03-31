package com.svalero.concesionario.servlet;

import com.svalero.concesionario.util.ValidaSesion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {

    public LoginServlet(){
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET");
        HttpSession sesion = req.getSession();
        String nombreUsuario;
        String contrasenia;
        if(sesion.isNew()){
            System.out.println("Sesion nueva creada");
            nombreUsuario = req.getParameter("nombre");
            contrasenia = req.getParameter("password");
        } else {
            if(sesion.getAttribute("nombre") == null){
                System.out.println("Sesion vacia");
                nombreUsuario = req.getParameter("nombre");
                contrasenia = req.getParameter("password");
            } else {
                System.out.println("Sesion anterior cargada");
                nombreUsuario = sesion.getAttribute("nombre").toString();
                contrasenia = sesion.getAttribute("password").toString();
            }
        }
        if(nombreUsuario == null || contrasenia == null || !ValidaSesion.validar(nombreUsuario, contrasenia)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        } else {
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
}
