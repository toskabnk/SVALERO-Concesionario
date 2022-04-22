DROP TABLE COMPLEMENTOS;
DROP TABLE EXTRAS;
DROP TABLE VENTAS;
DROP TABLE VEHICULO;
DROP TABLE CLIENTE;
DROP TABLE EMPLEADO;
DROP TABLE CITAS;
DROP TABLE USUARIO;

DROP SEQUENCE SECUENCIA_CITA;
DROP SEQUENCE SECUENCIA_EXTRA;
DROP SEQUENCE SECUENCIA_USUARIO;
DROP SEQUENCE SECUENCIA_VENTA;

CREATE TABLE USUARIO(
id_usuario INTEGER,
nombreUsuario VARCHAR2(30),
contraseņa VARCHAR2(30),
nombre VARCHAR2(30),
apellido1 VARCHAR2(30),
apellido2 VARCHAR2(30),
telefono VARCHAR2(30),
email VARCHAR2(50),
rol VARCHAR2(10),
CONSTRAINT PK_ID_USUARIO PRIMARY KEY(id_usuario)
);

CREATE TABLE CITAS(
id_cita INTEGER,
id_usuario INTEGER,
fecha DATE,
seccion VARCHAR2(30),
comentarios VARCHAR2(100),
CONSTRAINT PK_ID_CITA PRIMARY KEY(id_cita),
CONSTRAINT FK_ID_USUARIO_CITA FOREIGN KEY(id_usuario) REFERENCES USUARIO
);

CREATE TABLE EMPLEADO(
dni VARCHAR2(15),
id_usuario INTEGER,
departamento VARCHAR2(30),
salario INTEGER,
direccion VARCHAR2(50),
codigoEmpleado VARCHAR2(30),
CONSTRAINT PK_DNI_EMPLEADO PRIMARY KEY(dni),
CONSTRAINT FK_ID_USUARIO_EMPLEADO FOREIGN KEY(id_usuario) REFERENCES USUARIO
);

CREATE TABLE CLIENTE(
dni VARCHAR2(15),
id_usuario INTEGER,
direccion VARCHAR2(50),
provincia VARCHAR2(30),
codigoPostal VARCHAR2(10),
CONSTRAINT PK_DNI_CLIENTE PRIMARY KEY(dni),
CONSTRAINT FK_ID_USUARIO_CLIENTE FOREIGN KEY(id_usuario) REFERENCES USUARIO
);

CREATE TABLE VEHICULO(
referencia VARCHAR2(50),
marca VARCHAR2(30),
modelo VARCHAR2(30),
plazas INTEGER,
precioBase INTEGER,
CONSTRAINT PK_REFERENCIA PRIMARY KEY(referencia)
);

CREATE TABLE VENTAS(
id_venta INTEGER,
dni_empleado VARCHAR2(15),
dni_cliente VARCHAR2(15),
referencia VARCHAR2(50),
matricula VARCHAR2(10),
color VARCHAR2(15),
precioTotal INTEGER,
CONSTRAINT PK_ID_VENTA PRIMARY KEY(id_venta),
CONSTRAINT FK_DNI_EMPLEADO_VENTAS FOREIGN KEY(dni_empleado) REFERENCES EMPLEADO(dni),
CONSTRAINT FK_DNI_CLIENTE_VENTAS FOREIGN KEY(dni_cliente) REFERENCES CLIENTE(dni),
CONSTRAINT FK_REFERENCIA_VENTAS FOREIGN KEY(referencia) REFERENCES VEHICULO
);

CREATE TABLE EXTRAS(
id_extra INTEGER,
descripcion VARCHAR2(30),
precio INTEGER,
CONSTRAINT PK_ID_EXTRA PRIMARY KEY(id_extra)
);

CREATE TABLE COMPLEMENTOS(
id_extra INTEGER,
id_venta INTEGER,
CONSTRAINT PK_COMPLEMENTOS PRIMARY KEY(id_extra,id_venta),
CONSTRAINT FK_ID_EXTRA_COMP FOREIGN KEY(id_extra) REFERENCES EXTRAS,
CONSTRAINT FK_ID_VENTA_COMP FOREIGN KEY(id_venta) REFERENCES VENTAS
);

CREATE SEQUENCE SECUENCIA_USUARIO
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE;

CREATE OR REPLACE TRIGGER TRIGGER_ID_USUARIO
BEFORE INSERT ON Usuario
FOR EACH ROW
BEGIN
    SELECT SECUENCIA_USUARIO.NEXTVAL INTO :NEW.id_usuario FROM dual;
END;
/

CREATE SEQUENCE SECUENCIA_CITA
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE;

CREATE OR REPLACE TRIGGER TRIGGER_ID_CITA
BEFORE INSERT ON CITAS
FOR EACH ROW
BEGIN
    SELECT SECUENCIA_CITA.NEXTVAL INTO :NEW.id_cita FROM dual;
END;
/

CREATE SEQUENCE SECUENCIA_VENTA
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE;

CREATE OR REPLACE TRIGGER TRIGGER_ID_VENTA
BEFORE INSERT ON VENTAS
FOR EACH ROW
BEGIN
    SELECT SECUENCIA_VENTA.NEXTVAL INTO :NEW.id_venta FROM dual;
END;
/

CREATE SEQUENCE SECUENCIA_EXTRA
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE;

CREATE OR REPLACE TRIGGER TRIGGER_ID_EXTRA
BEFORE INSERT ON EXTRAS
FOR EACH ROW
BEGIN
    SELECT SECUENCIA_EXTRA.NEXTVAL INTO :NEW.id_extra FROM dual;
END;
/