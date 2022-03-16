package com.svalero.concesionario.dao;

import com.svalero.concesionario.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO(Connection connection){
        this.connection=connection;
    }

    /**
     * Devuelve todos los usuarios de la BD
     * @return ArrayList con todos los usuarios
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Usuario> findAll() throws SQLException {
        String sql = "SELECT * FROM USUARIO ORDER BY id_usuario";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res = st.executeQuery();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        if(res.next()){
            Usuario usuario = new Usuario();

            usuario.setIdUsuario(res.getInt("id_usuario"));
            usuario.setNombreUsuario(res.getString("nombreusuario"));
            usuario.setContraseña(res.getString("contraseña"));
            usuario.setNombre(res.getString("nombre"));
            usuario.setApellidos1(res.getString("apellido1"));
            usuario.setApellidos2(res.getString("apellido2"));
            usuario.setTelefono(res.getString("telefono"));
            usuario.setEmail(res.getString("email"));
            usuario.setRol(res.getString("rol"));

            usuarios.add(usuario);
        }
        st.close();
        return usuarios;
    }
}
