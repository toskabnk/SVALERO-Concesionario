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
                    <input type="text" class="form-control" id="marca" name="marca" placeholder="Toyota">
                </div>
                <div class="form-group">
                    <label for="modelo">Introduce el modelo:</label>
                    <input type="text" class="form-control" id="modelo" name="modelo" placeholder="Prius">
                </div>
                <div class="form-group">
                    <label for="plazas">Introduce el numero de plazas:</label>
                    <input type="text" class="form-control" id="plazas" name="plazas" placeholder="5">
                </div>
                <div class="form-group">
                    <label for="precio">Introduce el precio:</label>
                    <input type="text" class="form-control" id="precio" name="precio" placeholder="20000">
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