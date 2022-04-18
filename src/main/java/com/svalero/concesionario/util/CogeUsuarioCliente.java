package com.svalero.concesionario.util;

import com.svalero.concesionario.dao.ClienteDAO;
import com.svalero.concesionario.dao.UsuarioDAO;
import com.svalero.concesionario.domain.Cliente;
import com.svalero.concesionario.domain.Usuario;
import com.svalero.concesionario.exception.ClienteNoEncontrado;
import com.svalero.concesionario.exception.UsuarioNoEncontrado;

import java.sql.Connection;
import java.sql.SQLException;

public class CogeUsuarioCliente {

    public static Usuario getUsuario(String nombre, String contrasenia, Connection connection){
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        Usuario usuario = null;
        try {
            usuario = usuarioDAO.getUsuario(nombre, contrasenia).orElseThrow(UsuarioNoEncontrado::new);
        } catch (UsuarioNoEncontrado usuarioNoEncontrado) {
            usuarioNoEncontrado.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return usuario;
    }

    public static Cliente getCliente(Usuario usuario, Connection connection){
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        Cliente cliente = null;
        try {
            cliente = clienteDAO.getCliente(usuario).orElseThrow(ClienteNoEncontrado::new);
        } catch (SQLException | ClienteNoEncontrado throwables) {
            System.out.println("No hay usuario");
        }

        return cliente;
    }

}
