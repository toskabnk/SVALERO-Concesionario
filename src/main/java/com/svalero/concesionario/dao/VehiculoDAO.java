package com.svalero.concesionario.dao;

import com.svalero.concesionario.domain.Vehiculo;
import com.svalero.concesionario.exception.YaExisteVehiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VehiculoDAO {

    private Connection connection;

    public VehiculoDAO(Connection connection){
        this.connection = connection;
    }

    public void altaVehiculo(Vehiculo vehiculo) throws SQLException, YaExisteVehiculo {
        if(existeVehiculo(vehiculo)){
            throw new YaExisteVehiculo();
        }

        String sql = "INSERT INTO VEHICULO (referencia, marca, modelo, plazas, precioBase) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, vehiculo.getReferencia());
        st.setString(2, vehiculo.getMarca());
        st.setString(3, vehiculo.getModelo());
        st.setInt(4, vehiculo.getPlazas());
        st.setInt(5, vehiculo.getPrecioBase());
        st.executeUpdate();

        st.close();
    }

    private boolean existeVehiculo(Vehiculo vehiculo) throws SQLException{
        Optional<Vehiculo> vehiculo1 = findBy("marca", vehiculo.getMarca());
        Optional<Vehiculo> vehiculo2 = findBy("modelo", vehiculo.getModelo());

        if(vehiculo1.isPresent() && vehiculo2.isPresent()){
            return true;
        } else {
            return false;
        }
    }

    public Optional<Vehiculo> findBy(String campo, String valor) throws SQLException{
        String sql = "SELECT * FROM VEHICULO WHERE " + campo.trim() + " = ?";
        Vehiculo vehiculo = null;

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, valor);
        ResultSet res = st.executeQuery();
        if(res.next()){
            vehiculo = new Vehiculo();
            vehiculo.setReferencia(res.getString("referencia"));
            vehiculo.setMarca(res.getString("marca"));
            vehiculo.setModelo(res.getString("modelo"));
            vehiculo.setPlazas(res.getInt("plazas"));
            vehiculo.setPrecioBase(res.getInt("precioBase"));
        }

        return Optional.ofNullable(vehiculo);
    }
}
