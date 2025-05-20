-- Create de la base de datos
CREATE DATABASE arqui
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Colombia.1252'
    LC_CTYPE = 'Spanish_Colombia.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
-- Create de tabla empleado
CREATE TABLE empleado (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    rol VARCHAR(100)
);
-- Create de tabla empleado_login
CREATE TABLE empleado_login (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    empleado_id UUID NOT NULL,
    CONSTRAINT fk_empleado
        FOREIGN KEY (empleado_id)
        REFERENCES empleado(id)
);
-- Create de la tabla producto
CREATE TABLE producto (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),  
    nombre VARCHAR(255),
    precio_compra DOUBLE PRECISION,
    precio_venta DOUBLE PRECISION,
    existencias INTEGER,
    link_imagen VARCHAR(255),
    categoria VARCHAR(255)
);
