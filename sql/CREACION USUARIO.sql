-- USER SQL
CREATE USER CONCESIONARIO IDENTIFIED BY "1234"  
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP";

-- QUOTAS

-- ROLES
GRANT "CONNECT" TO CONCESIONARIO ;
GRANT "RESOURCE" TO CONCESIONARIO ;