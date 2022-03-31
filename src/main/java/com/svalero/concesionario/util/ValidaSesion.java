package com.svalero.concesionario.util;

import com.svalero.concesionario.dao.Database;
import com.svalero.concesionario.dao.UsuarioDAO;
import com.svalero.concesionario.domain.Usuario;
import com.svalero.concesionario.exception.UsuarioNoEncontrado;

import java.sql.SQLException;

public class ValidaSesion {
    public static boolean validar(String nombre, String password) {
        Database database = new Database();
        UsuarioDAO usuarioDAO = new UsuarioDAO(database.getConnection());
        Usuario usuarioActual = null;
        try {
            usuarioActual = usuarioDAO.getUsuario(nombre, password).orElseThrow(UsuarioNoEncontrado::new);
            database.close();
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
            database.close();
            return false;
        } catch (UsuarioNoEncontrado une) {
            System.out.println(une.getMessage());
            database.close();
            return false;
        }
        return true;
    }
}
