package com.svalero.concesionario.domain;

public class Cliente extends Usuario{
    String dni;
    Integer idUsuario;
    String direccion;
    String provincia;
    String codigoPostal;

    public Cliente() {
    }

    public Cliente(Integer idUsuario, String nombreUsuario, String contraseña, String nombre, String apellidos1, String apellidos2, String telefono, String email, String rol, String dni, String direccion, String provincia, String codigoPostal) {
        super(idUsuario, nombreUsuario, contraseña, nombre, apellidos1, apellidos2, telefono, email, rol);
        this.dni = dni;
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
    }

    public Cliente(Usuario usuario, String dni, String direccion, String provincia, String codigoPostal) {
        super(usuario.getIdUsuario(), usuario.getNombreUsuario(), usuario.getContraseña(), usuario.getNombre(), usuario.getApellidos1(), usuario.getApellidos2(), usuario.getTelefono(), usuario.getEmail(), usuario.getRol());
        this.dni = dni;
        this.idUsuario = usuario.getIdUsuario();
        this.direccion = direccion;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
    }



    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return "Cliente:" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos1='" + apellidos1 + '\'' +
                ", apellidos2='" + apellidos2;
    }
}
