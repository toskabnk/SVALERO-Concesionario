<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.svalero.concesionario.dao.Database" %>
<%@ page import="com.svalero.concesionario.dao.VehiculoDAO" %>
<%@ page import="com.svalero.concesionario.domain.Vehiculo" %>
<%@ page import="com.svalero.concesionario.dao.VentaDAO" %>
<%@ page import="com.svalero.concesionario.domain.Venta" %>
<%@ page import="com.svalero.concesionario.dao.EmpleadoDAO" %>
<%@ page import="com.svalero.concesionario.domain.Empleado" %>
<%@ page import="com.svalero.concesionario.dao.ClienteDAO" %>
<%@ page import="com.svalero.concesionario.domain.Cliente" %>

<!DOCTYPE html>
<html lang="es">

<%
    String n=(String)session.getAttribute("nombre");
    if(n == null){
        String redirectURL = "login.jsp";
        response.sendRedirect(redirectURL);
    }
    Database database = new Database();
    Connection connection = database.getConnection();
%>

    <head>
        <!-- Comentario -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Alta venta nueva</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>
        <script type="text/javascript">
                $(document).ready(function() {
                    $("form").on("submit", function(event) {
                        event.preventDefault();
                        var formValue = $(this).serialize();
                        $.post("altaVenta", formValue, function(data) {
                            $("#result").html(data);
                        });
                    });
                });
        </script>
        <div class="container">
        <h2>
            <p class="text-center">Alta venta nueva</p>
        </h2>
            <form>
                <div class="form-group">
                    <label for="empleado">Selecciona el empleado que ha hecho la venta:</label>
                    <select class="form-control" id="empleado" name="empleado">
                        <%
                            EmpleadoDAO empleadoDAO = new EmpleadoDAO(connection);
                            ArrayList<Empleado> empleados = empleadoDAO.findAll();
                            for (Empleado emp : empleados) {
                                out.println("<option value=\"" + emp.getDni() + "\">" + emp.getNombre() + " " + emp.getApellidos1() + "</option>");
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="cliente">Selecciona el cliente que hace la compra:</label>
                    <select class="form-control" id="cliente" name="cliente">
                        <%
                            ClienteDAO clienteDAO = new ClienteDAO(connection);
                            ArrayList<Cliente> clientes = clienteDAO.findAll();
                            for (Cliente cli : clientes) {
                                out.println("<option value=\"" + cli.getDni() + "\">" + cli.getNombre() + " " + cli.getApellidos1() + "</option>");
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="vehiculo">Selecciona el vehiculo que va a comprar el cliente:</label>
                    <select class="form-control" id="vehiculo" name="vehiculo">
                         <%
                             VehiculoDAO vehiculoDAO = new VehiculoDAO(connection);
                             ArrayList<Vehiculo> vehiculos = vehiculoDAO.findAll();
                             for (Vehiculo veh : vehiculos) {
                                 out.println("<option value=\"" + veh.getReferencia() + "\">" + veh.getMarca() + " " + veh.getModelo() + "</option>");
                             }
                         %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="precio">Introduce la matricula asignada:</label>
                    <input type="text" class="form-control" id="matricula" name="matricula" placeholder="0000XXX">
                </div>
                <div class="form-group">
                    <label for="precio">Introduce el color:</label>
                    <input type="text" class="form-control" id="color" name="color" placeholder="Blanco">
                </div>
                <div class="form-group">
                    <label for="precio">Introduce el precio total:</label>
                    <input type="text" class="form-control" id="precio" name="precio" placeholder="23000">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Enviar</button>
                    <button type="button" onclick="window.location.href='index.jsp'" class="btn btn-danger">Volver</button>
                </div>
                <div id="result"></div>
            </form>
        </div>
    </body>

</html>