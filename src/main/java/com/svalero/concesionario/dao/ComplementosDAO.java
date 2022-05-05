package com.svalero.concesionario.dao;

import com.svalero.concesionario.domain.Complementos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComplementosDAO {
    private Connection connection;

    public ComplementosDAO(Connection connection) { this.connection=connection;}

    /**
     * Devuelve una lista con todos los extras que tenga registrado la venta pasada por parametro
     * @param id_venta id de la venta de la que buscar complementos
     * @return Lista con todos los extras
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Complementos> findComplemntoVenta(String id_venta) throws SQLException {
        String sql = "SELECT * FROM COMPLEMENTOS WHERE id_venta = ?";
        ArrayList<Complementos> complementos = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,id_venta);
        ResultSet res = st.executeQuery();
        while(res.next()){
            Complementos complemento = new Complementos();
            complemento.setId_venta(res.getString("id_venta"));
            complemento.setId_extra(res.getString("id_extra"));
            complementos.add(complemento);
        }

        return complementos;
    }

    /**
     * Borra todos los Extras de la venta pasada por paramatro
     * @param id_venta id de la venta para borrar los extras
     * @return false si no se ha borrado nada, true si ha borrado filas
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public boolean borraComplementos(String id_venta) throws SQLException {
        String sql = "DELETE FROM COMPLEMENTOS WHERE id_venta = ?";

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,id_venta);
        int rows = st.executeUpdate();
        st.close();
        return rows >= 1;
    }

}
