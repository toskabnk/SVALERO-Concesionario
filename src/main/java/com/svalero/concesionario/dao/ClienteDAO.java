package com.svalero.concesionario.dao;

import com.svalero.concesionario.domain.Cliente;
import com.svalero.concesionario.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    private Connection connection;

    public ClienteDAO(Connection connection){
        this.connection= connection;
    }

    /**
     * Devuelve todos los clientes de la BD
     * @return ArrayList con todos los clientes de la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Cliente> findAll() throws SQLException {
        String sql = "SELECT * FROM CLIENTE ORDER BY dni";
        ArrayList<Cliente> clientes = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res = st.executeQuery();
        while(res.next()){
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            Cliente cliente = new Cliente();
            cliente.setDni(res.getString("dni"));
            cliente.setIdUsuario(res.getInt("id_usuario"));
            cliente.setDireccion(res.getString("direccion"));
            cliente.setCodigoPostal(res.getString("codigopostal"));
            Usuario usuario = usuarioDAO.getUsuario(cliente);
            cliente.setNombreUsuario(usuario.getNombreUsuario());
            cliente.setContraseña(usuario.getContraseña());
            cliente.setNombre(usuario.getNombre());
            cliente.setApellidos1(usuario.getApellidos1());
            cliente.setApellidos2(usuario.getApellidos2());
            cliente.setTelefono(usuario.getTelefono());
            cliente.setEmail(usuario.getTelefono());
            cliente.setRol(usuario.getRol());
            clientes.add(cliente);
        }
        return clientes;
    }
}
