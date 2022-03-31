<!DOCTYPE html>
<html lang="es">

<head>
    <!-- Comentario -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Concesionario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
    <div class="container">
        <form method="post">
            <div class="form-group">
                <label for="nombre">Introduce tu nombre de usuario</label>
                <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre de usuario">
            </div>
            <div class="form-group">
                <label for="password">Introduce tu contrase&ntilde;a</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Contrase&ntilde;a">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Enviar</button>
            </div>
            <div id="result"></div>
        </form>
    </div>
</body>

</html>