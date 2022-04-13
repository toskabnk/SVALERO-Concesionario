<%@ page import="com.svalero.concesionario.dao.Database" %>
<%@ page import="com.svalero.concesionario.dao.VehiculoDAO" %>
<%@ page import="com.svalero.concesionario.domain.Vehiculo" %>
<%@ page import="com.svalero.concesionario.dao.VentaDAO" %>
<%@ page import="com.svalero.concesionario.domain.Venta" %>
<%@ page import="com.svalero.concesionario.dao.EmpleadoDAO" %>
<%@ page import="com.svalero.concesionario.domain.Empleado" %>
<%@ page import="com.svalero.concesionario.dao.ClienteDAO" %>
<%@ page import="com.svalero.concesionario.domain.Cliente" %>
<%@ page import="com.svalero.concesionario.dao.UsuarioDAO" %>
<%@ page import="com.svalero.concesionario.domain.Usuario" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>


<!DOCTYPE html>
<html lang="es">
    <%
        String n=(String)session.getAttribute("nombre");
        if(n == null){
            String redirectURL = "login.jsp";
            response.sendRedirect(redirectURL);
        }
    %>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Ver ventas</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>

    <body>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <%
            Database database = new Database();
            Connection connection = database.getConnection();
            Venta venta;
            Empleado empleado;
            Cliente cliente;
            Vehiculo vehiculo;
        %>
        <div class="container">
            <h2>
                <p class="text-center">Lista de todos las ventas</p>
            </h2>
            <div class="container">
                <%
                    try {
                    out.println("<div id=\"accordion\">");
                        ArrayList<Venta> ventas;
                        VentaDAO ventaDAO = new VentaDAO(connection);
                        ventas = ventaDAO.findAll();
                        Integer id = 1;
                        for (Venta aux : ventas) {
                                out.print("<div class=\"card\"><div class=\"card-header\" id=\"heading" + id + "\"><h5 class=\"mb-0\"><button class=\"btn btn-link collapsed\" data-toggle=\"collapse\" data-target=\"#collapse" + id + "\" aria-expanded=\"false\" aria-controls=\"collapse" + id + "\">" + aux.toString() + "</button></h5></div>");

                                ClienteDAO clienteDAO = new ClienteDAO(connection);
                                EmpleadoDAO empleadoDAO = new EmpleadoDAO(connection);
                                VehiculoDAO vehiculoDAO = new VehiculoDAO(connection);

                                cliente = clienteDAO.getCliente(aux.getDni_cliente());
                                empleado = empleadoDAO.getEmpleado(aux.getDni_empleado());
                                vehiculo = vehiculoDAO.getVehiculo(aux.getReferencia());

                                out.print("<div id=\"collapse" + id + "\" class=\"collapse\" aria-labelledby=\"heading" + id + "\" data-parent=\"#accordion\"> <div class=\"card-body\">");
                                out.println(cliente.toString());
                                out.println(empleado.toString());
                                out.println(vehiculo.toString());
                                out.print("<a class=\"btn btn-danger\" href=\"confirmaEliminar.jsp?id=" + aux.getIdVenta() + "\" role=\"button\">Eliminar</a>");
                                out.println("</div></div></div>");
                                id++;
                        }
                    } catch (SQLException sqle) {
                        out.println("<h3>Ha habido un error al cargar los vehiculos</h3>");
                    } finally {
                        database.close();
                    }
                    out.print("</div>");
                %>
            </div>
        </div>
    </body>
</html>