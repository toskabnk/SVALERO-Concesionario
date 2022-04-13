<%@ page import="com.svalero.concesionario.dao.Database" %>
<%@ page import="com.svalero.concesionario.dao.VehiculoDAO" %>
<%@ page import="com.svalero.concesionario.domain.Vehiculo" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>

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
        <title>Ver vehiculos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>

    <body>
        <%
            Database database = new Database();
            VehiculoDAO vehiculoDAO = new VehiculoDAO(database.getConnection());
        %>
        <div class="container">
            <h2>
                <p class="text-center">Lista de todos los vehiculos</p>
            </h2>
            <div class="container">
                <%
                    try {
                        out.println("<ul class='list-group'>");
                        ArrayList<Vehiculo> vehiculos = vehiculoDAO.findAll();
                        for (Vehiculo vehiculo : vehiculos) {
                            out.println("<li class='list-group-item d-flex justify-content-between align-items-center'>" + vehiculo.toString() + "<a class=\"btn btn-warning\" href=\"altaVehiculo.jsp?id=" + vehiculo.getReferencia() + "\" role=\"button\">Modificar</a></a></li>");
                        }
                        out.println("</ul>");
                    } catch (SQLException sqle) {
                        out.println("<h3>Ha habido un error al cargar los vehiculos</h3>");
                    }
                %>
            </div>
        </div>
    </body>
</html>