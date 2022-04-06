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
        <title>Concesionario</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>

    <body>
        <div class="container">
            <h2>
                <p class="text-center">Panel principal de administracion del Concesionario</p>
            </h2>
            <div class="container">
                <p> Bienvenido
                    <%
                    out.print(n);
                %>
                        <form action="logout" method="post">
                            <input type="submit" name="logout" value="Cerrar sesion" />
                        </form>
                </p>
            </div>
            <div class="list-group">
                <a href="altaVehiculo.jsp" class="list-group-item list-group-item-action">A&ntilde;adir vehiculo nuevo</a>
                <a href="verVehiculos.jsp" class="list-group-item list-group-item-action">Ver todos los vehiculos</a>
                <a href="verVentas.jsp" class="list-group-item list-group-item-action">Ver todas las ventas</a>
            </div>
        </div>
    </body>

</html>