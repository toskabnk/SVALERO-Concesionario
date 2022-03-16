package com.svalero.concesionario.dao;

import com.svalero.concesionario.domain.Cliente;
import com.svalero.concesionario.domain.Empleado;
import com.svalero.concesionario.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    /**
     * Devuelve el Usuario que tenga el id pasado por parametro
     * @param id id_usuario de la tabla Usuario a buscar
     * @return Optional de Usuario
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    private Optional<Usuario> getUsuario(Integer id) throws SQLException{
        String sql = "SELECT * FROM USUARIO WHERE id_usuario = ?";
        Usuario usuario = null;

        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,id);
        ResultSet res = st.executeQuery();
        while(res.next()){
            usuario = new Usuario();
            usuario.setIdUsuario(res.getInt("id_usuario"));
            usuario.setNombreUsuario(res.getString("nombreUsuario"));
            usuario.setContraseña(res.getString("contraseña"));
            usuario.setNombre(res.getString("nombre"));
            usuario.setApellidos1(res.getString("apellido1"));
            usuario.setApellidos2(res.getString("apellido2"));
            usuario.setTelefono(res.getString("telefono"));
            usuario.setRol(res.getString("rol"));
        }
        st.close();
        return Optional.ofNullable(usuario);
    }

    /**
     * Devuelve el Usuario del Empleado pasado por parametro
     * @param empleado Empleado a buscar en la tabla de Usuario
     * @return Devuelve el Usuario, si lo ha encontrado
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     * @throws NoSuchElementException Si no existe el Usuario del Empleado
     */
    public Usuario getUsuario(Empleado empleado) throws SQLException, NoSuchElementException {
        return getUsuario(empleado.getIdUsuario()).orElseThrow();
    }

    /**
     * Devuelve el Usuario del Cliente pasado por parametro
     * @param cliente Cliente a buscar en la tabla de Usuario
     * @return Devuelve el Usuario, si lo ha encontrado
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     * @throws NoSuchElementException Si no existe el Usuario del Cliente
     */
    public Usuario getUsuario(Cliente cliente) throws SQLException, NoSuchElementException {
        return getUsuario(cliente.getIdUsuario()).orElseThrow();
    }
}
