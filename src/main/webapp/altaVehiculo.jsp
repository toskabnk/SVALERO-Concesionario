<%@ page import="com.svalero.concesionario.dao.Database" %>
<%@ page import="com.svalero.concesionario.dao.VehiculoDAO" %>
<%@ page import="com.svalero.concesionario.domain.Vehiculo" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html lang="es">

<%
    String texto = "Enviar";
    String color = "primary";
    String parametro = request.getParameter("id");
    Boolean modifica = false;
    Vehiculo vehiculo = null;
    String n=(String)session.getAttribute("nombre");
    if(n == null){
        String redirectURL = "login.jsp";
        response.sendRedirect(redirectURL);
    }
    if(parametro != null){
        texto = "Modificar";
        color = "warning";
        modifica = true;
        Database database = new Database();
        Connection connection = database.getConnection();
        VehiculoDAO vehiculoDAO = new VehiculoDAO(connection);

        try {
            vehiculo = vehiculoDAO.getVehiculo(parametro);
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Parametro incorrecto</div>");
        } finally {
            database.close();
        }
    }
%>

    <head>
        <!-- Comentario -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Alta vehiculo nuevo</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>
        <script type="text/javascript">
                $(document).ready(function() {
                    $("form").on("submit", function(event) {
                        event.preventDefault();
                        var formValue = $(this).serialize();
                        $.post("altaVehiculo", formValue, function(data) {
                            $("#result").html(data);
                        });
                    });
                });
        </script>
        <div class="container">
            <form>
                <div class="form-group">
                    <label for="marca">Introduce la marca:</label>
                    <input type="text" class="form-control" id="marca" name="marca" <% if(vehiculo!=null){out.println("value=\"" + vehiculo.getMarca() + "\"");} %> placeholder="Toyota">
                </div>
                <div class="form-group">
                    <label for="modelo">Introduce el modelo:</label>
                    <input type="text" class="form-control" id="modelo" name="modelo" <% if(vehiculo!=null){out.println("value=\"" + vehiculo.getModelo() + "\"");} %> placeholder="Prius">
                </div>
                <div class="form-group">
                    <label for="plazas">Introduce el numero de plazas:</label>
                    <input type="text" class="form-control" id="plazas" name="plazas" <% if(vehiculo!=null){out.println("value=\"" + vehiculo.getPlazas() + "\"");} %> placeholder="5">
                </div>
                <div class="form-group">
                    <label for="precio">Introduce el precio:</label>
                    <input type="text" class="form-control" id="precio" name="precio" <% if(vehiculo!=null){out.println("value=\"" + vehiculo.getPrecioBase() + "\"");} %> placeholder="20000">
                    <input type="hidden" class="form-control" id="referencia" name="referencia" value="<%=parametro%>">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-<%=color%>"><%=texto%></button>
                    <button type="button" onclick="window.location.href='index.jsp'" class="btn btn-danger">Volver</button>
                </div>
                <div id="result"></div>
            </form>
        </div>
    </body>

</html>