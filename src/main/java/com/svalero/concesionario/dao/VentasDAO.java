package com.svalero.concesionario.dao;

import com.svalero.concesionario.domain.Ventas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentasDAO {

    private Connection connection;

    public VentasDAO(Connection connection){
        this.connection = connection;
    }

    /**
     * Inserta en la BD una Venta pasada por parametro
     * @param venta Venta a introducir en la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public void altaVenta(Ventas venta) throws SQLException {
        String sql = "INSERT INTO VENTAS (dni_empleado, dni_cliente, referencia, matricula, color, precioTotal) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, venta.getDni_empleado());
        st.setString(2, venta.getDni_cliente());
        st.setString(3, venta.getReferencia());
        st.setString(4, venta.getMatricula());
        st.setString(5, venta.getColor());
        st.setInt(6,venta.getPrecioTotal());
        st.executeUpdate();
        st.close();
    }
}
