# Repositorio de la AA de la asignatura de Programacion de DAM

Este es el repositorio de la **Actividad de Aprendizaje** de la asignatura de **Programacion** de **Desarrollo de Aplicaciones Multiplataformas**.

La actividad consiste en implementar una **aplicacion web** que gestione informacion almacenando y recuperandola de una **Base de Datos Relacional**
Se trabaja en el supuesto de un concesionario multimarca y se creará la aplicacion web para gestionarlo.

Antes de la creacion de la aplicacion web puedes comprobar el funcionamiento de todas las caracteristicas ejecutando el main desde cualquier IDE que soporte Java.

## Requisitos antes de implementar la aplicacion web:
- [x] Dar de alta. Se da de alta vehiculos nuevos.
- [x] Listado y vista detalle. Se pueden ver toda la informacion de la ventas (En detalle) y de los vehiculos.
- [x] Funcionalidad (Busqueda). Se pueden buscar las ventas por el DNI del cliente y los vehiculos por marca y modelo.
- [x] Dar de baja. Se pueden eliminar las ventas desde la vista en detalle de las ventas.
- [x] Login de usuarios. Cada usuario registrado puede logearse y mostrara distintas opciones segun su rol.
- [x] Funcionalidad (Modificar). Se puede modificar la informacion de un vehiculo.
- [x] Funcionalidad (Relaciones). Los empleados crean ventas que implican relacionar clientes, empleados y vehiculos.
- [x] Zona privada. Los usuarios pueden modificar sus datos.
- [x] Repositorio de Github

## Requisitos implementando la aplicacion web:
- [x] Dar de alta.
- [x] Listado y vista detalle.
- [x] Funcionalidad (Busqueda).
- [x] Dar de baja.
- [x] Login de usuarios.
- [x] Funcionalidad (Modificar).
- [x] Funcionalidad (Relaciones).
- [x] Zona privada.
- [ ] Uso de imagenes<sup>[1](#opcional)</sup>.
- [x] Funcionalidad (Javascript)<sup>[1](#opcional)</sup>.
- [ ] Funcionalidad (Paginacion)<sup>[1](#opcional)</sup>.
- [x] Trabajar con la informacion de una tercera tabla<sup>[1](#opcional)</sup>.

## Requisitos para Entornos de Desarrollo:
- [x] Crea un repositorio de Github.
- [x] Para cada funcionalidad se creara una nueva rama feature y se fusionara con develop mediente Pull Request.
- [x] Configura un Job en Jenkins para obtener el codigo del proyecto del repositorio y que se compile y empaquete el proyecto.
- [x] Añade al Job la configuracion necesaria para que el codigo del proyecto sea analizado por un SonarQube.
- [x] Instala y ejecuta VisualVM y monitoriza el rendimiento y el uso de memoria de tu aplicacion.
### Otras funcionalidades
- [ ] Configura maven para poder lanzar un analisis de codigo del proyecto directamente con esta herramienta.
- [x] Añade algunos test JUnit al proyecto y configura el Job de Jenkins para que se ejecuten cada vez que este se lance.
- [ ] Añade una release del proyecto al repositorio para que los usuarios puedan descargarse tu aplicacion para usarla directamente.
- [ ] Utiliza el gestor de issues para registrar como features cada una de las funcionalidades que realices del proyecto. Registra tambien, al menos, 3 bugs que hayas encontrado durante el desarrollo.
- [ ] Diseña el diagrama de clases.
- [ ] Diseña una pagina web para el proyecto utilizando github-pages.

## Requisitos del sistema
- Se debe de tener la base de datos de [Oracle XE](https://www.oracle.com/es/database/technologies/appdev/xe.html).
- Ejecutar el script [Concesionario.sql](sql/CONCESIONARIO.sql) y [Inserts.sql](sql/INSERTS.sql) si ya quieres tener datos de ejemplo sobre los que trabajar.
- Tener instalado al menos el JDK de [Java 11](https://adoptopenjdk.net/).
- Tener descargado y ejecutado TomCat 8.5





<a name="opcional">1</a>: Caracteristicas a desarrollar opcionales.