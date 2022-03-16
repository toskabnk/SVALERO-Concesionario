package com.svalero.concesionario.dao;

import com.svalero.concesionario.domain.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentaDAO {

    private Connection connection;

    public VentaDAO(Connection connection){
        this.connection = connection;
    }

    /**
     * Inserta en la BD una Venta pasada por parametro
     * @param venta Venta a introducir en la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public void altaVenta(Venta venta) throws SQLException {
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

    /**
     * Busca las ventas que tenga el DNI del Cliente pasado por parametro
     * @param dni del Cliente a buscar
     * @return ArrayList con las ventas que tengan ese DNI
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Venta> findVenta(String dni) throws SQLException{
        String sql = "SELECT * FROM VENTAS WHERE dni_cliente = ?";
        ArrayList<Venta> ventas = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, dni);
        ResultSet res = st.executeQuery();
        while (res.next()) {
            Venta venta = new Venta();
            venta.setIdVenta(res.getInt("id_venta"));
            venta.setDni_empleado(res.getString("dni_empleado"));
            venta.setDni_cliente(res.getString("dni_empleado"));
            venta.setReferencia(res.getString("referencia"));
            venta.setMatricula(res.getString("matricula"));
            venta.setColor(res.getString("color"));
            venta.setPrecioTotal(res.getInt("precioTotal"));
            ventas.add(venta);
        }
        st.close();
        return ventas;
    }
}
