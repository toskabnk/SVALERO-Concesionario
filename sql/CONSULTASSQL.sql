--Consulta de Insercion. Inserta un usuario nuevo
CREATE OR REPLACE PROCEDURE insertar_usuario(
nombreUsuario IN USUARIO.NOMBREUSUARIO%type,
contrasenia IN USUARIO.CONTRASEÑA%type,
nombre IN USUARIO.NOMBRE%type,
apellido1 IN USUARIO.APELLIDO1%type,
apellido2 IN USUARIO.APELLIDO2%type,
telefono IN USUARIO.TELEFONO%type,
email IN USUARIO.EMAIL%type,
rol IN USUARIO.ROL%type)
IS
BEGIN
    INSERT INTO USUARIO(NOMBREUSUARIO,CONTRASEÑA,NOMBRE,APELLIDO1,APELLIDO2,TELEFONO,EMAIL,ROL)
    VALUES (nombreUsuario,contrasenia,nombre,apellido1,apellido2,telefono,email,rol);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
    ROLLBACK;
END insertar_usuario;
/

EXECUTE INSERTAR_USUARIO('TestUser','1234','NombreTest','Apellido1Test','Apellido2Test','600212121','TestEmail','TestRol');

--Consulta de modificacion. Modifica el usuario por el id.
CREATE OR REPLACE PROCEDURE modifica_usuario(
nidUsuario IN USUARIO.ID_USUARIO%type,
nnombreUsuario IN USUARIO.NOMBREUSUARIO%type,
ncontrasenia IN USUARIO.CONTRASEÑA%type,
nnombre IN USUARIO.NOMBRE%type,
napellido1 IN USUARIO.APELLIDO1%type,
napellido2 IN USUARIO.APELLIDO2%type,
ntelefono IN USUARIO.TELEFONO%type,
nemail IN USUARIO.EMAIL%type,
nrol IN USUARIO.ROL%type)
IS
BEGIN
    UPDATE USUARIO 
    SET NOMBREUSUARIO = nnombreUsuario, CONTRASEÑA = ncontrasenia, NOMBRE = nnombre, APELLIDO1 = napellido1, APELLIDO2 = napellido2, TELEFONO = ntelefono, EMAIL = nemail, ROL = nrol
    WHERE ID_USUARIO = nidUsuario;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
    ROLLBACK;
END modifica_usuario;
/ 

EXECUTE MODIFICA_USUARIO(9,'TestUserModificado','1234','NombreTest','Apellido1Test','Apellido2Test','600212121','TestEmail','TestRol');

--Consulta de listado agrupado. Muestra el numero de ventas de cada empleado.
SELECT COUNT(VENTAS.DNI_EMPLEADO) as NumeroVentas, VENTAS.DNI_EMPLEADO FROM VENTAS GROUP BY VENTAS.DNI_EMPLEADO;

--Informacion de una tabla principal que no tenga informacion relacionada en un tabla secundaria. Muestra los vehiculos que no tienen ninguna venta

SELECT MARCA, MODELO FROM VEHICULO
    LEFT OUTER JOIN VENTAS ON VEHICULO.REFERENCIA = VENTAS.REFERENCIA
    WHERE VENTAS.REFERENCIA IS NULL;
    
--Crear una consulta que muestre un listado de registros que contengan una cierta cadena.
SELECT NOMBREUSUARIO, NOMBRE, APELLIDO1 FROM USUARIO WHERE UPPER(NOMBREUSUARIO) LIKE UPPER('%user%');

--Para finalizar, crea un procedimiento almacenado que realice el borrado de parte de la información almacenada en una tabla secundaria, donde el/los criterios de borrado se obtengan de una tabla principal.
CREATE OR REPLACE PROCEDURE borrar_citas (DNICLIENTE IN VARCHAR) AS
BEGIN
    DELETE FROM CITAS
    WHERE ID_USUARIO = (SELECT CLIENTE.ID_USUARIO FROM CLIENTE
        JOIN USUARIO ON CLIENTE.ID_USUARIO = USUARIO.ID_USUARIO
        JOIN CITAS ON USUARIO.ID_USUARIO = CITAS.ID_USUARIO
        WHERE CLIENTE.DNI = DNICLIENTE
        );
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN 
        ROLLBACK;
END;
/

EXECUTE BORRAR_CITAS('80232539Y');