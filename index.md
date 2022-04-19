## Concesionario WebApp

En este repositorio puedes encontrar la WebApp desarrollada para las asignaturas de Programacion (WebApp), Base de Datos (BBDD) y Entornos de Desarrollo (Configuracion de Jenkins, SonarQube y tests JUnit)

### Requisitos para la ejecucion de la aplicacion

Para ejecutar correctamente la aplicacion debes tener varios programas instalados y configurados:
- Java 11 instalado
- Tomcat 8.5.77 o superior
- Oracle Express Edition instalado con un usuario Concesionario con contraseña 1234 creado. Tambien tienes que ejecutar los scripts SQL siguientes [Concesionario.sql](https://github.com/toskabnk/SVALERO-Concesionario/blob/master/sql/CONCESIONARIO.sql) y [Inserts.sql](https://github.com/toskabnk/SVALERO-Concesionario/blob/master/sql/INSERTS.sql)
Una vez tengas los programas necesarios instalados puedes proceder a su ejecucion

### Como ejecutar la aplicacion

Hay dos opciones para ejecutar la aplicacion, ejecutar el .jar o desplegar en Tomcat el .war.
 - Para ejecutar el .jar solo tienes que ir abrir un terminal y ejecutar esta linea `java -jar Concesionario.jar`
   ![GIF](https://i.gyazo.com/334f4163f65a3ed6db83713e86bcd33f.gif)
   Ejecutandolo de esta forma solo puedes interactuar con la aplicacion desde el terminar, no puedes acceder a la parte de la aplicacion web.
 
 - Para desplegar el .war tienes que tener Tomcat ejecutandose, acceder al gestori de aplicaciones y desde el apartado de Desplegar seleccionar el .war descargado. Una vez desplegado solo tienes que acceder a la ruta en la que tengas Tomcar y añadir `/concesionario`
   ![GIF](https://i.gyazo.com/bc4e76878a8dc7b04a7972180f49c773.gif)
   
### Uso de la aplicacion

(Aqui solo voy a abarcar el uso de la WebApp, aunque en su uso en terminal es igual de facil)

Una vez ejecutada la aplicacion con Tomcar y accedido a su URL nos mostrara la pagina de login, si has eje utado el script de parametrizacion inicial puedes inicar sesion con varios usuarios:
- ***SrToska - 1234*** Con el ROL de Admin, puede añadir, modificar y eliminar entidades
- ***Emp1 - 1234*** Con el ROL Employee, tiene los mismos permisos que Admin
- ***User1 - 1234*** Con el ROL de User, solo puede ver los objetos creados y las ventas en las que forme parte

Una vez iniciado sesion con alguno de los usuarios disponibles se muestran las opciones disponibles creadas hasta la fecha:

[![Image from Gyazo](https://i.gyazo.com/dd0bad28680c6abf3fcae95022922c2c.png)](https://gyazo.com/dd0bad28680c6abf3fcae95022922c2c)

- Buscar vehiculos: Introduciendo el nombre del modelo y opcionalmente seleccionando la marca del vehiculo que estes buscando [![Image from Gyazo](https://i.gyazo.com/26808226341271c31dead21ebc75e816.gif)](https://gyazo.com/26808226341271c31dead21ebc75e816)
- Ver todos los vehiculos: Muestra una lista con todos los vehiculos registrados, si eres Adminstrador puedes modificar las entradas de los vehiculos [![Image from Gyazo](https://i.gyazo.com/3941f5c69efa06dbb13536bdbe446bd6.gif)](https://gyazo.com/3941f5c69efa06dbb13536bdbe446bd6)
- Ver todas las ventas: Muestra una lista con todas las ventas registradas, si entras como usuario solo puedes ver las ventas en las que estes registrado, los administradores tambien pueden eliminar la venta [![Image from Gyazo](https://i.gyazo.com/63a958d319908c92eb88ed11a18b0262.gif)](https://gyazo.com/63a958d319908c92eb88ed11a18b0262)
- Añadir vehiculo nuevo: Puedes añadir un nuevo vehiculo a la base de datos. Los usuarios no pueden ver esta opcion. [![Image from Gyazo](https://i.gyazo.com/99714a17702b10470a96d6c0af7bda0c.gif)](https://gyazo.com/99714a17702b10470a96d6c0af7bda0c)
- Buscar una venta: Puedes busar un venta por el DNI del cliente. Los usuarios no pueden ver esta opcion. [![Image from Gyazo](https://i.gyazo.com/dce2ad0388f638330260ae2cf1573c78.gif)](https://gyazo.com/dce2ad0388f638330260ae2cf1573c78)
- Nueva venta: Puedes registrar una nueva venta seleccionando el empleado que realiza la venta, el cliente que hace la compra, el vehiculo, la matricual que se le asigna, el color del coche y el precio total. [![Image from Gyazo](https://i.gyazo.com/e64d8a74eab865b46e76eb7a6dcd4561.gif)](https://gyazo.com/e64d8a74eab865b46e76eb7a6dcd4561)
- Tambien puedes modificar tus datos personales, en el caso de clientes se pueden modificar algunos datos mas, hay datos que no se pueden modificar. [![Image from Gyazo](https://i.gyazo.com/780fffcf71f313c1e1e4d9509b2ad5f4.gif)](https://gyazo.com/780fffcf71f313c1e1e4d9509b2ad5f4)
