package com.svalero.concesionario.dao;

import com.svalero.concesionario.domain.Vehiculo;
import com.svalero.concesionario.exception.YaExisteVehiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class VehiculoDAO {

    private Connection connection;

    public VehiculoDAO(Connection connection){
        this.connection = connection;
    }

    /**
     * Da de alta un vehiculo nuevo en la BD pasado por parametro
     * @param vehiculo Vehiculo nuevo a añadir
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     * @throws YaExisteVehiculo Error lanzado si intentas añadir un vehiculo ya existente en la BD
     */
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

    /**
     * Comprueba si existe un vehiculo en la BD con la misma marca y modelo
     * @param vehiculo Vehiculo a comprobar si existe en BD
     * @return true si existe en la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    private boolean existeVehiculo(Vehiculo vehiculo) throws SQLException{
        Optional<Vehiculo> vehiculo1 = findBy("marca", vehiculo.getMarca());
        Optional<Vehiculo> vehiculo2 = findBy("modelo", vehiculo.getModelo());

        if(vehiculo1.isPresent() && vehiculo2.isPresent()){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo para buscar un vehiculo por el campo y valor pasado por parametros
     * @param campo Campo de la base de datos por el que se quiere buscar en la BD
     * @param valor Valor a buscsar en la BD
     * @return Optional con los vehiculos encontrados, si los hubiera
     * @throws SQLException Si hay algun error no especifico lanzado por la BD.
     */
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
        st.close();
        return Optional.ofNullable(vehiculo);
    }

    /**
     * Devuelve la lista de todos los vehiculos en la BD
     * @return ArrayList con todos los vehiculos encontrados en la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Vehiculo> findAll() throws SQLException {
        String sql = "SELECT * FROM VEHICULO ORDER BY marca";
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res = st.executeQuery();
        while (res.next()){
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setReferencia(res.getString("referencia"));
            vehiculo.setMarca(res.getString("marca"));
            vehiculo.setModelo(res.getString("modelo"));
            vehiculo.setPlazas(res.getInt("plazas"));
            vehiculo.setPrecioBase(res.getInt("precioBase"));
            vehiculos.add(vehiculo);
        }
        st.close();
        return vehiculos;
    }

    /**
     * Devuelve el Vehiculo pasado por parametro
     * @param referencia Referencia del Vehiculo a buscar en la BD
     * @return Vehiculo de la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public Vehiculo getVehiculo(String referencia) throws SQLException {
        String sql = "SELECT * FROM VEHICULO WHERE referencia = ?";
        Vehiculo vehiculo = new Vehiculo();

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, referencia);
        ResultSet res = st.executeQuery();
        while (res.next()){
            vehiculo.setReferencia(res.getString("referencia"));
            vehiculo.setMarca(res.getString("marca"));
            vehiculo.setModelo(res.getString("modelo"));
            vehiculo.setPlazas(res.getInt("plazas"));
            vehiculo.setPrecioBase(res.getInt("precioBase"));
        }
        st.close();
        return vehiculo;
    }

    public boolean modificaVehiulo(Vehiculo vehiculo) throws SQLException {
        String sql = "UPDATE VEHICULO SET marca = ?, modelo = ?, plazas = ?, precioBase = ? WHERE referencia = ?";

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, vehiculo.getMarca());
        st.setString(2, vehiculo.getModelo());
        st.setInt(3, vehiculo.getPlazas());
        st.setInt(4, vehiculo.getPrecioBase());
        st.setString(5, vehiculo.getReferencia());
        int rows = st.executeUpdate();
        return rows == 1;
    }
}
