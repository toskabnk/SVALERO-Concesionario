--INSERTS USUARIOS
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('SrToska','1234','Antonio','de la Rosa','Santiago',600252478,'toska@email.com','ADMIN');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('Emp1','1234','Ana Carmen','Vegano','Grapados',632589357,'anacarmen@email.com','EMPLOYEE');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('Emp2','1234','Angeles','Margarita','Roman',647512655,'angeles@email.com','EMPLOYEE');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('User1','1234','Vicenta','Carbajo','Valbuena',632547414,'vicenta@email.com','USER');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('User2','1234','Federico','Guijarro','Gaya',621547898,'federico@email.com','USER');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('User3','1234','Benigno','Ibañez','Aguilo',673143128,'benigno@email.com','USER');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('User4','1234','Benita','Bautista','Cañas',657364258,'benita@email.com','USER');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('User5','1234','Regulo','Miguel','Ribera',654782145,'regulo@email.com','USER');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('User6','1234','Manolo','Figueras','Lopez',625874151,'manolo@email.com','USER');
INSERT INTO USUARIO (nombreUsuario, contraseña, nombre, apellido1, apellido2, telefono, email, rol) VALUES ('User7','1234','Romualdo','Luza','Luza',625478551,'romualdo@email.com','REGISTER');


--INSERTS CITAS
INSERT INTO CITAS (id_usuario, fecha, seccion, comentarios) VALUES (4,'12/05/2022','INFORMACION','Me gustaria pedir informacion sobre la compra de x vehiculo');
INSERT INTO CITAS (id_usuario, fecha, seccion, comentarios) VALUES (5,'20/05/2022','VENTA','Me gustaria comprar x vehiculo');
INSERT INTO CITAS (id_usuario, fecha, seccion, comentarios) VALUES (6,'15/05/2022','TALLER','Cita para mantenimiento de mi vehiculo');
INSERT INTO CITAS (id_usuario, fecha, seccion, comentarios) VALUES (7,'5/05/2022','INFORMACION','Me gustaria pedir informacion sobre la compra de x vehiculo');

--INSERTS EMPLEADO
INSERT INTO EMPLEADO(dni, id_usuario, departamento, salario, direccion, codigoEmpleado) VALUES ('93642203A',1,'CEO',2000,'Calle Colon','EMP001');
INSERT INTO EMPLEADO(dni, id_usuario, departamento, salario, direccion, codigoEmpleado) VALUES ('09873870Q',2,'VENTAS',1900,'Calle Sevilla','EMP002');
INSERT INTO EMPLEADO(dni, id_usuario, departamento, salario, direccion, codigoEmpleado) VALUES ('26482112G',3,'VENTAS',1900,'Calle Madrid','EMP003');

--INSERTS CLIENTE 
INSERT INTO CLIENTE(dni, id_usuario, direccion, provincia, codigoPostal) VALUES ('26482112G',4,'Calle Zaragoza','Sevilla','41500');
INSERT INTO CLIENTE(dni, id_usuario, direccion, provincia, codigoPostal) VALUES ('11298941F',5,'Calle Jaen','Sevilla','41500');
INSERT INTO CLIENTE(dni, id_usuario, direccion, provincia, codigoPostal) VALUES ('39862490V',6,'Calle Malaga','Sevilla','41500');
INSERT INTO CLIENTE(dni, id_usuario, direccion, provincia, codigoPostal) VALUES ('80232539Y',7,'Calle Granada','Alcala de Guadaira','41552');
INSERT INTO CLIENTE(dni, id_usuario, direccion, provincia, codigoPostal) VALUES ('60006994V',8,'Calle Almeria','Benacazon','41532');

--INSERTS VEHICULO
INSERT INTO VEHICULO(referencia, marca, modelo, plazas, precioBase) VALUES ('6846519687','Volkswagen','Passat',5,25000);
INSERT INTO VEHICULO(referencia, marca, modelo, plazas, precioBase) VALUES ('4861845647','Volkswagen','Jetta',5,28000);
INSERT INTO VEHICULO(referencia, marca, modelo, plazas, precioBase) VALUES ('9846541684','Volkswagen','Golf',5,15000);
INSERT INTO VEHICULO(referencia, marca, modelo, plazas, precioBase) VALUES ('1238751895','Audi','A1',4,20000);
INSERT INTO VEHICULO(referencia, marca, modelo, plazas, precioBase) VALUES ('7845138651','Audi','A2',4,25000);
INSERT INTO VEHICULO(referencia, marca, modelo, plazas, precioBase) VALUES ('4845368451','Audi','A3',5,30000);
INSERT INTO VEHICULO(referencia, marca, modelo, plazas, precioBase) VALUES ('6545621687','Citroen','C1',4,15000);
INSERT INTO VEHICULO(referencia, marca, modelo, plazas, precioBase) VALUES ('4651684687','Citroen','C2',5,22000);
INSERT INTO VEHICULO(referencia, marca, modelo, plazas, precioBase) VALUES ('5418461874','Opel','Corsa',4,19000);
INSERT INTO VEHICULO(referencia, marca, modelo, plazas, precioBase) VALUES ('2135784595','Opel','Mokka',5,21000);

--INSERTS VENTAS
INSERT INTO VENTAS(dni_empleado, dni_cliente, referencia, matricula, color, precioTotal) VALUES ('93642203A','39862490V','6846519687','3658FDR','Rojo',25000);
INSERT INTO VENTAS(dni_empleado, dni_cliente, referencia, matricula, color, precioTotal) VALUES ('09873870Q','26482112G','4861845647','5247FDR','Negro',31000);
INSERT INTO VENTAS(dni_empleado, dni_cliente, referencia, matricula, color, precioTotal) VALUES ('09873870Q','11298941F','9846541684','5752FDR','Blanco',17000);
INSERT INTO VENTAS(dni_empleado, dni_cliente, referencia, matricula, color, precioTotal) VALUES ('26482112G','11298941F','6545621687','7512FDR','Azul',41000);


--INSERT EXTRAS
INSERT INTO EXTRAS(descripcion, precio) VALUES ('GPS',1000);
INSERT INTO EXTRAS(descripcion, precio) VALUES ('Asientos calefactados',2000);
INSERT INTO EXTRAS(descripcion, precio) VALUES ('Techo solar',3000);
INSERT INTO EXTRAS(descripcion, precio) VALUES ('Manos libres',500);
INSERT INTO EXTRAS(descripcion, precio) VALUES ('Lunas tintadas',800);
INSERT INTO EXTRAS(descripcion, precio) VALUES ('Llantas de aluminio',1500);
INSERT INTO EXTRAS(descripcion, precio) VALUES ('Blindado',20000);

--INSERTS COMPLEMENTOS
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (1,2);
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (2,2);
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (3,2);
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (4,1);
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (4,2);
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (4,3);
INSERT INTO COMPLEMENTOS(id_extra,id_venta) VALUES (4,4);







